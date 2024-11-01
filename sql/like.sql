CREATE TABLE likes
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    post_id   INT          NOT NULL,
    member_id VARCHAR(200) NOT NULL,
    inserted  DATETIME     NOT NULL DEFAULT NOW()
);

SELECT *
FROM likes;