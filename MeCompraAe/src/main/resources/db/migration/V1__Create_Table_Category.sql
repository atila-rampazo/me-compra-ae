create table categories
(
    id         bigint(20) NOT NULL AUTO_INCREMENT,
    name       varchar(80)  not null,
    status     boolean   default true  null,
    created_at timestamp default now() null,
    updated_at timestamp default now() not null,
    PRIMARY KEY (`id`)
);