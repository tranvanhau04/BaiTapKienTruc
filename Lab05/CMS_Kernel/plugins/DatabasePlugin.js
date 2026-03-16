// Plugin này chịu trách nhiệm mô phỏng Database
const DatabasePlugin = {
    init: (kernel) => {
        const db = []; // Giả lập CSDL

        // Cung cấp API lõi cho Kernel để các Plugin khác dùng
        kernel.api.saveToDatabase = (data) => {
            data.id = db.length + 1;
            db.push(data);
            return data;
        };

        kernel.api.getAllData = () => {
            return db;
        };
    }
};
module.exports = DatabasePlugin;