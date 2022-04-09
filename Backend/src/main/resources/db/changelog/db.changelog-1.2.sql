-- liquibase formatted sql
-- changeset thangct:1.2

create table if not exists tbl_product
(
    id bigserial
    constraint pk_product primary key,
    name varchar(255) not null,
    description text,
    quantity int,
    image varchar(255),
    price decimal,
    category_id serial not null,
    sale_id serial not null,
    created_at timestamp,
    updated_at timestamp,
    is_deleted boolean
);

create table if not exists tbl_category
(
    id bigserial
    constraint pk_category primary key,
    name varchar(255) not null,
    created_at timestamp,
    updated_at timestamp,
    is_deleted boolean
);

create table if not exists tbl_sale
(
    id bigserial
    constraint pk_sale primary key,
    voucher_code varchar(255),
    percentage int,
    starting_at timestamp,
    ending_at timestamp,
    is_active boolean,
    created_at timestamp,
    updated_at timestamp,
    is_deleted boolean
);

alter table tbl_product
add constraint fk_product_category  foreign key (category_id) references tbl_category(id);

alter table tbl_product
add constraint fk_product_sale  foreign key (sale_id) references tbl_sale(id);

INSERT INTO tbl_category(name) VALUES('Giày thể thao');
INSERT INTO tbl_category(name) VALUES('Giày Adidas');
INSERT INTO tbl_category(name) VALUES('Giày Nike');

INSERT INTO tbl_sale(voucher_code,percentage) VALUES('SHOESSALE10',10);
INSERT INTO tbl_sale(voucher_code,percentage) VALUES('SHOESSALE5',5);
INSERT INTO tbl_sale(voucher_code,percentage) VALUES('SHOESSALE15',15);
