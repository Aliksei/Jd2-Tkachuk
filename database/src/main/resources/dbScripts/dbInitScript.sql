CREATE schema if NOT EXISTS hotel_schema;

DROP TABLE if EXISTS hotel_schema.country CASCADE ;
DROP TABLE if EXISTS hotel_schema.city CASCADE;
DROP TABLE if EXISTS hotel_schema.spa_request CASCADE;
DROP TABLE if EXISTS hotel_schema.apartment CASCADE;
DROP TABLE if EXISTS hotel_schema.user CASCADE;
DROP TABLE if EXISTS hotel_schema.admin CASCADE;
DROP TABLE if EXISTS hotel_schema.resident CASCADE ;
DROP TABLE if EXISTS hotel_schema.rent_request CASCADE;
DROP TABLE if EXISTS hotel_schema.spa_procedure_resident CASCADE;


CREATE TABLE hotel_schema.country(
  id BIGSERIAL PRIMARY KEY ,
  country_name VARCHAR(126) UNIQUE NOT NULL
);
INSERT INTO hotel_schema.country (country_name)
VALUES ('Belarus'),
       ('Russia'),
       ('USA'),
       ('Poland'),
       ('Ukraine'),
       ('Lithuania'),
       ('Latvia');


CREATE TABLE hotel_schema.city(
  id BIGSERIAL PRIMARY KEY ,
  city_name VARCHAR(126) UNIQUE NOT NULL
);
INSERT INTO hotel_schema.city (city_name)
VALUES ('Minsk'),
       ('Moscow'),
       ('New York'),
       ('Warsaw'),
       ('Kiev'),
       ('Vilnius'),
       ('Riga');

CREATE TABLE hotel_schema.spa_request(
  id BIGSERIAL PRIMARY KEY ,
  procedure_name VARCHAR(126) UNIQUE NOT NULL ,
  duration integer NOT NULL,
  spa_price integer NOT NULL
);
INSERT into hotel_schema.spa_request (procedure_name, duration, spa_price)
VALUES ('swimming pool', 45, 20 ),
       ('gym', 65, 23 ),
       ('massage', 40, 40 );

CREATE TABLE hotel_schema.apartment(
  id BIGSERIAL PRIMARY KEY ,
  price_per_night integer NOT NULL default 10,
  number_of_rooms integer NOT NULL ,
  person_capacity integer NOT NULL default 1
);

INSERT into hotel_schema.apartment (price_per_night, number_of_rooms, person_capacity)
VALUES (45, 1, 2),
       (55,1,3),
       (60,2,3),
       (70,2,4),
       (80,3,5),
       (80,3,6),
       (60,2,4);

CREATE TABLE hotel_schema.user(
  id BIGSERIAL PRIMARY KEY ,
  first_name VARCHAR(126) NOT NULL ,
  second_name VARCHAR(126) NOT NULL ,
  login VARCHAR(126) UNIQUE NOT NULL ,
  password VARCHAR (126) NOT NULL,
  gender VARCHAR(126) NOT NULL
);

CREATE TABLE hotel_schema.admin(
  id BIGSERIAL PRIMARY KEY,
  salary INTEGER ,
  full_time BOOLEAN
) INHERITS (hotel_schema.user);

CREATE TABLE hotel_schema.resident(
  id BIGINT PRIMARY KEY ,
  apartment_id BIGINT,
  city_id BIGINT NOT NULL ,
  country_id BIGINT NOT NULL ,
  date_of_birth DATE NOT NULL ,
  phone_number VARCHAR(126) UNIQUE NOT NULL,
  FOREIGN key (city_id) REFERENCES hotel_schema.city (id),
  FOREIGN key (country_id) REFERENCES hotel_schema.country (id),
  FOREIGN key (apartment_id) REFERENCES hotel_schema.apartment (id)
) INHERITS (hotel_schema.user);

CREATE TABLE hotel_schema.rent_request(
  id BIGSERIAL PRIMARY KEY ,
  start_date DATE NOT NULL ,
  end_date DATE NOT NULL ,
  status VARCHAR(126) DEFAULT 'DRAFT',
  apartment_id BIGINT ,
  resident_id BIGINT,
  FOREIGN KEY (apartment_id) REFERENCES hotel_schema.apartment (id),
  FOREIGN KEY (resident_id) REFERENCES hotel_schema.resident (id)
);

CREATE TABLE hotel_schema.spa_procedure_resident(
  spa_request_id BIGINT NOT NULL ,
  resident_id BIGINT NOT NULL ,
  PRIMARY KEY (spa_request_id, resident_id)
);