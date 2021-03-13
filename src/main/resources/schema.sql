DROP TABLE IF EXISTS BRANDS;

DROP TABLE IF EXISTS PRODUCT;

DROP TABLE IF EXISTS PRICE;

CREATE TABLE BRAND (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(250) DEFAULT NULL
);

CREATE TABLE PRODUCT (
  ID INT AUTO_INCREMENT  PRIMARY KEY,
  NAME VARCHAR(250) DEFAULT NULL
);

CREATE TABLE PRICE (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  BRAND_ID INT NOT NULL,
  START_DATE DATE NOT NULL,
  END_DATE DATE NOT NULL,
  PRODUCT_ID INT NOT NULL,
  PRIORITY INT DEFAULT 0,
  COST DECIMAL(2,2) DEFAULT 0,
  CURR VARCHAR(250) DEFAULT NULL,
  foreign key (BRAND_ID) references BRAND(ID),
  foreign key (PRODUCT_ID) references PRODUCT(ID)
);