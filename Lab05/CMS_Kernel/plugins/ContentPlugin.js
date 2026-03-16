const ContentPlugin = {
    init: (kernel) => {
        // Cung cấp API tạo bài viết
        kernel.api.createPost = (title, content) => {
            let postData = { title, content, status: 'published' };

            // Gọi Kernel: "Tôi chuẩn bị lưu data, có ai muốn sửa không?"
            postData = kernel.applyFilters('before_post_save', postData);

            // Gọi API của Database (thông qua Kernel) để lưu
            return kernel.api.saveToDatabase(postData);
        };
    }
};
module.exports = ContentPlugin;