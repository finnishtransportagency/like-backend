CREATE TABLE `user` (
    id varchar(255) NOT null,
    username varchar(255) NOT null,
    password varchar(255) NOT null,
    `timestamp` datetime NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE location (
    id bigint NOT null AUTO_INCREMENT,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    speed int NOT NULL,
    heading int NOT NULL,
    user_id varchar(255) NOT NULL,
    `timestamp` datetime NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT `fk_location_user`
        FOREIGN KEY (user_id) REFERENCES `user` (id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE activity (
    id bigint NOT null AUTO_INCREMENT,
    `type` varchar(255) NOT NULL,
    confidence int NULL,
    user_id varchar(255) NOT NULL,
    location_id bigint NOT NULL,
    `timestamp` datetime NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT `fk_activity_user`
        FOREIGN KEY (user_id) REFERENCES `user` (id)
        ON DELETE CASCADE,
    CONSTRAINT `fk_activity_location`
        FOREIGN KEY (location_id) REFERENCES location (id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
