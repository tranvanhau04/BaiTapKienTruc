const express = require('express');

const WebUIPlugin = {
    init: (kernel) => {
        const app = express();
        app.set('view engine', 'ejs');
        app.use(express.urlencoded({ extended: true }));

        // Trang chủ: Lấy dữ liệu qua Kernel
        app.get('/', (req, res) => {
            const posts = kernel.api.getAllData();
            res.render('index', { posts });
        });

        // Xử lý Form: Tạo bài viết qua Kernel
        app.post('/add-post', (req, res) => {
            const { title, content } = req.body;
            kernel.api.createPost(title, content);
            res.redirect('/');
        });

        // Kích hoạt Server
        const PORT = 3000;
        app.listen(PORT, () => {
            console.log(`[WebUI] 🚀 Giao diện đang chạy tại http://localhost:${PORT}`);
        });
    }
};
module.exports = WebUIPlugin;