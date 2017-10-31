DROP TABLE IF EXISTS orders;
create table orders (
  id int auto_increment
    primary key,
  user_id smallint(6) not null,
  date_time timestamp not null,
  total_sum int(11) not null,
  status enum('CREATED','CONFIRMED','READY','PAID') not null
)
;