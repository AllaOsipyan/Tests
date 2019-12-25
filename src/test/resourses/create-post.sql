delete from post;

insert into post(id, CREATION_DATE, LIKES, TEXT) values
(1, TO_TIMESTAMP ('19-09-2019 08:27:04.93', 'DD-MM-YYYY HH24:MI:SS.FF'), 2, 'Hello, world!'),
(2, TO_TIMESTAMP ('19-09-2019 08:32:04.769', 'DD-MM-YYYY HH24:MI:SS.FF'), 1, 'News'),
(3, TO_TIMESTAMP ('19-09-2019 09:33:35.098', 'DD-MM-YYYY HH24:MI:SS.FF'), 1, 'Сегодня день рождения смайлика:)'),
(4, TO_TIMESTAMP ('20-09-2019 09:33:35.098', 'DD-MM-YYYY HH24:MI:SS.FF'), 1, 'Test');
