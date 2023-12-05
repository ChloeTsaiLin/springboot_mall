# MallWebApi-demo
#### This is a Java API practice project using the MVC pattern and Spring Boot framework, which contains 3 controller interfaces:
1. Account: 
    1. register : check email before create account, use MD5 for password hashing.
    2. log in : with simple validation to check if the account exists.
2. Product: CRUD for products. Can set search conditions such as sorting, pagination and total-count.
3. Order: CRU for orders. Add order data corresponding to the user and order product data corresponding to the product. Show all order data of the user with order product data of each order.

