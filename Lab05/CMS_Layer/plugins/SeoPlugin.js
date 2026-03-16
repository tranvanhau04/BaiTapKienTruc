const EventManager = require('../core/EventManager');

const SeoPlugin = {
    init: () => {
        EventManager.addFilter('before_post_save', (postData) => {
            // Tự động gắn thêm SEO Title trước khi lưu
            postData.seoTitle = `${postData.title} | Tối ưu SEO by Plugin`;
            return postData;
        });
        console.log("✔️ [Plugin] Auto SEO đã được kích hoạt!");
    }
};
module.exports = SeoPlugin;