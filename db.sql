create table if not exists netology.user
(
    id       int auto_increment
        primary key,
    username varchar(300) null,
    password text         null
);

create table if not exists netology.tfile
(
    id          int auto_increment
        primary key,
    filename    varchar(255) not null,
    create_date datetime     null,
    size        bigint       null,
    file        longblob     null,
    user_id     int          null,
    constraint fuser_id
        foreign key (user_id) references netology.user (id)
);