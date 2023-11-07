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
VALUES ('Apple', 'FRESH_PRODUCE', 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/992px-Red_Apple.jpg', 
	20, 30, 'Treat your family to the wildly juicy, sweet and crisp taste of Envy Apples. With a classic apple cider flavor, these apples have a firm texture with flesh that is slow to brown. This variety was developed in New Zealand by master farmers and now grown in Zillah, Washington.', 
	'2023-11-02 02:41:28', '2023-11-02 22:41:28'), 
	('Avocados', 'FRESH_PRODUCE', 'https://m.media-amazon.com/images/I/51FXx8p9rSL._AC_SX522_.jpg', 
	25, 50, 'Avocados can be used in Mexican food items like tacos or burritos, as appetizers like avocado crostini, or in a fresh guacamole or avocado dip. They provide unsaturated "good" fats and almost 20 vitamins, minerals, and phytonutrients.', 
	'2023-11-07 12:41:28', '2023-11-07 13:35:28'), 
	('Fresh Broccoli', 'FRESH_PRODUCE', 'https://static.hkej.net/lj/images/2016/12/14/1449083_9e7c846103113dc81a083495775dfb53_940.jpg',
	65, 40, 'Broccoli crowns are the premium cut of broccoli, trimmed just under the broccoli head. Healthy & Delicious!', 
	'2023-11-07 12:41:28', '2023-11-07 13:35:28'),
	('Snickers Iced Coffee Latte', 'COFFEE', 'https://m.media-amazon.com/images/I/81JDSDZm-vL._SX679_PIbundle-12,TopRight,0,0_SX679SY433SH20_.jpg',
	65, 40, ' Reach for this rich and delicious blend of Victor Allen’s Snickers Iced Coffee made with real milk and cream for the perfect blend of coffee and flavor!', 
	'2023-11-07 12:41:28', '2023-11-07 13:35:28'),

INSERT product (product_name, category, image_url, 
	price, stock, created_date, last_modified_date)
VALUES ('Instant Coffee Crystals', 'COFFEE', 'https://a.rimg.com.tw/s3/gmarket/bf9/306/agent%40gmarket/2/52/2b/808325a32c04be638ad5f29ae2773772_32021000493611.jpg',
	300, 80, '2023-11-07 14:07:08', '2023-11-07 14:15:08'),
	
	
	