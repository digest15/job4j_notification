CREATE TABLE orders_dishes (
   id SERIAL PRIMARY KEY,
   order_id int not null REFERENCES orders(id),
   dish_name text not null
);