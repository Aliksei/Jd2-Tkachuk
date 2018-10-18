CREATE SCHEMA if not exists application_schema;

DROP TABLE if exists application_schema.clients;

CREATE TABLE application_schema.clients (
  id SERIAL PRIMARY KEY,
  first_name CHARACTER VARYING(128) NOT NULL,
  second_name CHARACTER VARYING(128) not null,
  age INTEGER not null
);

INSERT INTO application_schema.clients (first_name, second_name, age) VALUES
                                                                             ('Alex', 'Tkachuk', 24),
                                                                             ('Vasia', 'Pupkin', 21 ),
                                                                             ('Vasia', 'Psdupkin', 221 ),
                                                                             ('Andrei', 'Ivanov', 24);