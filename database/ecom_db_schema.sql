-- Create new schema
CREATE DATABASE IF NOT EXISTS ecom_db;

-- Use the desired schema
USE ecom_db;

-- Drop tables in reverse order to handle dependencies
DROP TABLE IF EXISTS transaction_products;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS transaction_modes;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS admin_users;
DROP TABLE IF EXISTS roles;

-- 1. roles table
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(30) NOT NULL
);

-- 2. admin_users table
CREATE TABLE admin_users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    username VARCHAR(10) NOT NULL,
    password VARCHAR(255) NOT NULL,
    dob DATE,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 3. users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(500),
    username VARCHAR(10) NOT NULL,
    password VARCHAR(255) NOT NULL,
    dob DATE,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 4. products table
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(15) NOT NULL,
    product_description VARCHAR(200),
    product_revision VARCHAR(5),
    product_cost DOUBLE,
    product_selling_price DOUBLE,
    discount INT,
    available_stock INT CHECK (available_stock >= 0)
);

-- 5. cart table
CREATE TABLE cart (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    product_id BIGINT,
    quantity INT DEFAULT 1 CHECK (quantity > 0),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 6. transaction_modes table
CREATE TABLE transaction_modes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_mode VARCHAR(20),
    availability BOOLEAN
);

-- 7. transactions table
CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_id VARCHAR(15),
    transaction_user BIGINT,
    transaction_cost BIGINT,
    transaction_mode BIGINT,
    FOREIGN KEY (transaction_user) REFERENCES users(id),
    FOREIGN KEY (transaction_mode) REFERENCES transaction_modes(id)
);

-- 8. transaction_products table (junction table with quantity and snapshot cost)
CREATE TABLE transaction_products (
    transaction_id BIGINT,
    product_id BIGINT,
    quantity INT DEFAULT 1 CHECK (quantity > 0),
    product_cost DOUBLE,
    PRIMARY KEY (transaction_id, product_id),
    FOREIGN KEY (transaction_id) REFERENCES transactions(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);