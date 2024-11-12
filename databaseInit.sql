create table users
(
	id bigint primary key,
	username text,
	password text,
	email text,
	name text,
	surname text,
	address text,
	city text,
	country text,
	verification text,
	followers text[], 
	followed text[]		
)

INSERT INTO public.users (id, username, password, email, name, 
						  surname, address,
						  city, country, verification, followers, followed)
						  VALUES(default,
								 'user1', 
								 '1234', 
								 'user123454321@gmail.com',
								 'ivan', 
								 'ivanovic',
								  'ulica1', 
								 'novi sad', 
								 'srbija',
								 'asdfcgasiu1Mpd72vdp7',
								null,
								null);
								
INSERT INTO public.users (id, username, password, email, name, 
						  surname, address,
						  city, country, verification, followers, followed)
						  VALUES(default,
								 'perica', 
								 '0000', 
								 'perica12342@gmail.com',
								 'pera', 
								 'peric',
								  'ulica2', 
								 'novi sad', 
								 'srbija',
								 '896dhoa67nsy65olsjht',
								null,
								null);
								
INSERT INTO public.users (id, username, password, email, name, 
						  surname, address,
						  city, country, verification, followers, followed)
						  VALUES(default,
								 'malialek', 
								 'abcdefgh', 
								 'aca759426@gmail.com',
								 'aca', 
								 'acic',
								  'ulica1', 
								 'novi sad', 
								 'srbija',
								 'mjhgrdcs82ps28hnaja',
								null,
								null);
								
								
create type comment as(
	users text,
	content text
);

create table posts
(
	id bigint primary key,
	owner text,
	likes text[],
	comments comment[],
	imagePath text,
	time timestamp,
	content text,
	location_x real,
	location_y real	
)


INSERT INTO public.posts(id, owner, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					default,
					'perica',
					null,
					null,
					'images/7ytre13avg65SHjk09jymmgr',
					('2024-11-09 14:35:00'),
					'is a bunny',
					100,
					20					
					);
					
INSERT INTO public.posts(id, owner, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					default,
					'malialek',
					null,
					null,
					'images/qweyhdbnflou64fabjd7lm62',
					('2024-11-09 14:35:00'),
					'bunbun',
					80,
					10					
					);
					
INSERT INTO public.posts(id, owner, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					default,
					'perica',
					null,
					null,
					'images/9tdgb36yhfdki541dsfrgcvt',
					('2024-11-11 08:22:30'),
					'is another bunny',
					30,
					30					
					);