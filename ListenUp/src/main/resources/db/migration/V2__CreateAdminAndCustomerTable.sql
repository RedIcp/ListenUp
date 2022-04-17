CREATE TABLE `admin` (
    `id` int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);

CREATE TABLE `customer` (
    `id` int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);