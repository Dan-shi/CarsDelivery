
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL,
  `password` varchar(100),
  `phone` int(20) NOT NULL,
  `sex` int(1),
  `age` int(3),
  `birthday` date,
  `user_type` int(2) NOT NULL,
  `create_time` date NOT NULL,
  `update_time` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert admin user

INSERT INTO user (user_name, password, phone, user_type, create_time, update_time) VALUES ('admin', '{bcrypt}$2a$10$ZmhjSfYRtlIjCEM2nQqDb.XLZKMPeNpKEewdFJld50UsCtFdvoNku', '00000000000', '0', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());