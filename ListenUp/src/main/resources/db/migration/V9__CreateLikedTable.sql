CREATE TABLE `liked_playlist` (
                                 `id` int NOT NULL,
                                 `customer_id` int NOT NULL,
                                 `playlist_id` int NOT NULL,
                                 PRIMARY KEY (id),
                                 FOREIGN KEY (customer_id) REFERENCES customer (id),
                                 FOREIGN KEY (playlist_id) REFERENCES playlist (id)
);

CREATE TABLE `liked_song` (
                             `id` int NOT NULL,
                             `customer_id` int NOT NULL,
                             `song_id` int NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (customer_id) REFERENCES customer (id),
                             FOREIGN KEY (song_id) REFERENCES song (id)
);