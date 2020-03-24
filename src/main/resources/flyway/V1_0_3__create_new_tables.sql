-- unique constraint will automate add index
CREATE TABLE admin_user (
  admin_id bigint NOT NULL AUTO_INCREMENT,
  admin_name varchar(32) NOT NULL UNIQUE,
  password varchar(100) NOT NULL,
  email varchar(100) NOT NULL UNIQUE,
  phone varchar(20) NOT NULL UNIQUE,
  sex int(1),
  age int(3),
  birthday varchar(20),
  role_id bigint NOT NULL,
  is_active boolean NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (admin_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert admin user
INSERT INTO admin_user (admin_name, password, email, phone, role_id, is_active) VALUES ('admin', '{bcrypt}$2a$10$ZmhjSfYRtlIjCEM2nQqDb.XLZKMPeNpKEewdFJld50UsCtFdvoNku', '1055430512@qq.com', '10000000000', 1, TRUE);

-- order table
CREATE TABLE car_order (
  order_id bigint NOT NULL AUTO_INCREMENT,
  order_status int(2) NOT NULL,
  order_source int(2) NOT NULL,
  order_type int(2) NOT NULL,
  admin_id bigint NOT NULL,
  car_type int(2) NOT NULL,
  car_name varchar(50) NOT NULL,
  price varchar(20),
  from_location varchar(20) NOT NULL,
  to_location varchar(20) NOT NULL,
  is_active boolean NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (order_id),
  FOREIGN KEY (admin_id) REFERENCES admin_user(admin_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- location history table
CREATE TABLE location_history (
  loc_id bigint NOT NULL AUTO_INCREMENT,
  order_id bigint NOT NULL,
  location varchar(20) NOT NULL,
  longitude varchar(20),
  latitude varchar(20),
  description varchar(100),
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (loc_id),
  FOREIGN KEY (order_id) REFERENCES car_order(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



