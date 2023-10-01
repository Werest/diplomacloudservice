create table if not exists tfile
(
    id          int auto_increment,
    filename    varchar(255) null,
    create_date datetime     null,
    size        bigint       null,
    file        blob         null,
    constraint tfile_pk
        primary key (id)
);

create table if not exists user
(
    id       int auto_increment,
    username varchar(300) null,
    password text         null,
    constraint user_pk
        primary key (id)
);

