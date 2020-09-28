INSERT INTO app_user (id, user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(nextval('app_user_id_seq'), 'caio', 'Caio Teste', '$2a$16$9qr2tv0HmXbHBsx.TZFjfux742wCZM32a8Wi6iBqwIqaizlHPuxHS', true, true, true, true);

INSERT INTO app_user (id, user_name, full_name, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES(nextval('app_user_id_seq'), 'renan', 'Renan Teste', '$2a$16$h4yDQCYTy62R6xrtFDWONeMH3Lim4WQuU/aj8hxW.dJJoeyvtEkhK', true, true, true, true);