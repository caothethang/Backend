-- liquibase formatted sql
-- changeset thangct:1.1

create table if not exists "user"
(
    id bigserial
    constraint pk_user primary key,
    user_name varchar(255) not null,
    password varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    create_by varchar(255),
    modified_by varchar(255),
    is_deleted boolean
);

create table if not exists "role"
(
    id bigserial
    constraint pk_role primary key,
    name varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    create_by varchar(255),
    modified_by varchar(255),
    is_deleted boolean
);

create table if not exists "user_role"
(
    id bigserial
    constraint pk_user_role primary key,
    user_id serial not null
    constraint user_role_user_id_fk
    references "user",
    role_id serial not null
    constraint user_role_role_id_fk
    references "role",
    created_at timestamp,
    updated_at timestamp,
    create_by varchar(255),
    modified_by varchar(255),
    is_deleted boolean
    );

INSERT INTO "role"(name) VALUES('ROLE_USER');
INSERT INTO "role"(name) VALUES('ROLE_ADMIN');
