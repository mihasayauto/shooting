create table users (
	id  int noe null auto increment primary key,
	facebook_user_id varchar(30) unique,
	facebook_name varchar(30) unique,
	facebook_picture varchar(255),
	facebook_access_token varcher(255),
	created datetime,
	modified datetime
);
create table post (

);