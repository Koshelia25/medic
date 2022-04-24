
CREATE SEQUENCE document_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE users_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE document
(
    id integer NOT NULL DEFAULT nextval('document_seq'::regclass),
    title character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT document_pkey PRIMARY KEY (id)
)

CREATE TABLE users
(
    id integer NOT NULL DEFAULT nextval('users_seq'::regclass),
    name character varying(100) COLLATE pg_catalog."default",
    login character varying(100),
    password character varying(100),
    email character varying(100),
    phone_number character varying(100),
    role_name character varying(100),
    CONSTRAINT users_pkey PRIMARY KEY (id)
)


CREATE SEQUENCE user_document_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE user_document
(
    id integer NOT NULL DEFAULT nextval('user_document_seq'::regclass),
    user_id integer,
    document_id integer,
    document_status character varying(100),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (document_id) REFERENCES document(id)
)
