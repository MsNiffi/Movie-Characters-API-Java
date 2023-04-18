-- noinspection SqlNoDataSourceInspectionForFile

-- noinspection SqlDialectInspectionForFile
INSERT INTO franchise(name, description) VALUES
('Marvel Cinematic Universe', 'An American media franchise and shared universe centered on a series of superhero films produced by Marvel Studios.'),
('Lord of the Rings', 'A media franchise depicting works of J.R.R. Tolkien and his epic high-fantasy novels');

INSERT INTO movie(title, director, genre, release_year, franchise_id, trailer, picture) VALUES
('Iron Man', 'Jon Favreau', 'Action Adventure Sci-Fi',2008, 1,'https://www.youtube.com/watch?v=8ugaeA-nMTc&ab_channel=RottenTomatoesClassicTrailers','https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_FMjpg_UX1000_.jpg'),
('Captain America: The First Avenger', 'Joe Johnston', 'Action Adventure Sci-Fi',2011, 1, 'https://www.youtube.com/watch?v=JerVrbLldXw&ab_channel=MarvelEntertainment', 'https://m.media-amazon.com/images/M/MV5BMTYzOTc2NzU3N15BMl5BanBnXkFtZTcwNjY3MDE3NQ@@._V1_FMjpg_UX1000_.jpg'),
('Avengers: Endgame', 'Anthony Russo Joe Russo', 'Action Adventure Drama', 2019, 1, 'https://www.youtube.com/watch?v=TcMBFSGVi1c&ab_channel=MarvelEntertainment', 'https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_.jpg'),
('The Lord of the Rings: The Fellowship of the Ring', 'Peter Jackson', 'Action Adventure Drama',2001,2,'https://www.youtube.com/watch?v=V75dMMIW2B4&ab_channel=Movieclips', 'https://m.media-amazon.com/images/M/MV5BN2EyZjM3NzUtNWUzMi00MTgxLWI0NTctMzY4M2VlOTdjZWRiXkEyXkFqcGdeQXVyNDUzOTQ5MjY@._V1_FMjpg_UX1000_.jpg');

INSERT INTO character(alias, full_name, gender, picture) VALUES
('Iron Man', 'Tony Stark', 'MALE', 'https://m.media-amazon.com/images/M/MV5BMTI5ODY5NTUzMF5BMl5BanBnXkFtZTcwOTAzNTIzMw@@._V1_.jpg'),
('Captain America', 'Steve Rogers', 'MALE', 'https://cdn.vox-cdn.com/thumbor/XHxYk_GxBk_XhfAz95NfC-l3czI=/1400x1050/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/10857121/so_meme.jpg'),
('Thor', 'Thor Odinson', 'MALE', 'https://cdn.mos.cms.futurecdn.net/rohSW6fcL4hcAVujAVFaxX.jpg'),
('Scarlett Witch', 'Wanda Maximoff', 'FEMALE', 'https://static.wikia.nocookie.net/marvelcinematicuniverse/images/9/97/Scarlet_Witch.jpg/revision/latest?cb=20220501012641'),
('Spiderman', 'Peter Parker', 'MALE', 'https://i.guim.co.uk/img/media/9fef7d73f3df638b16318a135ec3459932437fb5/0_480_2402_1441/master/2402.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=753f76848a85db0f7e081df8bc266fe7'),
('Legolas', 'Legolas Greenleaf', 'MALE','https://static.wikia.nocookie.net/lotr/images/3/33/Legolas_-_in_Two_Towers.PNG/revision/latest?cb=20120916035151'),
('Strider', 'Aragorn II Elessar', 'MALE', 'https://static.wikia.nocookie.net/lotr/images/b/b6/Aragorn_profile.jpg/revision/latest?cb=20170121121423'),
('Captain of the White Tower', 'Boromir', 'MALE', 'https://static.wikia.nocookie.net/lotr/images/b/b4/Seanbean_boromir.jpg/revision/latest?cb=20110327195115'),
('Frodo', 'Frodo Baggins', 'MALE','https://static.wikia.nocookie.net/middle-earth-film-saga/images/7/71/Frodo_two_towers.png/revision/latest?cb=20160206224102'),
('Arwen', 'Arwen Undomiel', 'FEMALE','https://static.wikia.nocookie.net/lotr/images/6/64/Arwen_-_The_Fellowship_Of_The_Ring.jpg/revision/latest?cb=20210625164207'),
('Gimli', 'Gimli', 'MALE','https://static.wikia.nocookie.net/lotr/images/e/ec/Gimli_-_FOTR.png/revision/latest?cb=20121008105956'),
('Gandalf', 'Gandalf the Grey/White', 'MALE','https://i1.sndcdn.com/avatars-2AI10Rs3s1EneWOl-317StQ-t500x500.jpg'),
('Gollum', 'Smeagol', 'MALE','https://s26162.pcdn.co/wp-content/uploads/2019/11/gollum.jpg');

INSERT INTO character_movies(movie_id, character_id) VALUES
(1,1),
(2,2),
(3,1),
(3,2),
(3,3),
(3,4),
(3,5),
(4,6),
(4,7),
(4,8),
(4,9),
(4,10),
(4,11),
(4,12);

