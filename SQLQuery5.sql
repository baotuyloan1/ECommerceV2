INSERT INTO category (name, description, create_time, update_time)
VALUES 
('Smartphone', 'Mobile phones that offer advanced features beyond making phone calls and sending text messages.', GETDATE(), GETDATE()),
('Laptop', 'Portable computer that can be easily carried and used in a variety of locations.', GETDATE(), GETDATE()),
('Tablet', 'Portable computer with a touchscreen display, typically used for browsing the internet, reading books, and watching videos.', GETDATE(), GETDATE()),
('Smartwatch', 'A wearable device that can connect to a smartphone and perform various functions such as notifications, fitness tracking, and making phone calls.', GETDATE(), GETDATE()),
('Headphones', 'A pair of small loudspeakers worn over the ears, typically used for listening to music or other audio.', GETDATE(), GETDATE()),
('Speakers', 'A device that converts electrical signals into sound waves and amplifies them to produce audio.', GETDATE(), GETDATE()),
('Charger', 'A device used to recharge the battery of electronic devices such as smartphones, tablets, and laptops.', GETDATE(), GETDATE()),
('Camera', 'A device used for taking photographs or making videos.', GETDATE(), GETDATE()),
('Game Console', 'A device designed for playing video games, typically connected to a television or monitor.', GETDATE(), GETDATE()),
('Drone', 'A flying device controlled by a remote or a smartphone app, typically used for aerial photography or videography.', GETDATE(), GETDATE())


INSERT INTO brand (name, description) VALUES ('Apple', 'Manufacturer of popular consumer electronics such as iPhone, iPad, and MacBook.')
INSERT INTO brand (name, description) VALUES ('Asus', 'Taiwanese multinational computer and phone hardware and electronics company.')
INSERT INTO brand (name, description) VALUES ('MSI', 'Taiwanese multinational company that designs and manufactures computer hardware, including gaming laptops and desktops.')
INSERT INTO brand (name, description) VALUES ('Samsung', 'South Korean multinational conglomerate that manufactures electronics, including smartphones and home appliances.')
INSERT INTO brand (name, description) VALUES ('HP', 'American multinational company that manufactures laptops, desktops, and printers.')

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 10:00:00', '2022-05-09 10:00:00', 'The latest smartphone from Apple', 'iPhone 13 Pro Max', 1399.99, 1);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 11:00:00', '2022-05-09 11:00:00', 'A powerful gaming laptop from ASUS', 'ROG Zephyrus G14', 1299.99, 2);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 12:00:00', '2022-05-09 12:00:00', 'The best tablet for creative professionals', 'iPad Pro', 899.99, 1);

INSERT INTO product (create_time, update_time, description, name, price, brand_id ) 
VALUES ('2022-05-09 13:00:00', '2022-05-09 13:00:00', 'A sleek and stylish ultrabook from MSI', 'Modern 15', 999.99, 3);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 14:00:00', '2022-05-09 14:00:00', 'The latest smartphone from Samsung', 'Galaxy S22 Ultra', 1299.99, 4);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 15:00:00', '2022-05-09 15:00:00', 'A powerful gaming laptop from ASUS', 'ROG Strix Scar 17', 2199.99, 2);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 16:00:00', '2022-05-09 16:00:00', 'The latest smartphone from Google', 'Pixel 6 Pro', 899.99, 5);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 17:00:00', '2022-05-09 17:00:00', 'A powerful gaming laptop from MSI', 'GE76 Raider', 1799.99, 3);

INSERT INTO product (create_time, update_time, description, name, price, brand_id) 
VALUES ('2022-05-09 18:00:00', '2022-05-09 18:00:00', 'The latest smartphone from OnePlus', '9 Pro', 1099.99, 3);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 1, 2);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 2, 8);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 3, 8);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 4, 7);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 5, 6);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 6, 5);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 7, 4);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 8, 3);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 9, 2);

INSERT INTO [ecommerce].[dbo].[product_category] ([create_time], [update_time], [category_id], [product_id])
VALUES (GETDATE(), GETDATE(), 10, 1);

INSERT INTO [ecommerce].[dbo].[laptop] ([create_time], [update_time], [cpu], [graphics_card], [hard_drive], [operating_system], [ram], [screen_size], [product_id])
VALUES (GETDATE(), GETDATE(), 'Intel Core i5-1135G7', 'Intel Iris Xe Graphics', '512GB NVMe SSD', 'Windows 10 Home', '8GB DDR4', 14, 1);

INSERT INTO [ecommerce].[dbo].[laptop] ([create_time], [update_time], [cpu], [graphics_card], [hard_drive], [operating_system], [ram], [screen_size], [product_id])
VALUES (GETDATE(), GETDATE(), 'Intel Core i7-1165G7', 'NVIDIA GeForce MX450', '1TB NVMe SSD', 'Windows 10 Home', '16GB DDR4', 15.6, 2);

INSERT INTO [ecommerce].[dbo].[laptop] ([create_time], [update_time], [cpu], [graphics_card], [hard_drive], [operating_system], [ram], [screen_size], [product_id])
VALUES (GETDATE(), GETDATE(), 'AMD Ryzen 7 5800H', 'NVIDIA GeForce RTX 3060', '1TB NVMe SSD', 'Windows 10 Pro', '16GB DDR4', 15.6, 3);

INSERT INTO [ecommerce].[dbo].[laptop] ([create_time], [update_time], [cpu], [graphics_card], [hard_drive], [operating_system], [ram], [screen_size], [product_id])
VALUES (GETDATE(), GETDATE(), 'Intel Core i5-11400H', 'NVIDIA GeForce GTX 1650', '512GB NVMe SSD', 'Windows 10 Home', '16GB DDR4', 15.6, 4);

INSERT INTO [ecommerce].[dbo].[laptop] ([create_time], [update_time], [cpu], [graphics_card], [hard_drive], [operating_system], [ram], [screen_size], [product_id])
VALUES (GETDATE(), GETDATE(), 'AMD Ryzen 5 5600H', 'NVIDIA GeForce GTX 1650', '512GB NVMe SSD', 'Windows 10 Home', '8GB DDR4', 14, 5);

