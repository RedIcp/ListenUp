CREATE TABLE product(
                        id   int     NOT NULL AUTO_INCREMENT,
                        title char(50) NOT NULL,
                        sub_title varchar(50),
                        series varchar(30),
                        year int,
                        price double,
                        condition_ enum('TRASH','VERY_BAD','BAD','NOT_BAD','OK','GOOD','GREAT','EXCELLENT'),
                        description varchar(500),
                        genre enum('BOARD_GAME'),
                        sold boolean,
                        product_type enum('GAME', 'GAMES'),
                        PRIMARY KEY (id)
);