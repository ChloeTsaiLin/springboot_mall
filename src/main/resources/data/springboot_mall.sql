CREATE DATABASE IF NOT EXISTS mall;
USE mall;

CREATE TABLE product
(
 product_id		INT		NOT NULL PRIMARY KEY AUTO_INCREMENT,
 product_name		VARCHAR(128)	NOT NULL,
 category		VARCHAR(64)	NOT NULL,
 image_url		VARCHAR(256)	NOT NULL,
 price			INT		NOT NULL,
 stock			INT		NOT NULL,
 product_desc		VARCHAR(1024),
 created_date		TIMESTAMP		NOT NULL,
 last_modified_date	TIMESTAMP		NOT NULL
);

INSERT product (product_name, category, image_url, price, stock, 
	product_desc, created_date, last_modified_date)
VALUES ('apple', 'FOOD', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/992px-Red_Apple.jpg', 
	20, 30, 'Treat your family to the wildly juicy, sweet and crisp taste of Envy Apples. With a classic apple cider flavor, these apples have a firm texture with flesh that is slow to brown. This variety was developed in New Zealand by master farmers and now grown in Zillah, Washington.', 
	'2023-11-02 02:41:28', '2023-11-02 22:41:28')