const PostModel = require('../models/PostModel');
const EventManager = require('../core/EventManager');

class PostService {
    static async createPost(title, content) {
        let postData = { title, content, status: 'published' };

        // 1. Cho phép Plugin can thiệp dữ liệu
        postData = EventManager.applyFilters('before_post_save', postData);

        // 2. Lưu xuống MySQL qua ORM
        const savedPost = await PostModel.create(postData);
        return savedPost;
    }

    static async getAllPosts() {
        return await PostModel.findAll();
    }
}
module.exports = PostService;