DROP TABLE customer IF EXISTS;

CREATE TABLE customer
(
    id             SERIAL PRIMARY KEY,
    name           VARCHAR(255),
    favorite_genre VARCHAR(255)
);

INSERT INTO customer (name, favorite_genre)
VALUES ('Navneet', 'Action'),
       ('Buddy', 'Comedy'),
       ('Sri', 'Thriller');