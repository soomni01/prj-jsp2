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

# 권한 테이블
CREATE TABLE auth
(
    id   VARCHAR(50) REFERENCES member (id),
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id, name)
);
# admin, bdmin
INSERT INTO auth
    (id, name)
VALUES ('admin', 'admin'),
       ('bdmin', 'admin');

SELECT *
FROM auth;