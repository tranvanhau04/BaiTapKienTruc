const { Food } = require('../models/food');

// 1. Lấy danh sách tất cả món ăn
exports.getAllFoods = async (req, res) => {
    try {
        const foods = await Food.findAll();
        res.json(foods);
    } catch (error) {
        res.status(500).json({ message: 'Lỗi server', error: error.message });
    }
};

// 2. Thêm món ăn mới
exports.createFood = async (req, res) => {
    try {
        const newFood = await Food.create(req.body);
        res.status(201).json(newFood);
    } catch (error) {
        res.status(500).json({ message: 'Lỗi khi tạo món', error: error.message });
    }
};

// 3. Cập nhật thông tin món ăn theo ID
exports.updateFood = async (req, res) => {
    try {
        const foodId = req.params.id;
        
        // Tìm món ăn theo ID (Primary Key)
        const food = await Food.findByPk(foodId);
        
        if (!food) {
            return res.status(404).json({ message: 'Không tìm thấy món ăn để cập nhật' });
        }

        // Cập nhật dữ liệu mới từ req.body
        await food.update(req.body);
        
        // Trả về dữ liệu món ăn sau khi đã cập nhật thành công
        res.json(food);
    } catch (error) {
        res.status(500).json({ message: 'Lỗi khi cập nhật món', error: error.message });
    }
};

// 4. Xóa món ăn theo ID
exports.deleteFood = async (req, res) => {
    try {
        const foodId = req.params.id;
        
        // Hàm destroy trả về số lượng dòng bị xóa
        const deletedCount = await Food.destroy({ 
            where: { id: foodId } 
        });
        
        if (deletedCount === 0) {
            return res.status(404).json({ message: 'Không tìm thấy món ăn để xóa' });
        }

        res.json({ message: 'Đã xóa món ăn thành công' });
    } catch (error) {
        res.status(500).json({ message: 'Lỗi khi xóa món', error: error.message });
    }
};