CREATE USER 'kafeshka'@'%' IDENTIFIED BY 'test123';
GRANT ALL PRIVILEGES ON *.* TO 'kafeshka'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;
CREATE DATABASE IF NOT EXISTS kafeshka;