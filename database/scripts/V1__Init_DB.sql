CREATE DATABASE IF NOT EXISTS `jwtapp_db`;

CREATE TABLE `jwtapp_db`.`files`
(
    `id`     int         not null,
    `name`   varchar(45) not null,
    `status` varchar(45) not null,
    PRIMARY KEY (`id`)
);


CREATE TABLE jwtapp_db.users
(
    id        int auto_increment,
    username  varchar(100) not null,
    email     varchar(255) not null,
    firstname varchar(255) not null,
    lastname  varchar(255) not null,
    password  varchar(255) not null,
    created   varchar(255)    not null,
    updated   varchar(255)    not null,
    status    varchar(25)  not null,
    role      varchar(25)  not null,
    constraint users_pk
        primary key (id)
);


CREATE TABLE jwtapp_db.events
(
    id      int auto_increment,
    user_id int         not null,
    file_id int         not null,
    created varchar(255)   not null,
    updated varchar(255)   not null,
    status  varchar(25) not null,
    constraint events_pk
        primary key (id),
    constraint events_files_id_fk
        foreign key (file_id) references jwtapp_db.files (id),
    constraint events_users_id_fk
        foreign key (user_id) references jwtapp_db.users (id)
);