-- optional schema.sql (if not present then Spring will generate the schema by default from JPA annotations)

CREATE TABLE person (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
  first_name  VARCHAR(150),
  last_name   VARCHAR(150),
  age         INTEGER,
  place       VARCHAR(100)
);
