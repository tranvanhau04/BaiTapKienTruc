require('dotenv').config();
const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const { connectRabbitMQ, publishEvent, consumeEvent } = require('./rabbitmq');

const app = express();
app.use(express.json());
app.use(cors());

// KẾT NỐI DATABASE
mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log('✅ Đã kết nối MongoDB (payment_db)'))
  .catch(err => console.error('❌ Lỗi kết nối Database:', err));

// SCHEMA TRANSACTION (Event Sourcing)
const transactionSchema = new mongoose.Schema({
  userId: { type: String, required: true },
  amount: { type: Number, required: true },
  type: { type: String, required: true },
  referenceId: { type: String },
  timestamp: { type: Date, default: Date.now }
});
const Transaction = mongoose.model('Transaction', transactionSchema);

app.get('/wallet/:userId/balance', async (req, res) => {
    console.log(`\n🔍 [QUERY - CQRS]: Đang truy vấn số dư cho User: ${req.params.userId}`);
    const result = await Transaction.aggregate([
        { $match: { userId: req.params.userId } },
        { $group: { _id: null, balance: { $sum: "$amount" } } }
    ]);
    
    // Log này cực kỳ quan trọng
    console.log(`⚙️ [REPLAY]: Đã quét qua ${result.length} nhóm giao dịch để tính toán lại số dư.`);
    const balance = result.length > 0 ? result[0].balance : 0;
    console.log(`💰 [RESULT]: Số dư tính toán được là: ${balance}đ`);
    
    res.status(200).json({ balance });
});

// CQRS: LUỒNG NẠP TIỀN
app.post('/wallet/deposit', async (req, res) => {
    try {
        const { userId, amount } = req.body;
        await new Transaction({ userId, amount, type: 'DEPOSIT' }).save();
        res.status(200).json({ message: "Nạp tiền thành công" });
    } catch (error) { res.status(500).json({ message: "Lỗi nạp tiền" }); }
});

// XỬ LÝ THANH TOÁN TỰ ĐỘNG
async function startService() {
    await connectRabbitMQ();

    consumeEvent('movie_exchange', 'BOOKING_CREATED', 'payment_queue', async (bookingData) => {
        console.log(`\n⏳ Đang xử lý đơn: ${bookingData.bookingId}`);
        
        try {
            const { userId, seats, price, bookingId } = bookingData;
            
            // TÍNH TOÁN: Lấy giá từ Event gửi sang, không để 100k cố định nữa
            const seatsCount = seats ? seats.length : 1;
            const moviePrice = price || 100000; // Fallback 100k nếu dữ liệu lỗi
            const totalPrice = seatsCount * moviePrice; 

            // 1. Replay lấy số dư hiện tại
            const result = await Transaction.aggregate([
                { $match: { userId: userId } },
                { $group: { _id: null, balance: { $sum: "$amount" } } }
            ]);
            const currentBalance = result.length > 0 ? result[0].balance : 0;

            setTimeout(async () => {
                // 2. Kiểm tra ví & Thanh toán
                if (currentBalance >= totalPrice) {
                    const isBankNetworkOk = Math.random() > 0.1; // 90% thành công

                    if (isBankNetworkOk) {
                        // Lưu giao dịch trừ tiền
                        await new Transaction({
                            userId, amount: -totalPrice, type: 'PAYMENT', referenceId: bookingId
                        }).save();

                        console.log(`✅ Thành công: Đã trừ ${totalPrice}đ (Giá gốc: ${moviePrice}đ/ghế)`);
                        await publishEvent('movie_exchange', 'PAYMENT_COMPLETED', { ...bookingData, status: 'PAID', finalAmount: totalPrice });
                    } else {
                        console.log(`❌ Thất bại: Lỗi mạng ngân hàng.`);
                        await publishEvent('movie_exchange', 'BOOKING_FAILED', { ...bookingData, status: 'FAILED', reason: 'Lỗi mạng ngân hàng' });
                    }
                } else {
                    console.log(`❌ Thất bại: Thiếu tiền (Cần ${totalPrice}đ, nhưng ví chỉ có ${currentBalance}đ)`);
                    await publishEvent('movie_exchange', 'BOOKING_FAILED', { ...bookingData, status: 'FAILED', reason: 'Số dư không đủ' });
                }
            }, 2000);
        } catch (error) { console.error("Lỗi Payment:", error); }
    });
}

const PORT = process.env.PORT || 8084;
app.listen(PORT, () => {
    startService();
    console.log(`💰 Payment & Notification Service running on port ${PORT}`);
});