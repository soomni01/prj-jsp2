USE jsp2;

CREATE TABLE board
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    title    VARCHAR(200)  NOT NULL,
    content  VARCHAR(5000) NOT NULL,
    writer   VARCHAR(200)  NOT NULL,
    inserted DATETIME      NOT NULL DEFAULT NOW()
);

SELECT *
FROM board;

SELECT COUNT(*)
FROM board;

INSERT INTO board(title, content, writer)
SELECT title, content, writer
FROM board;
