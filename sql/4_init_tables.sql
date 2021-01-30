INSERT INTO `movies_db`.`users`
(`id`,
 `login`,
 `password`,
 `email`,
 `role`,
 `registrationdate`)
VALUES
(1,
    'admin',
    '$2a$05$iEeRTdIne5umv448A3Wsu.rijKYX0WP/rfCL8MDP64pvsSla/BM2y', /*ХЕШ-пароль bCrypt */
    'adminka@mail.ru',
    'admin',
    2021-01-30);
