-- unique constraint will automate add index
CREATE TABLE user (
  user_id bigint NOT NULL AUTO_INCREMENT,
  user_name varchar(32) NOT NULL UNIQUE ,
  password varchar(100),
  phone varchar(20) NOT NULL UNIQUE,
  sex int(1),
  age int(3),
  birthday varchar(20),
  role_id bigint NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert admin user
INSERT INTO user (user_name, password, phone, role_id) VALUES ('user', '{bcrypt}$2a$10$ZmhjSfYRtlIjCEM2nQqDb.XLZKMPeNpKEewdFJld50UsCtFdvoNku', '10000000000', 1);