USE `Films`;

CREATE TABLE `users` (
                         `identity` INTEGER NOT NULL AUTO_INCREMENT,
                         `login` VARCHAR(255) NOT NULL UNIQUE,
                         `password` CHAR(32) NOT NULL,
    /*
     * 0 - администратор (Role.ADMINISTRATOR)
     * 1 - пользователь (Role.USER)
     * 2 - гость (Role.GUEST)
     */
                         `role` TINYINT NOT NULL CHECK (`role` IN (0, 1, 2)),
                         PRIMARY KEY (`identity`)
) ENGINE=INNODB DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `Films`.`Actor`
(
    `actor_id`      INT         NOT NULL AUTO_INCREMENT,
    `actor_name`    VARCHAR(45) NOT NULL,
    `actor_surname` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`actor_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`genres`
(
    `genre_id`   INT         NOT NULL AUTO_INCREMENT,
    `genre_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`genre_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`countries`
(
    `country_code` INT         NOT NULL,
    `country_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`country_code`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`rating`
(
    `score` INT NOT NULL
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`movies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`movies`
(
    `movie_id`    INT          NOT NULL AUTO_INCREMENT,
    `title`       VARCHAR(45)  NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`movie_id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`movie_genre`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`movie_genre`
(
    `movie_id` INT NOT NULL,
    `genre_id` INT NOT NULL,
    INDEX `movies_idx` (`movie_id` ASC) VISIBLE,
    INDEX `genre_idx` (`genre_id` ASC) VISIBLE,
    CONSTRAINT `movies`
        FOREIGN KEY (`movie_id`)
            REFERENCES `Films`.`movies` (`movie_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `genre`
        FOREIGN KEY (`genre_id`)
            REFERENCES `Films`.`genres` (`genre_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`movie_country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`movie_country`
(
    `movie_id`     INT NOT NULL,
    `country_code` INT NOT NULL,
    INDEX `movies_idx` (`movie_id` ASC) VISIBLE,
    INDEX `countries_idx` (`country_code` ASC) VISIBLE,
    CONSTRAINT `movies`
        FOREIGN KEY (`movie_id`)
            REFERENCES `Films`.`movies` (`movie_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `countries`
        FOREIGN KEY (`country_code`)
            REFERENCES `Films`.`countries` (`country_code`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`movie_actor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`movie_actor`
(
    `movie_id` INT NOT NULL,
    `actor_id` INT NOT NULL,
    INDEX `movies_idx` (`movie_id` ASC) VISIBLE,
    INDEX `actor_idx` (`actor_id` ASC) VISIBLE,
    CONSTRAINT `movies`
        FOREIGN KEY (`movie_id`)
            REFERENCES `Films`.`movies` (`movie_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `actor`
        FOREIGN KEY (`actor_id`)
            REFERENCES `Films`.`Actor` (`actor_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Films`.`movie_rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Films`.`movie_rating`
(
    `movie_id` INT NOT NULL,
    `score`    INT NOT NULL,
    INDEX `movies_idx` (`movie_id` ASC) VISIBLE,
    CONSTRAINT `movies`
        FOREIGN KEY (`movie_id`)
            REFERENCES `Films`.`movies` (`movie_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `score`
        FOREIGN KEY ()
            REFERENCES `Films`.`rating` ()
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;