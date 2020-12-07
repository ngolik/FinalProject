CREATE DATABASE `Films` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `Films`.*
    TO film_user@localhost
    IDENTIFIED BY 'film_password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `Films`.*
    TO film_user@'%'
    IDENTIFIED BY 'film_password';