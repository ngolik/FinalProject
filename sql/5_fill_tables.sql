INSERT INTO `genres`
    (`id`,       `name`)
VALUES
    (1, "Comedy"),
    (2, "Action movie"),
    (3, "sci-fi"),
    (4, "documentary"),
    (5, "drama"),
    (6, "entertainment"),
    (7, "family movie"),
    (8, "horror movie"),
    (9, "feature film"),
    (10, "short film"),
    (11, "silent film"),
    (12, "adventure film"),
    (13, "disaster film"),
    (14, "thriller"),
    (15, "musical"),
    (16, "cartoon"),
    (17, "gangster film"),
    (18, "detective film"),
    (19, "western"),
    (20, "buddy movie"),
    (21, "biopic"),
    (22, "a tear jerker"),
    (23, "space film"),
    (24, "war film"),
    (25, "opera film"),
    (26, "a B-movie"),
    (27, "film noir");

INSERT INTO participants
    (id,      name,           surname,         secondname)
    VALUES
    (1, "Thomas",         "Hanks",           "Jeffrey"),
    (2, "Morgan",         "Freeman",         " "),
    (3, "Tim",            "Robbins",         " "),
    (4, "Elijah",         "Wood",            "Jordan"),
    (5, "Matthew",        "McConaughey",     " "),
    (6, "Christopher",    "Nolan",           " ");

INSERT INTO movies
    (id, title, year, image_path, runtime, budget, gross, description)
    VALUES
    (1, "The Green Mile", 1999, ,189, 60000000, 286801374, "Mystical drama based on the novel by Stephen King"),
    (2, "The Shawshank Redemption", 1994, , 142, 25000000, 28418687, "Accountant Andy Dufrein is charged with the murder of"),
    (3, "Interstellar", 2014, , 169, 165000000, 677463813, "safsadfas"),
    (4, "The Lord of the Rings: The Return of the King", 2003, , 201, 94000000, 1118887224, "The last part of the trilogy about the Ring of Omnipotence");
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






