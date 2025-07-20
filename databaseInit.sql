create table users
(
	id BIGSERIAL primary key,
	username text,
	password text,
	email text,
	name text,
	surname text,
	address text,
	city text,
	country text,
	verification text,
	authorized boolean,
	followers text[], 
	followed text[]		
);

create type comment as(
	users text,
	content text
);

CREATE TABLE comments (
    post_id BIGINT not null,
    author text not null,
    content text not null,
    foreign key (post_id) references posts(id) on delete cascade
);

create table posts
(
	id BIGSERIAL primary key,
	user_id BIGINT not null,
	owner text,
	likes text[],
	comments comment[],
	imagePath text,
	time timestamp,
	content text,
	location_x real,
	location_y real,
	foreign key (user_id) references users(id) on delete cascade
);

INSERT INTO public.users (username, password, email, name, 
						  surname, address,
						  city, country, verification, authorized)
						  VALUES('user1', 
								 '1234', 
								 'user123454321@gmail.com',
								 'ivan', 
								 'ivanovic',
								  'ulica1', 
								 'novi sad', 
								 'srbija',
								 'asdfcgasiu1Mpd72vdp7',
								 true);
								
INSERT INTO public.users (username, password, email, name, 
						  surname, address,
						  city, country, verification, authorized)
						  VALUES('perica', 
								 '0000', 
								 'perica12342@gmail.com',
								 'pera', 
								 'peric',
								  'ulica2', 
								 'novi sad', 
								 'srbija',
								 '896dhoa67nsy65olsjht',
								 true);
								
INSERT INTO public.users (username, password, email, name, 
						  surname, address,
						  city, country, verification, authorized)
						  VALUES('malialek', 
								 'abcdefgh', 
								 'aca759426@gmail.com',
								 'aca', 
								 'acic',
								  'ulica1', 
								 'novi sad', 
								 'srbija',
								 'mjhgrdcs82ps28hnaja',
								 true);
								
								



INSERT INTO public.posts(user_id, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					2,
					'{}',
					'{}',
					'images/7ytre13avg65SHjk09jymmgr.jpg',
					('2025-07-07 14:00:00'),
					'is a bunny',
					100,
					20					
					);
					
INSERT INTO public.posts(user_id, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					3,
					'{}',
					'{}',
					'images/qweyhdbnflou64fabjd7lm62.jpg',
					('2025-07-07 19:00:00'),
					'bunbun',
					80,
					10					
					);
					
INSERT INTO public.posts(user_id, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					2,
					'{}',
					'{}',
					'images/9tdgb36yhfdki541dsfrgcvt.jpg',
					('2025-06-07 08:22:30'),
					'is another bunny',
					30,
					30					
					);
					
INSERT INTO public.comments (post_id, author, content)
					VALUES (1,
					'malialek',
					'brze jace bolje');