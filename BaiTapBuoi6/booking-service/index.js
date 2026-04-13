require('dotenv').config();
const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
const { connectRabbitMQ, publishEvent } = require('./rabbitmq');

const app = express();
app.use(express.json());
app.use(cors());

// KẾT NỐI DATABASE
mongoose.connect(process.env.MONGO_URI)
  .then(() => console.log('✅ Đã kết nối MongoDB (booking_db)'))
  .catch(err => console.error('❌ Lỗi kết nối Database:', err));

// SCHEMA BOOKING
const bookingSchema = new mongoose.Schema({
  userId: { type: String, required: true },
  movieId: { type: String, required: true },
  seats: { type: [String], required: true },
  status: { type: String, default: 'PENDING' }
});
const Booking = mongoose.model('Booking', bookingSchema);

// API TẠO BOOKING
app.post('/bookings', async (req, res) => {
    try {
        // NHẬN THÊM BIẾN PRICE TỪ FRONTEND GỬI LÊN
        const { userId, movieId, seats, price } = req.body; 
        
        const newBooking = new Booking({ userId, movieId, seats, status: 'PENDING' });
        const savedBooking = await newBooking.save();
        
        console.log(`🎟️ Đã lưu đơn hàng: ${savedBooking._id} | Giá mỗi ghế: ${price}đ`);

        // BẮN EVENT: Gửi kèm giá vé (price) sang cho Payment xử lý
        await publishEvent('movie_exchange', 'BOOKING_CREATED', {
            bookingId: savedBooking._id,
            userId: savedBooking.userId,
            movieId: savedBooking.movieId,
            seats: savedBooking.seats,
            price: price // <--- Truyền giá thực tế vào đây
        });
        
        res.status(201).json({ 
            message: "Booking thành công, đang chờ thanh toán!", 
            bookingId: savedBooking._id 
        });
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: "Lỗi tạo đơn đặt vé!" });
    }
});

const PORT = process.env.PORT || 8083;
app.listen(PORT, async () => {
    await connectRabbitMQ();
    console.log(`🎟️ Booking Service running on port ${PORT}`);
});