
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 1, 'Marian', 'Kowalski', 'marian@kowalski.com', 'password');
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 2, 'Michał', 'Tworuszka', 'michal@tworuszka.com', 'password');
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 3, 'Oskar', 'Hanke', 'oskar@hanke.com', 'password');
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 4, 'Paweł', 'Manowski', 'pawel@manowski.com', 'password');
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 5, 'Janina', 'Nowak', 'janina@nowak.com', 'password');
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 6, 'Aleksandra', 'Pietruszka', 'ola@pietruszka.com', 'password');
INSERT INTO users(user_id, first_name, last_name, email, password)
VALUES ( 7, 'Krzysztof', 'Dudek', 'kdudek@gmail.com', 'password');

INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 1, 3, 'Polska' , 'Garncarska 17a', 'Pomorskie', 'Gdańsk', '12-345');
INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 2, 2, 'Polska' , 'Lipowa 12', 'Śląskie', 'Gliwice', '44-100');
INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 3, 2, 'Polska' , 'Zachodnia 2', 'Śląskie', 'Zabrze', '41-800');
INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 4, 5, 'Polska' , 'Rynek 14', 'Śląskie', 'Katowice', '40-000');
INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 5, 7, 'Polska' , 'Królewska 11a', 'Małopolskie', 'Kraków', '31-923');
INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 6, 4, 'Polska' , 'Wszystkich Świętych 3', 'Małopolskie', 'Kraków', '31-004');

INSERT INTO product(product_id, name, category , price_nett, price_gross)
VALUES ( 1, 'Dead Space' , 'HORROR' , 10, 15);
INSERT INTO product(product_id, name, category , price_nett, price_gross)
VALUES ( 2, 'Mass Effect' , 'SCIFI', 100, 123);
INSERT INTO product(product_id, name, category , price_nett, price_gross)
VALUES ( 3, 'Red Dead Redemtion' , 'STORY' , 150, 180);
INSERT INTO product(product_id, name, category , price_nett, price_gross)
VALUES ( 4, 'Forza' , 'RACES' , 80, 100);
INSERT INTO product(product_id, name, category , price_nett, price_gross)
VALUES ( 5, 'Warzone' , 'ACTION' , 100, 123);

INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 1, 1, '14.06.2022', 220, 'RECIEVED');
INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 2, 3, '17.06.2022', 230, 'PROCESSING');
INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 3, 5, '14.06.2022', 350, 'RECIEVED');
INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 4, 2, '10.06.2022', 800, 'DELIVERED');
INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 5, 4, '14.06.2022', 123, 'DELIVERED');
INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 6, 7, '10.06.2022', 130, 'CANCELLED');

