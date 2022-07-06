# Game Shop

### Functionalities

- Account registration
- Login into account
- Customizing user profile
- Product segregation via categories
- Placing orders
- Checking order status
- Email confirmation on purchase
- Displaying suggestions based on previous purchases

### Authors

- Oscar Hanke
- Michał Tworuszka
- Paweł Manowski

### API usages

### User
1. Create user - POST/api/users {"firstName": "Josh", "lastName": "Bosh", "email": "josh.bosh@email.com", "password": "paswd1"}
2. Update user - PUT/api/users/{id} {"firstName": "John", "lastName": "Johnson", "email": "john.johnson@email.com", "password": "paswd2"}
3. Get user by id - GET/api/users/id/{id}
4. Get user by mail - GET/api/users/email/{email}
5. Get users - GET/api/users
6. Delete user - DELETE/api/users/{id}

### Product
1. Create product - POST/api/products {"name": "Flock","category": "HORROR","attributes": [3, 2],"price": 10.50}
2. Update product - PUT/api/products/{id} {"name": "Doom 3","category": "HORROR","attributes": [1, 2],"price": 15.50}
3. Get product - GET/api/products/{id}
4. Get products - GET/api/products
5. Delete product - DELETE/api/products/{id}

### Order
1. Create order - POST/api/orders {"userId": 1}
2. Update order - PUT/api/orders/{id} {"userId": 2,"totalValue": 50.0,"orderStatus": "PROCESSING","products": []}
3. Get order - GET/api/orders/{id}
4. Get orders - GET/api/orders
5. Delete order - DELETE/api/orders/{id}
6. Add product - GET/api/orders{orderId}/add/{productId}

### Adress
1. Create adress - POST/api/adresses {"country": "Pakistan","street": "Street","state": "State","city": "City","zip": "89-123"}
2. Update adress - PUT/api/adresses {"country": "Pakistan","street": "Street","state": "State","city": "City","zip": "12-987"}
3. Get adress - GET/api/adresses/{id}
4. Get adresses - GET/api/adresses
5. Delete adress - DELETE/api/adresses/{id}