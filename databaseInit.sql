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
						  city, country, verification, authorized, followers, followed)
						  VALUES('user1', 
								 '1234', 
								 'user123454321@gmail.com',
								 'ivan', 
								 'ivanovic',
								  'ulica1', 
								 'novi sad', 
								 'srbija',
								 'asdfcgasiu1Mpd72vdp7',
								 true,
								'{}',
								'{}');
								
INSERT INTO public.users (username, password, email, name, 
						  surname, address,
						  city, country, verification, authorized, followers, followed)
						  VALUES('perica', 
								 '0000', 
								 'perica12342@gmail.com',
								 'pera', 
								 'peric',
								  'ulica2', 
								 'novi sad', 
								 'srbija',
								 '896dhoa67nsy65olsjht',
								 true,
								'{}',
								'{}');
								
INSERT INTO public.users (username, password, email, name, 
						  surname, address,
						  city, country, verification, authorized, followers, followed)
						  VALUES('malialek', 
								 'abcdefgh', 
								 'aca759426@gmail.com',
								 'aca', 
								 'acic',
								  'ulica1', 
								 'novi sad', 
								 'srbija',
								 'mjhgrdcs82ps28hnaja',
								 true,
								'{}',
								'{}');
								
								


INSERT INTO public.posts(owner, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					(SELECT id FROM public.users WHERE username = 'perica'),
					null,
					null,
					'images/7ytre13avg65SHjk09jymmgr',
					('2024-11-09 14:35:00'),
					'is a bunny',
					100,
					20					
					);
					
INSERT INTO public.posts(owner, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					(SELECT id FROM public.users WHERE username = 'malialek')
					null,
					null,
					'images/qweyhdbnflou64fabjd7lm62',
					('2024-11-09 14:35:00'),
					'bunbun',
					80,
					10					
					);
					
INSERT INTO public.posts(owner, likes, comments, imagePath, time, content, location_x, location_y)
					VALUES(
					(SELECT id FROM public.users WHERE username = 'perica')
					null,
					null,
					'images/9tdgb36yhfdki541dsfrgcvt',
					('2024-11-11 08:22:30'),
					'is another bunny',
					30,
					30					
					);