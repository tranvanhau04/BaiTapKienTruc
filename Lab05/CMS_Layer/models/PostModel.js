const { DataTypes } = require('sequelize');
const sequelize = require('../config/database');

const PostModel = sequelize.define('Post', {
    title: { type: DataTypes.STRING, allowNull: false },
    content: { type: DataTypes.TEXT },
    status: { type: DataTypes.STRING, defaultValue: 'published' },
    seoTitle: { type: DataTypes.STRING, allowNull: true }
});

module.exports = PostModel;