CREATE TABLE member
(
    id          VARCHAR(50) PRIMARY KEY,
    password    VARCHAR(100) NOT NULL,
    nick_name   varchar(100) NOT NULL UNIQUE,
    description VARCHAR(500),
    inserted    DATETIME     NOT NULL DEFAULT NOW()
);

SELECT *
FROM member;