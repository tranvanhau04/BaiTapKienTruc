const express = require('express');
const app = express();
const sequelize = require('./config/database');
const PostController = require('./controllers/PostController');
const SeoPlugin = require('./plugins/SeoPlugin');

// 1. Cấu hình Express
app.set('view engine', 'ejs');
app.use(express.urlencoded({ extended: true })); // Đọc dữ liệu từ Form

// 2. Kích hoạt Plugin
SeoPlugin.init();

// 3. Khai báo các đường dẫn (Router)
app.get('/', PostController.renderHomePage);
app.post('/add-post', PostController.createNewPost);

// 4. Đồng bộ CSDL và Khởi động Server
const PORT = 3000;

sequelize.sync({ alter: true }) // Lệnh này tự động tạo bảng trong MySQL nếu chưa có
    .then(() => {
        console.log("✔️ Đã kết nối và đồng bộ xong Cơ Sở Dữ Liệu MySQL!");
        app.listen(PORT, () => {
            console.log(`🚀 CMS đang chạy tại: http://localhost:${PORT}`);
        });
    })
    .catch((err) => {
        console.error("❌ Lỗi kết nối CSDL:", err);
    });