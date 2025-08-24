-- Delete the database if it already exists
DROP DATABASE IF EXISTS ecom_db;

-- Create new schema
CREATE DATABASE IF NOT EXISTS ecom_db;
USE ecom_db;

-- Drop tables in reverse order to handle dependencies
DROP TABLE IF EXISTS transaction_products;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS transaction_modes;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS admin_users;
DROP TABLE IF EXISTS roles;

-- 1. roles table
CREATE TABLE roles (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(30) NOT NULL UNIQUE
);

-- 2. admin_users table
CREATE TABLE admin_users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(10) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 3. users table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(10) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    dob DATE NOT NULL,
    role_id BIGINT,
    address VARCHAR(500) NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 4. products table
CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(15) NOT NULL UNIQUE,
    product_description VARCHAR(200) NOT NULL,
    product_revision VARCHAR(5) NOT NULL,
    product_cost DOUBLE NOT NULL CHECK (product_cost > 0),
    product_selling_price DOUBLE NOT NULL CHECK (product_selling_price > 0),
    discount INT NOT NULL CHECK (discount >= 0),
    available_stock INT NOT NULL CHECK (available_stock >= 0)
);

-- 5. carts table
CREATE TABLE carts (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1 CHECK (quantity >= 1),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- 6. transaction_modes table
CREATE TABLE transaction_modes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_mode VARCHAR(20) NOT NULL UNIQUE,
    availability BOOLEAN NOT NULL
);

-- 7. transactions table
CREATE TABLE transactions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    transaction_id VARCHAR(15) NOT NULL UNIQUE,
    transaction_cost BIGINT NOT NULL,
    transaction_user BIGINT,
    transaction_mode BIGINT,
    FOREIGN KEY (transaction_user) REFERENCES users(id),
    FOREIGN KEY (transaction_mode) REFERENCES transaction_modes(id)
);

-- 8. transaction_products table
CREATE TABLE transaction_products (
    transaction_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1 CHECK (quantity >= 1),
    product_cost DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (transaction_id, product_id),
    FOREIGN KEY (transaction_id) REFERENCES transactions(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);