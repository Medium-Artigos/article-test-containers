-- V1__create_tables.sql
CREATE TABLE employee(
    id SERIAL NOT NULL,
    name varchar(255) NOT NULL,
    salary DECIMAL(12, 2) NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT employee_name_key UNIQUE (name)
);
