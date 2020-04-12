-- unique constraint will automate add index
CREATE TABLE blog (
  blog_id bigint NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  description varchar(200) NOT NULL,
  author varchar(30),
  image_url varchar(100),
  blog_type int(1),
  is_active boolean NOT NULL default 1,
  content text NOT NULL,
  create_time timestamp NOT NULL default current_timestamp,
  update_time timestamp NOT NULL default current_timestamp on update current_timestamp,
  PRIMARY KEY (blog_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- blog_type
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (6, 'Blog Type', 'BLOG_TYPE', '文章类型');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('News', 'NEWS', '新闻', 0, 6);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Case', 'Case', '案例', 1, 6);