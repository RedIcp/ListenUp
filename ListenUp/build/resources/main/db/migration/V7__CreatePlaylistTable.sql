CREATE TABLE `playlist` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(45) NOT NULL,
                            `customer_id` int NOT NULL,
                            `is_public` boolean NOT NULL ,
                            `liked_users` int NOT NULL ,
                            PRIMARY KEY (id),
                            FOREIGN KEY (customer_id) REFERENCES customer (id)
);