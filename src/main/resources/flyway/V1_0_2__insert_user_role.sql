-- insert user role category
INSERT INTO reference_category (category_id, category_name, category_key, category_description) VALUES (1, 'USER ROLE', 'USER_ROLE', 'user role in system');

-- insert user role value
INSERT INTO reference_xref (ref_name, ref_key, ref_description, ref_value, category_id) values ('Admin', 'ADMIN', 'Admin user role', 0, 1);
INSERT INTO reference_xref (ref_name, ref_key, ref_description, ref_value, category_id) values ('User', 'USER', 'Normal user role', 1, 1);