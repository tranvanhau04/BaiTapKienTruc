# 1. Sử dụng image redis mới nhất
FROM redis:latest

# 2. Copy file cấu hình từ máy tính vào thư mục chứa config của Redis trong container
COPY redis.conf /usr/local/etc/redis/redis.conf

# 3. Mở cổng mặc định của Redis
EXPOSE 6379

# 4. Ghi đè lệnh khởi chạy mặc định để Redis đọc file cấu hình của chúng ta
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]