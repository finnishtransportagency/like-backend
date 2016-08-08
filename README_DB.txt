-- ##### Installation Instructions Date: 02.08.2016 #####

-- ### Database setup, production ###
-- 1. Create database in MariaDb
    CREATE DATABASE likedb;
    USE likedb;
-- 2. Add user to access database
    CREATE USER 'likeuser' IDENTIFIED BY 'likepwd';
-- 3. Give rights to user in database created in step one: create, drop, select, insert, update
    GRANT ALL ON likedb.* TO 'likeuser'@'%';
-- 4. If database is located on different server than application is run, change database parameters in properties files:
--    - ./src/main/resources/application.properties

### Database setup, tests ###
-- 1. Create database in MariaDb
    CREATE DATABASE liketestdb;
    USE liketestdb;
-- 2. Add user to access database (if not created for production already)
    CREATE USER 'liketestuser' IDENTIFIED BY 'liketestpwd';
-- 3. Give rights to user in database created in step one: create, drop, select, insert, update
    GRANT ALL ON liketestdb.* TO 'liketestuser'@'%';
-- 4. If database is located on different server than application is run, change database parameters in properties files:
--    - ./src/test/resources/application.properties