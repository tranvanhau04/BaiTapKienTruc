# ==========================================
# STAGE 1: BUILD (Dùng bản node:18 đầy đủ)
# ==========================================
FROM node:18 AS builder

# Tạo thư mục làm việc
WORKDIR /app

# Copy file cấu hình thư viện vào trước
COPY package.json ./

# Cài đặt toàn bộ thư viện (node_modules sẽ được sinh ra ở đây)
RUN npm install

# Copy toàn bộ mã nguồn vào
COPY server.js ./


# ==========================================
# STAGE 2: RUN (Dùng bản node:18-alpine siêu nhẹ)
# ==========================================
FROM node:18-alpine

# Tạo thư mục làm việc ở môi trường mới
WORKDIR /app

# CHỈ copy những gì cần thiết từ 'builder' (Stage 1) sang đây
# Ở đây ta copy thư viện (node_modules) và code (server.js)
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/server.js ./
COPY --from=builder /app/package.json ./

# Khai báo cổng
EXPOSE 3000

# Khởi chạy ứng dụng
CMD ["node", "server.js"]