CREATE TABLE garage (
  id       BIGINT PRIMARY KEY,
  title    VARCHAR(40),
  host     VARCHAR(100),
  location VARCHAR(100)
);

CREATE TABLE car (
  id           IDENTITY PRIMARY KEY,
  make         VARCHAR(40),
  model        VARCHAR(40),
  color        VARCHAR(10),
  creation_day DATE,
  mileage      BIGINT,
  is_running   BOOLEAN,
  garage_id    BIGINT,
  FOREIGN KEY (garage_id) REFERENCES garage (id)
);

INSERT INTO garage(id, title, host, location) VALUES (1, 'Private garage', 'Vasya Pupkin', 'Moscow, Kutuzovsky avenue 32');
INSERT INTO garage(id, title, host, location) VALUES (2, 'Mall parking', 'Roman Abramovich', 'Saratov, Astrahansky street 20');
INSERT INTO garage(id, title, host, location) VALUES (3, 'Public parking', 'Public', 'Moscow, Sh. Entuziastov');

insert into car(make, model, color, creation_day, mileage, is_running, garage_id) VALUES('BMW', 'M3', 'Red', '2015-08-30', 20000, 0, 1);
insert into car(make, model, color, creation_day, mileage, is_running, garage_id) VALUES('Audi', 'A4', 'White', '2015-02-14', 25000, 0, 1);
insert into car(make, model, color, creation_day, mileage, is_running, garage_id) VALUES('Ford', 'Focus', 'Red', '2016-09-22', 10000, 1, 1);