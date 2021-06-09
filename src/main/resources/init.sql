CREATE SCHEMA IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS items;
CREATE TABLE items (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(80), imageName VARCHAR(255), price BIGINT, description VARCHAR(100), type VARCHAR (100));

INSERT INTO items(name, imageName, price, description, type ) VALUES ('Super Mobile #1', '/mobiles/mobile1.jpg', 100, 'Очень классный телефон', 'Mobile phone');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Super Mobile #2', '/mobiles/mobile2.jpg', 250, 'Очень классный телефон', 'Mobile phone');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Super Mobile #3', '/mobiles/mobile3.jpg', 350, 'Очень классный телефон', 'Mobile phone');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Super Mobile #4', '/mobiles/mobile2.jpg', 450, 'Очень классный телефон', 'Mobile phone');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Super Mobile #5', '/mobiles/mobile1.jpg', 1550, 'Очень классный телефон', 'Mobile phone');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Laptop #1', '/laptops/laptop1.jpg', 750, 'Очень классный ноут', 'Laptop');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Laptop #2', '/laptops/laptop2.jpg', 880, 'Очень классный ноут', 'Laptop');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Laptop #3', '/laptops/laptop3.jpg', 10550, 'Очень классный ноут', 'Laptop');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Laptop #4', '/laptops/laptop4.jpg', 1650, 'Очень классный ноут', 'Laptop');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Laptop #5', '/laptops/laptop5.jpg', 800.80, 'Очень классный ноут', 'Laptop');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Fridge #1', '/fridges/fridge1.jpg', 1655, 'Очень классный холодос', 'Fridge');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Fridge #2', '/fridges/fridge2.jpg', 1777, 'Очень классный холодос', 'Fridge');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Fridge #3', '/fridges/fridge3.jpg', 155, 'Очень классный холодос', 'Fridge');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Fridge #4', '/fridges/fridge4.jpg', 10, 'Очень классный холодос', 'Fridge');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Camera #1', '/cameras/camera1.png', 300, 'Очень хорошая камера', 'Camera');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Camera #2', '/cameras/camera2.jpg', 1800, 'Очень хорошая камера', 'Camera');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Camera #3', '/cameras/camera3.jpg', 1650, 'Очень хорошая камера', 'Camera');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('GPS #1', '/gps/gps1.jpg', 1550, 'Очень классный gps', 'GPS');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('GPS #2', '/gps/gps2.jpg', 850, 'Очень классный gps', 'GPS');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Cars #1', '/cars/car1.jpg', 1750, 'Очень классный автомобиль', 'Cars');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Cars #2', '/cars/car2.jpg', 16000, 'Очень классный автомобиль', 'Cars');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Cars #3', '/cars/car3.jpg', 10000, 'Очень классный автомобиль', 'Cars');
INSERT INTO items(name, imageName, price, description, type ) VALUES ('Cars #4', '/cars/car4.jpg', 155000, 'Очень классный автомобиль', 'Cars');