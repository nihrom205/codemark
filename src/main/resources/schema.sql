DROP TABLE company_user_company_role;
DROP TABLE company_user;
DROP TABLE company_role;

CREATE TABLE IF NOT EXISTS company_role (
	id SERIAL PRIMARY KEY,
	name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS company_user (
	login VARCHAR(30) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	pass VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS company_user_company_role (
    role_id int NOT NULL,
    user_id varchar(30) NOT NULL,
    CONSTRAINT fk1_company_user_company_role FOREIGN KEY(role_id) REFERENCES company_role(id),
    CONSTRAINT fk2_company_user_company_role FOREIGN KEY(user_id) REFERENCES company_user(login)
)