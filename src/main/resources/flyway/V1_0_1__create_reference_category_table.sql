-- unique constraint will automate add index
CREATE TABLE reference_category (
  category_id bigint NOT NULL AUTO_INCREMENT,
  category_name varchar(100) NOT NULL,
  category_key varchar(100) NOT NULL UNIQUE,
  category_description varchar(255),
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- reference value table
CREATE TABLE reference_xref (
  ref_id bigint NOT NULL AUTO_INCREMENT,
  ref_name varchar(100) NOT NULL,
  ref_key varchar(100) NOT NULL UNIQUE,
  ref_description varchar(255),
  ref_value INTEGER NOT NULL,
  category_id bigint NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (ref_id),
  FOREIGN KEY (category_id) REFERENCES reference_category(category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;