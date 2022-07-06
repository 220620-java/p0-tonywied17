-- DROP SCHEMA bank3;

CREATE SCHEMA bank3 AUTHORIZATION postgres;

-- DROP SEQUENCE bank3.account_id_seq;

CREATE SEQUENCE bank3.account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE bank3.account_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE bank3.account_id_seq TO postgres;

-- DROP SEQUENCE bank3.transactions_id_seq;

CREATE SEQUENCE bank3.transactions_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE bank3.transactions_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE bank3.transactions_id_seq TO postgres;

-- DROP SEQUENCE bank3.users_id_seq;

CREATE SEQUENCE bank3.users_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE bank3.users_id_seq OWNER TO postgres;
GRANT ALL ON SEQUENCE bank3.users_id_seq TO postgres;
-- bank3.transactions definition

-- Drop table

-- DROP TABLE bank3.transactions;

CREATE TABLE bank3.transactions (
	id serial4 NOT NULL,
	acc_type varchar(50) NOT NULL,
	amount varchar(100) NOT NULL,
	balance_after varchar(100) NOT NULL,
	account_id int4 NOT NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE bank3.transactions OWNER TO postgres;
GRANT ALL ON TABLE bank3.transactions TO postgres;


-- bank3.users definition

-- Drop table

-- DROP TABLE bank3.users;

CREATE TABLE bank3.users (
	id serial4 NOT NULL,
	username varchar(30) NOT NULL,
	"password" varchar(30) NOT NULL,
	fullname varchar(75) NOT NULL,
	phone varchar(20) NOT NULL,
	email varchar(100) NOT NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);

-- Permissions

ALTER TABLE bank3.users OWNER TO postgres;
GRANT ALL ON TABLE bank3.users TO postgres;


-- bank3.account definition

-- Drop table

-- DROP TABLE bank3.account;

CREATE TABLE bank3.account (
	id serial4 NOT NULL,
	owner_id int4 NULL,
	balance varchar(255) NOT NULL,
	account_type varchar(20) NOT NULL,
	CONSTRAINT account_pkey PRIMARY KEY (id),
	CONSTRAINT account_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES bank3.users(id)
);

-- Permissions

ALTER TABLE bank3.account OWNER TO postgres;
GRANT ALL ON TABLE bank3.account TO postgres;


-- bank3.clients definition

-- Drop table

-- DROP TABLE bank3.clients;

CREATE TABLE bank3.clients (
	owner_id int4 NOT NULL,
	account_id int4 NOT NULL,
	trans_id int4 NOT NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (owner_id, account_id),
	CONSTRAINT clients_account_id_fkey FOREIGN KEY (account_id) REFERENCES bank3.account(id),
	CONSTRAINT clients_owner_id_fkey FOREIGN KEY (owner_id) REFERENCES bank3.users(id),
	CONSTRAINT clients_trans_id_fkey FOREIGN KEY (trans_id) REFERENCES bank3.transactions(id)
);

-- Permissions

ALTER TABLE bank3.clients OWNER TO postgres;
GRANT ALL ON TABLE bank3.clients TO postgres;




-- Permissions

GRANT ALL ON SCHEMA bank3 TO postgres;
