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
INSERT INTO user_role (role_id, role_name, role_key, role_description) VALUES (3, 'Tourist', 'TOURIST', 'Tourist user role');

-- user permission table
CREATE TABLE user_permission (
  per_id bigint NOT NULL AUTO_INCREMENT,
  per_name varchar(100) NOT NULL,
  per_key varchar(100) NOT NULL UNIQUE,
  per_description varchar(255),
  per_value varchar(100),
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (per_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO user_permission (per_id, per_name, per_key, per_description, per_value) VALUES (1, 'View', 'VIEW', 'view info', 'view');
INSERT INTO user_permission (per_id, per_name, per_key, per_description, per_value) VALUES (2, 'Admin', 'ADMIN', 'Admin', 'Admin');
INSERT INTO user_permission (per_id, per_name, per_key, per_description, per_value) VALUES (3, 'Search', 'SEARCH', 'Search', 'Search');
INSERT INTO user_permission (per_id, per_name, per_key, per_description, per_value) VALUES (4, 'Create Order', 'CREATE_ORDER', 'Create Order', 'Create Order');
INSERT INTO user_permission (per_id, per_name, per_key, per_description, per_value) VALUES (5, 'User', 'USER', 'Could get own user info and register', 'User');

-- user permission table
CREATE TABLE role_permission_xref (
  id bigint NOT NULL AUTO_INCREMENT,
  role_id bigint NOT NULL,
  per_id bigint NOT NULL,
  UNIQUE(role_id,per_id),
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES user_role(role_id),
  FOREIGN KEY (per_id) REFERENCES user_permission(per_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (1, 1, 1);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (2, 1, 2);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (3, 1, 3);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (4, 1, 4);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (5, 2, 1);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (6, 2, 3);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (7, 2, 4);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (8, 3, 1);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (9, 3, 4);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (10, 1, 5);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (11, 2, 5);
INSERT INTO role_permission_xref (id, role_id, per_id) VALUES (12, 3, 5);


