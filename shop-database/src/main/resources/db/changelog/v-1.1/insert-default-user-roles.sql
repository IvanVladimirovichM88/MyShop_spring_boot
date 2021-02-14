INSERT INTO `user_tbl` (`username_fld`, `password_fld`)
    VALUE   ('admin', '$2y$12$ysYXE5IpQo6SrwfLAvIA5.5l6VaLViRe0./G.k9WDCXN01D1LFP/2'),
    ('guest', '$2y$12$ysYXE5IpQo6SrwfLAvIA5.5l6VaLViRe0./G.k9WDCXN01D1LFP/2'),
    ('alex', '$2y$12$LvVz9Dve3.Uha8rV9hCqbuYXeM4qDCuVvFwJgV3g774nakxQl090C');
GO
INSERT INTO `role_tbl` (`name_fld`)
    VALUE 	('ROLE_ADMIN'),
    ('ROLE_GUEST');
GO

INSERT INTO `user_role_tbl`(`user_id`, `role_id`)
SELECT (SELECT `user_id` FROM `user_tbl` WHERE `username_fld` = 'admin'), (SELECT `role_id` FROM `role_tbl` WHERE `name_fld` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT `user_id` FROM `user_tbl` WHERE `username_fld` = 'alex'), (SELECT `role_id` FROM `role_tbl` WHERE `name_fld` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT `user_id` FROM `user_tbl` WHERE `username_fld` = 'guest'), (SELECT `role_id` FROM `role_tbl` WHERE `name_fld` = 'ROLE_GUEST');
GO
