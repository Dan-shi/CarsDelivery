-- unique constraint will automate add index
CREATE TABLE blog (
  blog_id bigint NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  description varchar(200) NOT NULL,
  author varchar(30),
  image_url varchar(100),
  blog_type int(1) NOT NULL,
  is_active boolean NOT NULL default 1,
  content text NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  INDEX(blog_type, is_active),
  PRIMARY KEY (blog_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- blog_type
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (7, 'Blog Type', 'BLOG_TYPE', '文章类型');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('News', 'NEWS', '必读', 0, 7);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Case', 'Case', '案例', 1, 7);
