-- unique constraint will automate add index
CREATE TABLE user_role (
  role_id bigint NOT NULL AUTO_INCREMENT,
  role_name varchar(100) NOT NULL,
  role_key varchar(100) NOT NULL UNIQUE,
  role_description varchar(255),
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- insert user role
INSERT INTO user_role (role_id, role_name, role_key, role_description) VALUES (1, 'Admin', 'ADMIN', 'Admin user role');
INSERT INTO user_role (role_id, role_name, role_key, role_description) VALUES (2, 'User', 'USER', 'Normal user role');

-- user permission table
CREATE TABLE user_permission (
  per_id bigint NOT NULL AUTO_INCREMENT,
  per_name varchar(100) NOT NULL,
  per_key varchar(100) NOT NULL UNIQUE,
  per_description varchar(255),
  per_value varchar(100),
  role_id bigint NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (per_id),
  FOREIGN KEY (role_id) REFERENCES user_role(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_permission (per_id, per_name, per_key, per_description, per_value, role_id) VALUES (1, 'View', 'VIEW', 'view info', 'view', 1);


