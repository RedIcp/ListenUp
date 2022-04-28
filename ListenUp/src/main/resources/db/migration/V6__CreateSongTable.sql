CREATE TABLE `song` (
                        `id` int NOT NULL,
                        `name` varchar(50) NOT NULL,
                        `genre_id` int NOT NULL,
                        `artist_id` int NOT NULL,
                        `released_date` date NOT NULL,
                        `uploaded_date` date NOT NULL,
                        PRIMARY KEY (id),
                        FOREIGN KEY (artist_id) REFERENCES artist (id),
                        FOREIGN KEY (genre_id) REFERENCES genre (id)
);
CREATE TABLE `single_song` (
                              `id` int NOT NULL,
                              PRIMARY KEY (id),
                              FOREIGN KEY (id) REFERENCES song (id)
);
CREATE TABLE `album_song` (
                             `id` int NOT NULL,
                             `album_id` int NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (id) REFERENCES song (id)
);
CREATE TABLE `featured_artist` (
                                   `song_id` int NOT NULL,
                                   `artist_id` int NOT NULL,
                                   PRIMARY KEY (song_id, artist_id),
                                   FOREIGN KEY (song_id) REFERENCES song (id),
                                   FOREIGN KEY (artist_id) REFERENCES artist (id)
);