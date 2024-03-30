CREATE TABLE Customers (
    customerID INT PRIMARY KEY,
    customerName VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE Payments (
    paymentID INT PRIMARY KEY,
    cardName VARCHAR(255),
    CVV VARCHAR(4),
    expDate DATE
);

CREATE TABLE Suppliers (
    supplierID INT PRIMARY KEY,
    supplierName VARCHAR(255),
    contactPhone VARCHAR(20)
);

CREATE TABLE Products (
    productID INT PRIMARY KEY,
    productName VARCHAR(255),
    stock INT,
    price DECIMAL(10, 2),
    supplierID INT,
    FOREIGN KEY (supplierID) REFERENCES Suppliers(supplierID)
);

CREATE TABLE Orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    customerID INT,
    timestamp DATETIME,
    total DECIMAL(10, 2),
    paymentID INT,
    FOREIGN KEY (customerID) REFERENCES Customers(customerID),
    FOREIGN KEY (paymentID) REFERENCES Payments(paymentID)
);

CREATE TABLE OrderProducts (
    orderID INT,
    productID INT,
    quantity INT,
    PRIMARY KEY (orderID, productID),
    FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    FOREIGN KEY (productID) REFERENCES Products(productID)
);







-- Inserting sample data into Customers table
INSERT INTO Customers (customerID, customerName, email) VALUES
(1, 'John Smith', 'john@example.com'),
(2, 'Alice Johnson', 'alice@example.com'),
(3, 'Michael Brown', 'michael@example.com'),
(4, 'Emily Davis', 'emily@example.com'),
(5, 'Daniel Wilson', 'daniel@example.com');

-- Inserting sample data into Payments table
INSERT INTO Payments (paymentID, cardName, CVV, expDate) VALUES
(1, 'John Smith', '123', '2025-10-01'),
(2, 'Alice Johnson', '456', '2024-12-01'),
(3, 'Michael Brown', '789', '2025-06-01'),
(4, 'Emily Davis', '234', '2024-08-01'),
(5, 'Daniel Wilson', '567', '2025-03-01');

-- Inserting sample data into Suppliers table
INSERT INTO Suppliers (supplierID, supplierName, contactPhone) VALUES
(1, 'Supplier A', '123-456-7890'),
(2, 'Supplier B', '987-654-3210'),
(3, 'Supplier C', '456-789-0123'),
(4, 'Supplier D', '321-654-0987'),
(5, 'Supplier E', '789-012-3456');

-- Inserting sample data into Products table
INSERT INTO Products (productID, productName, stock, price, supplierID) VALUES
(1, 'Product 1', 50, 10.99, 1),
(2, 'Product 2', 100, 5.99, 2),
(3, 'Product 3', 75, 8.49, 3),
(4, 'Product 4', 30, 15.99, 4),
(5, 'Product 5', 40, 12.49, 5),
(6, 'Product 6', 60, 9.99, 1),
(7, 'Product 7', 25, 7.99, 2),
(8, 'Product 8', 80, 6.49, 3),
(9, 'Product 9', 20, 11.99, 4),
(10, 'Product 10', 35, 14.49, 5),
(11, 'Product 11', 55, 13.99, 1),
(12, 'Product 12', 70, 4.99, 2),
(13, 'Product 13', 45, 10.49, 3),
(14, 'Product 14', 90, 8.99, 4),
(15, 'Product 15', 15, 16.49, 5),
(16, 'Product 16', 65, 12.99, 1),
(17, 'Product 17', 10, 6.99, 2),
(18, 'Product 18', 85, 9.49, 3),
(19, 'Product 19', 95, 11.99, 4),
(20, 'Product 20', 5, 17.49, 5);

-- SQL query to insert data into the 'orders' table with randomly generated timestamps
INSERT INTO orders (customerID, timestamp, total, paymentID)
VALUES
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 50.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 75.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 60.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 90.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 120.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 85.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 95.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 110.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 70.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 80.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 45.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 35.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 100.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 65.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 55.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 40.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 25.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 70.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 30.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 90.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 65.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 45.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 80.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 50.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 100.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 55.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 55.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 90.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 60.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 110.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 70.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 65.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 120.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 75.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 120.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 75.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 75.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 130.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 85.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 130.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 85.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 85.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 140.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 95.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 140.99, 5),
    (1, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 95.99, 1),
    (2, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 95.49, 2),
    (3, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 150.99, 3),
    (4, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 105.99, 4),
    (5, NOW() - INTERVAL FLOOR(RAND() * 365) + 1 DAY, 150.99, 5);


-- SQL query to insert data into the 'orderproducts' table
INSERT INTO orderproducts (orderID, productID, quantity)
VALUES
    (1, 1, 2),
    (1, 3, 1),
    (2, 2, 3),
    (2, 5, 2),
    (3, 4, 1),
    (3, 6, 4),
    (4, 7, 3),
    (4, 9, 1),
    (5, 10, 2),
    (5, 12, 3),
    (6, 14, 1),
    (6, 15, 2),
    (7, 16, 3),
    (7, 18, 1),
    (8, 20, 2),
    (8, 1, 4),
    (9, 2, 3),
    (9, 4, 2),
    (10, 5, 1),
    (10, 7, 3),
    (11, 9, 2),
    (11, 11, 1),
    (12, 12, 4),
    (12, 14, 2),
    (13, 15, 3),
    (13, 17, 1),
    (14, 18, 2),
    (14, 20, 4),
    (15, 1, 3),
    (15, 3, 1),
    (16, 4, 2),
    (16, 6, 3),
    (17, 7, 1),
    (17, 9, 2),
    (18, 10, 4),
    (18, 12, 1),
    (19, 14, 3),
    (19, 15, 2),
    (20, 16, 1),
    (20, 18, 4),
    (21, 20, 3),
    (21, 1, 1),
    (22, 2, 2),
    (22, 4, 3),
    (23, 5, 4),
    (23, 7, 1),
    (24, 9, 2),
    (24, 11, 3),
    (25, 12, 1),
    (25, 14, 2),
    (26, 15, 3),
    (26, 17, 4),
    (27, 1, 2),
    (27, 3, 3),
    (28, 2, 1),
    (28, 4, 4),
    (29, 5, 2),
    (29, 7, 3),
    (30, 9, 1),
    (30, 11, 2),
    (31, 12, 3),
    (31, 14, 4),
    (32, 15, 1),
    (32, 17, 2),
    (33, 18, 3),
    (33, 20, 1),
    (34, 1, 2),
    (34, 3, 3),
    (35, 4, 4),
    (35, 6, 1),
    (36, 7, 2),
    (36, 9, 3),
    (37, 10, 1),
    (37, 12, 4),
    (38, 14, 2),
    (38, 15, 3),
    (39, 16, 2),
    (39, 18, 1),
    (40, 20, 3),
    (40, 1, 2),
    (41, 2, 1),
    (41, 4, 4),
    (42, 5, 3),
    (42, 7, 2),
    (43, 9, 1),
    (43, 11, 3),
    (44, 12, 2),
    (44, 14, 1),
    (45, 15, 4),
    (45, 17, 2),
    (46, 18, 1),
    (46, 20, 3),
    (47, 1, 2),
    (47, 3, 3),
    (48, 4, 4),
    (48, 6, 1),
    (49, 7, 2),
    (49, 9, 3),
    (50, 10, 1),
    (50, 12, 4);

UPDATE orders o
SET o.total = (
    SELECT SUM(op.quantity * p.price)
    FROM orderproducts op
    INNER JOIN products p ON op.productID = p.productID
    WHERE op.orderID = o.orderID
)
WHERE o.orderID BETWEEN 1 AND 50;
