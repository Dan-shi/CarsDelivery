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
  is_active boolean NOT NULL default 1,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (user_id),
  FOREIGN KEY (role_id) REFERENCES user_role(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert admin user
INSERT INTO user (user_name, password, phone, role_id) VALUES ('admin', '{bcrypt}$2a$10$qec1F01NmZyFAKNQCkeJDeHdeiBd9oCkkfKYokIPSbp7U6gsB5yGe', '10000000000', 1);
INSERT INTO user (user_name, password, phone, role_id) VALUES ('user', '{bcrypt}$2a$10$Stp7SNyb3lD50/Wwn5bC/ezZwzNDJBqB2EHTBT9X9HLVdLW5zOFme', '10000000001', 2);
INSERT INTO user (user_name, password, phone, role_id) VALUES ('tourist', '{bcrypt}$2a$10$OwlL1LkoZPqDi6URJqkm9eWj1zM6Ervtt.CyZl3SDJ9rG0PKfM7bC', '10000000002', 3);