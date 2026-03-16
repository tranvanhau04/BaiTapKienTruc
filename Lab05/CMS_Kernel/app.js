// 1. Lấy nhân hệ thống
const kernel = require('./kernel/Microkernel');

// 2. Lấy các module (Plugin)
const DatabasePlugin = require('./plugins/DatabasePlugin');
const ContentPlugin = require('./plugins/ContentPlugin');
const SeoPlugin = require('./plugins/SeoPlugin');
const WebUIPlugin = require('./plugins/WebUIPlugin');

// 3. Tiến hành cắm Plugin vào Kernel (Hot-plugging)
console.log("=== KHỞI ĐỘNG HỆ THỐNG MICROKERNEL ===");

// Chú ý: Thứ tự cắm có thể quan trọng (Vd: DB phải có trước để Content dùng)
kernel.registerPlugin('Database', DatabasePlugin);
kernel.registerPlugin('Content', ContentPlugin);
kernel.registerPlugin('SeoTagger', SeoPlugin);
kernel.registerPlugin('WebFrontend', WebUIPlugin);

console.log("=== HỆ THỐNG ĐÃ SẴN SÀNG ===");