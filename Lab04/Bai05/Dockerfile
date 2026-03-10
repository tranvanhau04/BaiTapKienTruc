# --- GIAI ĐOẠN 1: BUILD (Biên dịch code) ---
# Sử dụng image golang đầy đủ công cụ để build
FROM golang:1.21-alpine AS builder

WORKDIR /app

# Copy file quản lý module và mã nguồn vào container
COPY go.mod ./
COPY main.go ./

# Biên dịch mã nguồn Go thành một file chạy duy nhất tên là "myapp"
RUN go build -o myapp .


# --- GIAI ĐOẠN 2: RUN (Chạy ứng dụng) ---
# Sử dụng alpine - một hệ điều hành Linux siêu nhỏ (chỉ khoảng 5MB)
FROM alpine:latest

WORKDIR /root/

# Copy file "myapp" đã được biên dịch từ Giai đoạn 1 sang Giai đoạn 2
COPY --from=builder /app/myapp .

# Khai báo cổng 8080
EXPOSE 8080

# Lệnh khởi chạy ứng dụng
CMD ["./myapp"]