USE liketestdb;
DELETE FROM `user`;
DELETE FROM location;
INSERT INTO user (id, username, password, timestamp)
        VALUES ("userid", "username", "password", CURRENT_TIMESTAMP());
