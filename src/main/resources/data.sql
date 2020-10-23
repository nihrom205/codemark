INSERT INTO company_role(name) VALUES ('admin');
INSERT INTO company_role(name) VALUES ('operator');
INSERT INTO company_role(name) VALUES ('analyst');
INSERT INTO company_role(name) VALUES ('manager');

INSERT INTO company_user(login, name, pass) VALUES ('vasiliyROOT', 'vasiliy', 'root');
INSERT INTO company_user(login, name, pass) VALUES ('petr100', 'petya', '123');
INSERT INTO company_user(login, name, pass) VALUES ('oleg2000', 'Oleg', 'qwerty');

INSERT INTO company_user_company_role(role_id, user_id) VALUES (1, 'vasiliyROOT');
INSERT INTO company_user_company_role(role_id, user_id) VALUES (2, 'vasiliyROOT');
INSERT INTO company_user_company_role(role_id, user_id) VALUES (2, 'petr100');
INSERT INTO company_user_company_role(role_id, user_id) VALUES (3, 'petr100');
INSERT INTO company_user_company_role(role_id, user_id) VALUES (4, 'oleg2000');