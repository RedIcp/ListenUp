CREATE TABLE `album` (
                         `id` int NOT NULL,
                         `name` varchar(45) NOT NULL,
                         `artist_id` int NOT NULL,
                         `released_date` date NOT NULL,
                         `uploaded_date` date NOT NULL,
                         PRIMARY KEY (id),
                         FOREIGN KEY (artist_id) REFERENCES artist (id)
);