# 1. Sử dụng python:3.9 làm base image theo yêu cầu
FROM python:3.9

# 2. Thiết lập thư mục làm việc bên trong container
WORKDIR /app

# 3. Copy file requirements.txt vào container
COPY requirements.txt .

# 4. Cài đặt các thư viện (Flask) từ file requirements
RUN pip install --no-cache-dir -r requirements.txt

# 5. Copy toàn bộ mã nguồn (app.py) vào container
COPY app.py .

# 6. Khai báo cổng 5000 mà ứng dụng Flask sẽ sử dụng
EXPOSE 5000

# 7. Lệnh khởi chạy ứng dụng khi container bắt đầu
CMD ["python", "app.py"]