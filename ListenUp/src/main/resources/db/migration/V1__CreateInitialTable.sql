CREATE TABLE `user` (
                        `id` int NOT NULL,
                        `username` varchar(45) NOT NULL,
                        `email` varchar(45) NOT NULL,
                        `password` varchar(45) NOT NULL,
                        PRIMARY KEY (id),
                        UNIQUE (username),
                        UNIQUE (email)
);