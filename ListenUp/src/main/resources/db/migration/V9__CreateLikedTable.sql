CREATE TABLE `liked_playlist` (
                                 `customer_id` int NOT NULL AUTO_INCREMENT,
                                 `playlist_id` int NOT NULL,
                                 PRIMARY KEY (customer_id, playlist_id),
                                 FOREIGN KEY (customer_id) REFERENCES customer (id),
                                 FOREIGN KEY (playlist_id) REFERENCES playlist (id)
);

CREATE TABLE `liked_song` (
                             `customer_id` int NOT NULL AUTO_INCREMENT,
                             `song_id` int NOT NULL,
                             PRIMARY KEY (customer_id, song_id),
                             FOREIGN KEY (customer_id) REFERENCES customer (id),
                             FOREIGN KEY (song_id) REFERENCES song (id)
);