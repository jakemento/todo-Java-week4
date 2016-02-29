--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: jws
--

CREATE TABLE categories (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE categories OWNER TO jws;

--
-- Name: categories_id_seq; Type: SEQUENCE; Schema: public; Owner: jws
--

CREATE SEQUENCE categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_id_seq OWNER TO jws;

--
-- Name: categories_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jws
--

ALTER SEQUENCE categories_id_seq OWNED BY categories.id;


--
-- Name: categories_tasks; Type: TABLE; Schema: public; Owner: jws
--

CREATE TABLE categories_tasks (
    id integer NOT NULL,
    category_id integer,
    task_id integer
);


ALTER TABLE categories_tasks OWNER TO jws;

--
-- Name: categories_tasks_id_seq; Type: SEQUENCE; Schema: public; Owner: jws
--

CREATE SEQUENCE categories_tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE categories_tasks_id_seq OWNER TO jws;

--
-- Name: categories_tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jws
--

ALTER SEQUENCE categories_tasks_id_seq OWNED BY categories_tasks.id;


--
-- Name: tasks; Type: TABLE; Schema: public; Owner: jws
--

CREATE TABLE tasks (
    id integer NOT NULL,
    description character varying
);


ALTER TABLE tasks OWNER TO jws;

--
-- Name: tasks_id_seq; Type: SEQUENCE; Schema: public; Owner: jws
--

CREATE SEQUENCE tasks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tasks_id_seq OWNER TO jws;

--
-- Name: tasks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jws
--

ALTER SEQUENCE tasks_id_seq OWNED BY tasks.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jws
--

ALTER TABLE ONLY categories ALTER COLUMN id SET DEFAULT nextval('categories_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jws
--

ALTER TABLE ONLY categories_tasks ALTER COLUMN id SET DEFAULT nextval('categories_tasks_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jws
--

ALTER TABLE ONLY tasks ALTER COLUMN id SET DEFAULT nextval('tasks_id_seq'::regclass);


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: jws
--

COPY categories (id, name) FROM stdin;
\.


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jws
--

SELECT pg_catalog.setval('categories_id_seq', 28, true);


--
-- Data for Name: categories_tasks; Type: TABLE DATA; Schema: public; Owner: jws
--

COPY categories_tasks (id, category_id, task_id) FROM stdin;
1	1	3
2	3	1
3	3	3
\.


--
-- Name: categories_tasks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jws
--

SELECT pg_catalog.setval('categories_tasks_id_seq', 3, true);


--
-- Data for Name: tasks; Type: TABLE DATA; Schema: public; Owner: jws
--

COPY tasks (id, description) FROM stdin;
\.


--
-- Name: tasks_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jws
--

SELECT pg_catalog.setval('tasks_id_seq', 9, true);


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: jws
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: categories_tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: jws
--

ALTER TABLE ONLY categories_tasks
    ADD CONSTRAINT categories_tasks_pkey PRIMARY KEY (id);


--
-- Name: tasks_pkey; Type: CONSTRAINT; Schema: public; Owner: jws
--

ALTER TABLE ONLY tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: jws
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM jws;
GRANT ALL ON SCHEMA public TO jws;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

