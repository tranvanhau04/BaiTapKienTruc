class Microkernel {
    constructor() {
        this.plugins = {};
        this.hooks = {}; // Hệ thống sự kiện & Hook
        this.api = {};   // Giao diện API cốt lõi (Để các plugin chia sẻ hàm cho nhau)
    }

    // Bộ Nạp & Quản lý Plugin
    registerPlugin(pluginName, pluginModule) {
        this.plugins[pluginName] = pluginModule;
        // Gọi hàm khởi tạo của Plugin và truyền chính Kernel vào
        pluginModule.init(this); 
        console.log(`[Kernel] 🔌 Đã cắm Plugin: ${pluginName}`);
    }

    // Đăng ký một điểm neo sự kiện
    addHook(hookName, callback) {
        if (!this.hooks[hookName]) this.hooks[hookName] = [];
        this.hooks[hookName].push(callback);
    }

    // Kích hoạt điểm neo (Cho phép các Plugin can thiệp dữ liệu)
    applyFilters(hookName, data) {
        let processedData = data;
        if (this.hooks[hookName]) {
            for (const callback of this.hooks[hookName]) {
                processedData = callback(processedData);
            }
        }
        return processedData;
    }
}

module.exports = new Microkernel(); // Xuất ra một bản thể duy nhất (Singleton)