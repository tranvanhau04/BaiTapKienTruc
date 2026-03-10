-- Tạo bảng người dùng (users)
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL
);

-- Thêm một vài dữ liệu mẫu vào bảng
INSERT INTO users (username, email) VALUES
('nguyen_van_a', 'nguyenvana@example.com'),
('tran_thi_b', 'tranthib@example.com'),
('docker_master', 'admin@docker.local');