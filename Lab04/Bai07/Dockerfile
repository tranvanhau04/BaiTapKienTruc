# Sử dụng base image Python bản slim cho nhẹ
FROM python:3.9-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Copy file code vào container
COPY app.py .

# Thiết lập biến môi trường theo đúng yêu cầu đề bài
ENV APP_ENV=development

# Mở cổng 8000
EXPOSE 8000

# Lệnh chạy ứng dụng
CMD ["python", "app.py"]