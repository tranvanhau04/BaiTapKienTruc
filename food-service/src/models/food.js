const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const Food = sequelize.define('Food', {
    id: { 
        type: DataTypes.INTEGER, 
        primaryKey: true, 
        autoIncrement: true 
    },
    name: { 
        type: DataTypes.STRING, 
        allowNull: false 
    },
    price: { 
        type: DataTypes.FLOAT, 
        allowNull: false 
    },
    description: { 
        type: DataTypes.TEXT 
    }
}, {
    tableName: 'foods',
    timestamps: false // Tắt tính năng tự động tìm createdAt, updatedAt
});

// Chỉ export Food ra thôi
module.exports = { Food };