DROP TABLE IF EXISTS customers;
CREATE TABLE customers
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(255)          NULL,
    last_name  VARCHAR(255)          NULL,
    email      VARCHAR(255)          NULL,
    gender     VARCHAR(255)          NULL,
    ip_address VARCHAR(255)          NULL,
    CONSTRAINT pk_customer PRIMARY KEY (id)
);