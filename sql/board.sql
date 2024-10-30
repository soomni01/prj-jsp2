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

# 게시물의 writer 값을 member에 있는 값으로 update
# list를 select 시 게시물 작성자와 멤버 id로 join 하기 때문에
UPDATE board
SET writer = (SELECT id FROM member LIMIT 1)
WHERE id > 0;