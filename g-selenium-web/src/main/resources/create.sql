create table project (id integer primary key not null generated always as identity (start with 1, increment by 1), name varchar(100) not null, url varchar(100) not null,  environment varchar(20) not null, browser varchar(20) not null, parent_project integer references project(id));

create table workflow (id integer primary key generated always as identity (start with 1, increment by 1), name varchar(100) not null, path varchar(100) not null, isIgnorable char(1) not null, isPositive char(1) not null, project_id integer references project(id));