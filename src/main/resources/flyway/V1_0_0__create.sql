
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(100),
  `phone` int(20) NOT NULL,
  `sex` int(1),
  `age` int(3),
  `birthday` date,
  `user_type` int(2) NOT NULL,
  `create_time` timestamp NOT NULL default current_timestamp,
  `update_time` timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert admin user

INSERT INTO user (user_name, password, phone, user_type) VALUES ('admin', '{bcrypt}$2a$10$ZmhjSfYRtlIjCEM2nQqDb.XLZKMPeNpKEewdFJld50UsCtFdvoNku', '00000000000', '0');