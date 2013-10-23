CREATE DATABASE IF NOT EXISTS newsfeed;
CREATE USER 'newsfeed_user'@'localhost' IDENTIFIED BY 'newsfeed_pass';
GRANT ALL ON newsfeed.* to 'newsfeed_user'@'localhost';
