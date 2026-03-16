class EventManager {
    constructor() {
        this.filters = {};
    }
    addFilter(hookName, callback) {
        if (!this.filters[hookName]) this.filters[hookName] = [];
        this.filters[hookName].push(callback);
    }
    applyFilters(hookName, data) {
        if (!this.filters[hookName]) return data;
        let result = data;
        for (const cb of this.filters[hookName]) {
            result = cb(result);
        }
        return result;
    }
}
module.exports = new EventManager();