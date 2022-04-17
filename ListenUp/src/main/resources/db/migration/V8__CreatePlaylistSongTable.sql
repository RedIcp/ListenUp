CREATE TABLE `playlist_song` (
                                `id` int NOT NULL,
                                `playlist_id` int NOT NULL,
                                `song_id` int NOT NULL,
                                PRIMARY KEY (id),
                                FOREIGN KEY (playlist_id) REFERENCES playlist (id),
                                FOREIGN KEY (song_id) REFERENCES song (id)
);