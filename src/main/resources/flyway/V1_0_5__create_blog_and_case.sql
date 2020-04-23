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
  PRIMARY KEY (blog_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- blog_type
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (7, 'Blog Type', 'BLOG_TYPE', '文章类型');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('News', 'NEWS', '新闻', 0, 7);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Case', 'Case', '案例', 1, 7);

-- insert demo blog
INSERT INTO blog(title, description, author, image_url, blog_type, content) VALUES ('办理轿车托运车辆运输保险须知及注意事项', '北京渤远物流有限公司简称（渤远运车）是一家专业从事汽车托运的物流企业，公司专注运营全国范围内私家车托运，二手车托运，巡展试驾车辆托运，主机厂保密车型托运，试验车托运，返乡团体车辆运输及自驾游车辆运输。', '渤远物流', '/images/gy_06.jpg', 0, '');