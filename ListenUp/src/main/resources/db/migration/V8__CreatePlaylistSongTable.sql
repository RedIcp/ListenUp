CREATE TABLE `playlist_song` (
                                `playlist_id` int NOT NULL,
                                `song_id` int NOT NULL,
                                PRIMARY KEY (playlist_id, song_id),
                                FOREIGN KEY (playlist_id) REFERENCES playlist (id),
                                FOREIGN KEY (song_id) REFERENCES song (id)
);