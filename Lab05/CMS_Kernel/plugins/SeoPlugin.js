
const SeoPlugin = {
    init: (kernel) => {
        // Can thiệp vào sự kiện của ContentPlugin
        kernel.addHook('before_post_save', (postData) => {
            postData.seoTags = `[SEO] ${postData.title} | Đỉnh cao Microkernel`;
            return postData;
        });
    }
};
module.exports = SeoPlugin;