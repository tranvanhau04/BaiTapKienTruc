# Sử dụng bản Postgres nhẹ (alpine)
FROM postgres:15-alpine

ENV POSTGRES_PASSWORD=123456

# 2. Copy file sql vào thư mục của Postgres

COPY init.sql /docker-entrypoint-initdb.d/