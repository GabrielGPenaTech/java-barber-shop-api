CREATE TABLE clients  (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(11) NOT NULL,
    CONSTRAINT UK_EMAIL UNIQUE (email),
    CONSTRAINT UK_PHONE UNIQUE (phone)
);