DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS bael_users;

CREATE TABLE tuyucheng_users
(
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (email)
);

CREATE TABLE authorities
(
    email     VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (email) REFERENCES tuyucheng_users (email)
);

CREATE UNIQUE INDEX ix_auth_email on authorities (email, authority);