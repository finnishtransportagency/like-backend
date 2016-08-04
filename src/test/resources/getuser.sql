USE liketestdb;
DELETE FROM `user`;
DELETE FROM location;
DELETE FROM activity;
INSERT INTO user (id, username, password, timestamp)
        VALUES ("userid", "username", "password", CURRENT_TIMESTAMP());
