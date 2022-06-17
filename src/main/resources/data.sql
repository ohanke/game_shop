INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 1, 'Marian', 'Kowalski', 'marian@kowalski.com', 'password', 1);
INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 2, 'Michał', 'Tworuszka', 'michal@tworuszka.com', 'password', 4);
INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 3, 'Oskar', 'Hanke', 'oskar@hanke.com', 'password', 56);
INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 4, 'Paweł', 'Manowski', 'pawel@manowski.com', 'password', 11);
INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 5, 'Janina', 'Nowak', 'janina@nowak.com', 'password', 12);
INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 6, 'Aleksandra', 'Pietruszka', 'ola@pietruszka.com', 'password', 41);
INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 7, 'Krzysztof', 'Dudek', 'kdudek@gmail.com', 'password', 123);


INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 1, 'Polska' , 'Garncarska 17a', 'Pomorskie', 'Gdańsk', '12-345');
INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 2, 'Polska' , 'Lipowa 12', 'Śląskie', 'Gliwice', '44-100');
INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 3, 'Polska' , 'Zachodnia 2', 'Śląskie', 'Zabrze', '41-800');
INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 4, 'Polska' , 'Rynek 14', 'Śląskie', 'Katowice', '40-000);
INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 5, 'Polska' , 'Królewska 11a', 'Małopolskie', 'Kraków', '31-923');
INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 6, 'Polska' , 'Wszystkich Świętych 3', 'Małopolskie', 'Kraków', '31-004');

INSERT INTO product(id, name, category, description, price_nett, price_gross)
VALUES ( 1, 'Dead Space' , 'HORROR', 'some description here' , 10, 15);
INSERT INTO product(id, name, category, description, price_nett, price_gross)
VALUES ( 2, 'Mass Effect' , 'SCIFI', 'some description here' , 100, 123);
INSERT INTO product(id, name, category, description, price_nett, price_gross)
VALUES ( 3, 'Red Dead Redemtion' , 'STORY', 'some description here' , 150, 180);
INSERT INTO product(id, name, category, description, price_nett, price_gross)
VALUES ( 4, 'Forza' , 'RACES', 'some description here' , 80, 100);
INSERT INTO product(id, name, category, description, price_nett, price_gross)
VALUES ( 5, 'Warzone' , 'ACTION', 'some description here' , 100, 123);

INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 1, 1, '14.06.2022', 220, 'RECIEVED');
INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 2, 3, '17.06.2022', 230, 'PROCESSING');
INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 3, 5, '14.06.2022', 350, 'RECIEVED');
INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 4, 2, '10.06.2022', 800, 'DELIVERED');
INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 5, 4, '14.06.2022', 123, 'DELIVERED');
INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 6, 7, '10.06.2022', 130, 'CANCELLED');