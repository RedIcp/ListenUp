CREATE TABLE `admin` (
    `id` int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);

CREATE TABLE `customer` (
    `id` int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    FOREIGN KEY (id) REFERENCES user (id)
);