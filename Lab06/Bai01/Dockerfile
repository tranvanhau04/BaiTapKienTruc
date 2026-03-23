# STAGE 1: Sử dụng image đầy đủ để cài đặt
FROM node:18 AS builder
WORKDIR /build
COPY app.js .
# Giả sử đây là bước xử lý nặng (ví dụ: build từ TypeScript sang JS)
RUN echo "Đang tối ưu hóa code..." > build_log.txt

# STAGE 2: Chỉ copy những gì thực sự cần để chạy
FROM node:18-alpine
WORKDIR /app

COPY --from=builder /build/app.js .

CMD ["node", "app.js"]