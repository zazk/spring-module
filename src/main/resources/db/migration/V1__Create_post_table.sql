create table t_categoria (
    id SERIAL,
    title varchar(100) not null,
    fecha TIMESTAMP DEFAULT NOW()
);

insert into t_categoria (title, fecha)
  values 
  ('The Hitchhikers Guide to the Galaxy', '2018-05-12 14:00')
  ,('I was living in Namekusein', '2018-05-12 21:00')
  ,('Go off weirdo', '2018-05-14 13:00')
  ,('Where we go', '2018-05-11 10:00')
  ;