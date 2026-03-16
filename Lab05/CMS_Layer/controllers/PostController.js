const PostService = require('../services/PostService');

class PostController {
    static async renderHomePage(req, res) {
        // Lấy dữ liệu từ Service
        const posts = await PostService.getAllPosts();
        // Hiển thị ra màn hình
        res.render('index', { posts: posts }); 
    }

    static async createNewPost(req, res) {
        const { title, content } = req.body;
        // Gửi lệnh tạo bài viết
        await PostService.createPost(title, content);
        // Quay lại trang chủ
        res.redirect('/'); 
    }
}
module.exports = PostController;