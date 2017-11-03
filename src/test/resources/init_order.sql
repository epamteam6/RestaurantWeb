DROP TABLE IF EXISTS orders;
create table orders (
  id bigint auto_increment
    primary key,
  user_id bigint(6) not null,
  date_time timestamp not null,
  total_sum bigint(11) not null,
  status enum('CREATED','CONFIRMED','READY','PAID') not null
)
;