import os
from http.server import BaseHTTPRequestHandler, HTTPServer

# Đọc biến môi trường APP_ENV. Nếu không tìm thấy, mặc định là 'Không xác định'
app_env = os.getenv('APP_ENV', 'Không xác định')

# 1. In ra màn hình console (Terminal / Docker Logs)
print(f"[*] Ứng dụng đã khởi động!", flush=True)
print(f"[*] Biến môi trường APP_ENV hiện tại đang là: {app_env}", flush=True)

# 2. Hiển thị lên giao diện Web
class SimpleHandler(BaseHTTPRequestHandler):
    def do_GET(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html; charset=utf-8')
        self.end_headers()
        html = f"<h2>Xin chào! Ứng dụng Python đang chạy ở môi trường: <span style='color:red'>{app_env}</span></h2>"
        self.wfile.write(html.encode('utf-8'))

if __name__ == '__main__':
    # Chạy server ở cổng 8000
    server = HTTPServer(('0.0.0.0', 8000), SimpleHandler)
    server.serve_forever()