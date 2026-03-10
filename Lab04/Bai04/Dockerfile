# 1. Sử dụng image nginx mới nhất theo yêu cầu
FROM nginx:latest

# 2. Copy file index.html từ máy tính của bạn vào thư mục mặc định của Nginx bên trong container
COPY index.html /usr/share/nginx/html/

# 3. Khai báo cổng 80 (Cổng mặc định mà Nginx sử dụng để phục vụ web)
EXPOSE 80

# (Lưu ý: Với Nginx, chúng ta không cần lệnh CMD ở cuối vì base image nginx:latest đã tự động cấu hình sẵn lệnh chạy ngầm rồi)