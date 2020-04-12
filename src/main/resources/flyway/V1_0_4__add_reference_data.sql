-- sex
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (1, 'Sex', 'SEX', '用户性别');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Female', 'FEMALE', '女', 0, 1);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Male', 'MALE', '男', 1, 1);
-- order_status
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (2, 'Order Status', 'ORDER_STATUS', '订单状态');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Submit', 'SUBMIT', '提交', 0, 2);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Start', 'START', '开始', 1, 2);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Processing', 'PROCESSING', '进行中', 2, 2);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Finish', 'FINISH', '完成', 3, 2);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Cancel', 'CANCEL', '取消', 4, 2);
-- order_source
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (3, 'Order Source', 'ORDER_SOURCE', '订单来源');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Website', 'WEBSITE', '网页', 0, 3);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Wechat', 'WECHAT', '小程序', 1, 3);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Advertise', 'AD', '推广', 2, 3);
-- order_type
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (4, 'Order Type', 'ORDER_TYPE', '订单类型');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Normal', 'NORMAL', '正常', 0, 4);
-- car_type
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (5, 'Car Type', 'CAR_TYPE', '车辆类型');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Car', 'CAR', '轿车', 0, 5);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('SUV', 'SUV', 'SUV', 1, 5);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Truck', 'TRUCK', '皮卡', 2, 5);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Other', 'OTHER', '其他', 3, 5);

-- is active
INSERT INTO reference_category(category_id, category_name, category_key, category_description) VALUES (6, 'Is Active', 'IS_ACTIVE', '是否存在');

INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Inactive', 'INACTIVE', '不存在', 0, 6);
INSERT INTO reference_xref(ref_name, ref_key, ref_description, ref_value, category_id) VALUES ('Active', 'ACTIVE', '存在', 1, 6);
