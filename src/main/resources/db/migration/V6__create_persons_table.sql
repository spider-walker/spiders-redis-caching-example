CREATE TABLE persons (
      id VARCHAR ( 64 )  NOT NULL,
      first_name VARCHAR ( 50 )  NOT NULL,
      last_name VARCHAR ( 50 )  NOT NULL,
      PRIMARY KEY (id)
);

CREATE TABLE address (
     id VARCHAR ( 64 )  NOT NULL,
     state VARCHAR ( 50 )  NOT NULL,
     city VARCHAR ( 50 )  NOT NULL,
     street VARCHAR ( 50 )  NOT NULL,
     zip_code VARCHAR ( 5 )  NOT NULL,
     person VARCHAR ( 64 )  NOT NULL,
     PRIMARY KEY (id)
);