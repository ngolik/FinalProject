CREATE DATABASE `movies_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `movies_db`.*
    TO movie_user@localhost
    IDENTIFIED BY 'film_password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `movies_db`.*
    TO movie_user@'%'
    IDENTIFIED BY 'film_password';
