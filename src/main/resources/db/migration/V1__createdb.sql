CREATE TABLE `user` (
    id varchar(255) NOT null,
    username varchar(255) NOT null,
    password varchar(255) NOT null,
    `timestamp` datetime NOT NULL,
    PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE location (
    id bigint NOT null AUTO_INCREMENT,
    journey_id bigint NOT null,
    sub_journey_id bigint NOT null,
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL,
    speed int NOT NULL,
    heading int NOT NULL,
    activity_type varchar(255) NULL,
    activity_confidence int NULL,
    activity_type_pt varchar(255) NULL,
    user_id varchar(255) NOT NULL,
    `timestamp` datetime NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT `fk_location_user`
        FOREIGN KEY (user_id) REFERENCES `user` (id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;