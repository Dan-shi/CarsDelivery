-- unique constraint will automate add index
CREATE TABLE configuration (
  config_id bigint NOT NULL AUTO_INCREMENT,
  config_name varchar(50) NOT NULL,
  description varchar(100) NOT NULL,
  config_key varchar(50),
  config_value varchar(200),
  is_active boolean NOT NULL default 1,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  INDEX(config_key),
  PRIMARY KEY (config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- blog_type
INSERT INTO configuration(config_name, description, config_key, config_value) VALUES ('Email Recipients', '邮件接收人, 以逗号分隔, 没空格', 'EMAIL_RECEIVER', '1055430512@qq.com,284141583@qq.com');
