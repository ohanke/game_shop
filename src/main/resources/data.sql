INSERT INTO users(id, first_name, last_name, email, password, adress_id)
VALUES ( 1, 'Marian', 'Kowalski', 'marian@kowalski.com', 'password', 1);

INSERT INTO adress(id, country, address, state, city, zip)
VALUES ( 1, 'Polska' , 'Garncarska 17a', 'Pomorskie', 'Gdańsk', '12-345');

INSERT INTO product(id, name, category, description, price_nett, price_gross)
VALUES ( 1, 'Dead Space' , 'HORROR', 'some description here' , 10, 15);

INSERT INTO orders(id, user_id, order_date, total_value, order_status)
VALUES ( 1, 1, '14.06.2022', 10, 'RECIEVED');