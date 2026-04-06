const express = require('express');
const cors = require('cors');
const sequelize = require('./src/config/database');
const foodRoutes = require('./src/routes/food.routes'); // File route API của bạn
require('dotenv').config();

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Định tuyến API
app.use('/foods', foodRoutes);

const PORT = process.env.PORT || 9902;

// Khởi động Database và Server
sequelize.sync({ alter: true }) 
    .then(() => sequelize.authenticate())
    .then(() => {
        console.log('✅ Đã kết nối Database thành công!');
        // Đã xóa hoàn toàn phần gọi seedData ở đây
    })
    .then(() => {
        app.listen(PORT, () => {
            console.log(`🚀 Food Service đang chạy tại http://localhost:${PORT}`);
        });
    })
    .catch(err => console.error('❌ Lỗi khởi động:', err));