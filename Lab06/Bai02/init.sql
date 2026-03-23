-- Tự động tạo bảng khi khởi động
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    fullname VARCHAR(100),
    student_id VARCHAR(20)
);

-- Chèn dữ liệu mẫu (Seed data)
INSERT INTO students (fullname, student_id) VALUES 
('Tran Hau', '226xxxx1'), 
('Nguyen Van A', '226xxxx2');