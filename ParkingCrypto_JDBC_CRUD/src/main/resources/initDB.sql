DROP TABLE IF EXISTS parking_cards;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS owners;


DROP SEQUENCE IF EXISTS owner_seq;
DROP SEQUENCE IF EXISTS parking_card_seq;

CREATE SEQUENCE owner_seq
  START 1000;
CREATE SEQUENCE parking_card_seq
  START 5000;

CREATE TABLE owners (
  id   INTEGER PRIMARY KEY DEFAULT nextval('owner_seq'),
  name VARCHAR(100) NOT NULL
);

CREATE TABLE cars (
  car_number VARCHAR(10) PRIMARY KEY  NOT NULL,
  owner_id   INTEGER                  NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES owners (id) ON DELETE CASCADE
);

CREATE TABLE parking_cards (
  id         INTEGER PRIMARY KEY DEFAULT nextval('parking_card_seq'),
  car_number VARCHAR(50) NOT NULL,
  start      DATE        NOT NULL,
  finish     DATE CHECK (finish > start),
  period     INTEGER,
  payCheck   DOUBLE PRECISION,
  FOREIGN KEY (car_number) REFERENCES cars (car_number) ON DELETE CASCADE
);








/*

SELECT SUM(parking_cards.payCheck)
FROM  parking_cards, (SELECT cars.car_number
                      FROM cars INNER JOIN owners ON cars.owner_id = owners.id
                      WHERE owners.id='1001') AS TAB
WHERE parking_cards.car_number=TAB.car_number;

SELECT SUM(parking_cards.payCheck)
FROM parking_cards
WHERE car_number='a11';


SELECT finish-parking_cards.start, (finish-parking_cards.start)*2.4
  FROM parking_cards

UPDATE parking_cards SET period = finish-parking_cards.start,
payCheck=(finish-parking_cards.start)*2.4;



SELECT
  parking_cards.id,
  TAB.name,
  parking_cards.car_number,
  parking_cards.start,
  parking_cards.finish,
  parking_cards.period,
  parking_cards.payCheck
FROM parking_cards, (SELECT
                       cars.car_number,
                       owners.name
                     FROM cars
                       INNER JOIN owners ON cars.owner_id = owners.id
                     WHERE owners.id = 1000) AS TAB
WHERE parking_cards.car_number = TAB.car_number;*/

/*
CREATE OR REPLACE FUNCTION public.fillPeriod(
      sta    TIMESTAMP WITHOUT TIME ZONE,
      finish TIMESTAMP WITHOUT TIME ZONE,
  OUT period INTERVAL)
  RETURNS SETOF INTERVAL
LANGUAGE 'plpgsql'

COST 100
VOLATILE
ROWS 1000
AS $BODY$

BEGIN
  period := finish - sta;
END;
$BODY$;

SELECT *
FROM parkingCards;*/
