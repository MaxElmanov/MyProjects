--Table users
create table users(
  id       int          not null IDENTITY (1, 1) primary key,
  username varchar(255) not null,
  password varchar(255) not null
)

--Table roles
create table roles(
  id   int          not null IDENTITY (1, 1) primary key,
  name varchar(100) not null
)

--Table user-roles
create table user_roles(
  user_id int not null,
  role_id int not null,

  FOREIGN key (user_id) references users(id),
  foreign key (role_id) references roles(id),

  unique (user_id, role_id)
)

--Insert Data

insert into users(username, password) values('proselyte', '$2a$11$uSXS6rLJ91WjgOHhEGDx..VGs7MkKZV68Lv5r1uwFu7HgtRn3dcXG'); -- id = 1

insert into roles(name) values ('ROLE_USER'), ('ROLE_ADMIN'); -- id = 1, id = 2

INSERT INTO user_roles(user_id, role_id) VALUES (1, 2);