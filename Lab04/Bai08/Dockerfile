# 1. Sử dụng image PostgreSQL phiên bản 15 theo yêu cầu
FROM postgres:15

# 2. Copy file init.sql vào thư mục "ma thuật" của PostgreSQL
# Khi container khởi động lần đầu, nó sẽ tự động quét thư mục này và chạy các file .sql
COPY init.sql /docker-entrypoint-initdb.d/