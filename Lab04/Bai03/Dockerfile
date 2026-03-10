FROM node:18-alpine
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .

# Fix lỗi WebSocket khi chạy Docker khác cổng
ENV WDS_SOCKET_PORT=0 

EXPOSE 3000
CMD ["npm", "start"]