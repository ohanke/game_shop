INSERT INTO users(id, first_name, last_name, email, password)
VALUES ( 1, 'Marian', 'Kowalski', 'marian@kowalski.com', 'password');

INSERT INTO adress(adress_id, user_id, country, street, state, city, zip_code)
VALUES ( 1, 1, 'Polska' , 'Garncarska 17a', 'Pomorskie', 'Gdańsk', '12-345');

INSERT INTO product(product_id, name, category , price_nett, price_gross)
VALUES ( 1, 'Dead Space' , 'HORROR' , 10, 15);

INSERT INTO orders(order_id, user_id, order_date, total_value, order_status)
VALUES ( 1, 1, '14.06.2022', 10, 'RECIEVED');