create table orders_status (
    id serial primary key not null,
    order_id int not null REFERENCES orders(id),
    creation_date timestamp,
    status text
);