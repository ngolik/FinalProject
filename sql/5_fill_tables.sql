INSERT INTO `genres`
    (`id`,       `name`)
VALUES
    (1, 'Comedy'),
    (2, 'Action'),
    (3, 'Documentary'),
    (4, 'Drama'),
    (5, 'Family'),
    (6, 'Horror'),
    (7, 'Adventure'),
    (8, 'Thriller'),
    (9, 'Musical'),
    (10, 'Cartoon'),
    (11, 'Detective'),
    (12, 'Western');

INSERT INTO participants
    (id,      name,           surname,         secondname)
    VALUES
    (1, 'Thomas',         'Hanks',           'Jeffrey'),
    (2, 'Morgan',         'Freeman',         ' '),
    (3, 'Tim',            'Robbins',         ' '),
    (4, 'Elijah',         'Wood',            'Jordan'),
    (5, 'Matthew',        'McConaughey',     ' '),
    (6, 'Christopher',    'Nolan',           ' ');
INSERT INTO `movies_db`.`movies`
(`id`,
 `title`,
 `year`,
 `runtime`,
 `budget`,
 `gross`)
VALUES
(1, "The Green Mile", 1999, 189, 60000000, 286801374),
(2, "The Shawshank Redemption", 1994, 142, 25000000, 28418687),
(3, "Interstellar", 2014,  169, 165000000, 677463813),
(4, "The Lord of the Rings: The Return of the King", 2003, 201, 94000000, 1118887224);

INSERT INTO `movies_db`.`users`
(`id`,
 `login`,
 `password`,
 `email`,
 `role`,
 `registrationdate`)
VALUES
(1,
    'berry',
    '$2a$05$3k1pBYhytMKyvSXqiJBN5OVvCvCnAmswSqX6ObrhqtrwLNURff3xC',
    'berry@mail.ru',
    'user',
    2021-01-30);


INSERT INTO movies_genres
    (movies_id, genres_id)
    VALUES
    (1, 5),
    (1, 3),
    (2, 5),
    (3, 5),
    (3, 9),
    (4, 2);
INSERT INTO movies_participants
    (movies_id, participants_id, position)
    VALUES
    (1, 1, 3),
    (2, 2, 3),
    (3,5, 3),
    (3, 6, 1),
    (4, 4, 3);
INSERT INTO users
    (id,
     login,
     password,
     email,
     role,
     registrationdate)
    VALUES (
            2,
            "moder",
            "EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "moder" */
            "moder@tut.by",
             1,
             2020
            ),
           (
            3,
            "user1",
            "EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user1" */
            "user1@tut.by",
            2,
            2020
           );
INSERT INTO votes
    (score, date, users_id, movies_id)
    VALUES (

           );
INSERT INTO movies_users
    (users_id, date, movies_id)
    VALUES(

          );






