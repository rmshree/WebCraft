INSERT INTO webcraft.user (`id`, `firstName`, `lastName`, `username`, `email`, `win`, `loss`, `avatar_url`, `password`, `elo` )
VALUES
('1', 'Root', 'TestRoot', 'root', 'root@root', 0, 0, 'http://gdpit.com/avatars_pictures/img2852/gdpit_com_41490242_66.gif','12345', 1000),
('2', 'Jaina','Proudmoore' ,'jaina', 'neanguyen@ucdavis.edu', 5, 0, 'https://lh4.googleusercontent.com/-e0zkRSI70gc/UnQtL4Y81dI/AAAAAAAAABA/WxC9Q9VgBl8/w1021-h1019/world-of-warcraft.png','12345', 1000),
('3', 'Go', 'el', 'thrall', 'nguyenneric@yahoo.com', 20, 0, 'https://hydra-media.cursecdn.com/wow.gamepedia.com/thumb/5/50/DefenderNagalaas.jpg/300px-DefenderNagalaas.jpg?version=f428bf7022ea9d8e981f85ba39c0b6c3','12345', 2400),
('4', 'Medivh', 'Magus','medivh', 'dumdum@yahoo.com', 0, 5, 'https://s-media-cache-ak0.pinimg.com/originals/1a/7f/b4/1a7fb432753cb4be6389ee6a99a445e3.jpg','12345', 700),
('5', 'Eric', 'Nguyen','noob',  'dummy@yahoo.com', 0, 20, 'https://pp.vk.me/c418521/v418521230/2f7b/NNcmKw5XfJc.jpg', '12345', 20);

INSERT INTO webcraft.password (`id`, `user_id`, `password`)
VALUES
(1, '1','12345'),
(2, '2','12345'),
(3, '3','12345'),
(4, '4','12345'),
(5, '5','12345');

INSERT INTO webcraft.settings (`id`, `notifications`, `delay`, `delay_unit`, `user_id`)
VALUES
(1, 1, 1, 'Minutes', 1),
(2, 1, 3, 'Days', 2),
(3, 1, 1, 'Minutes', 3),
(4, 1, 3, 'Days', 4),
(5, 1, 3, 'Days', 5);