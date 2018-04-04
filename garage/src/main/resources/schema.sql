CREATE TABLE garage (
  id       BIGINT PRIMARY KEY,
  capacity INTEGER NOT NULL,
  title    VARCHAR(40)
);

CREATE TABLE car (
  id         IDENTITY PRIMARY KEY,
  make       VARCHAR(40),
  model      VARCHAR(40),
  color      VARCHAR(10),
  is_running BOOLEAN,
  garage_id  BIGINT,
  FOREIGN KEY (garage_id) REFERENCES garage (id)
);

INSERT INTO garage(id, capacity, title) VALUES (1, 3, 'Private garage');