URL Shortener Service by using Spring Boot and MySQL.

Shorten long URL into short URL.
Interchange short URL to original URL
Store all URLs in MySQL


Create database in MYSQL
CREATE DATABASE url;
then run the project.


By using Postman

1. POST MAPPING 
http://localhost:8080/shorten?url=https://google.com

2. GET MAPPING
http://localhost:8080/analytics/{code}

3. GET MAPPING
4. http://localhost:8080/{code}
