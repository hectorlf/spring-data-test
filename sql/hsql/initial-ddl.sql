-- Sequences
create sequence jpa_seq start with 100;

-- Table: messages
create table messages
(
  id integer not null primary key,
  msg_type integer not null,
  subject character varying(50),
  text character varying(300) not null
);
