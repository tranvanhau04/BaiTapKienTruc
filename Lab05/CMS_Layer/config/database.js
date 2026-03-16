const { Sequelize } = require('sequelize');

const sequelize = new Sequelize('cms_database', 'cms_user', 'secretpassword', {
    host: 'localhost',
    dialect: 'mysql',
    logging: false 
});

module.exports = sequelize;