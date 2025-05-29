create table client (
	id INT AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(50) NOT NULL,
	middle_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	client_id VARCHAR(40) NOT NULL UNIQUE
);
create table account (
	id INT AUTO_INCREMENT PRIMARY KEY,
	client_id VARCHAR(40) NOT NULL,
	type_account VARCHAR(20) NOT NULL,
	balance DECIMAL(8,2) NOT NULL,
	CONSTRAINT fk_client_id
	FOREIGN KEY (client_id) REFERENCES client(client_id)
);

create table transactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
	account_id INT NOT NULL,
	sum_transaction DECIMAL(8,2) NOT NULL,
	date_time TIMESTAMP NOT NULL,
	CONSTRAINT fk_account_id
	FOREIGN KEY (account_id) REFERENCES account(id)
);

create table data_source_error_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
	stacktrace CLOB NOT NULL,
	message VARCHAR(255) NOT NULL,
	method_signature VARCHAR(255) NOT NULL
);

--create table time_limit_exceed_log (
--    id INT PRIMARY KEY AUTO_INCREMENT,
--    method_signature VARCHAR(255) NOT NULL,
--    time_execute BIGINT NOT NULL,
--    date_time TIMESTAMP NOT NULL,
--    limit BIGINT NOT NULL
--);