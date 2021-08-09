drop table if exists model CASCADE;
drop sequence if exists global_seq;
create sequence global_seq start with 1 increment by 1;
create table model (id integer not null, value integer, primary key (id));
