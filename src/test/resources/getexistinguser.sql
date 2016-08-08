USE liketestdb;
DELETE FROM `user`;
DELETE FROM location;
INSERT INTO user (id, username, password, timestamp)
        VALUES ("249ba36000029bbe97499c03db5a9001f6b734ec5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", "249ba36000029bbe97499c03db5a9001f6b734ec", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", CURRENT_TIMESTAMP());
