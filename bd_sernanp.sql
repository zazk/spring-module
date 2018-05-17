--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.23
-- Dumped by pg_dump version 9.3.23
-- Started on 2018-05-13 10:10:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2051 (class 1262 OID 16393)
-- Name: sernanp; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE sernanp WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Peru.1252' LC_CTYPE = 'Spanish_Peru.1252';


ALTER DATABASE sernanp OWNER TO postgres;

\connect sernanp

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 179 (class 1259 OID 16434)
-- Name: t_categoria; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_categoria (
    srl_cod_categoria integer NOT NULL,
    var_nombre character varying(50) NOT NULL
);


ALTER TABLE public.t_categoria OWNER TO postgres;

--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 179
-- Name: COLUMN t_categoria.srl_cod_categoria; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_categoria.srl_cod_categoria IS 'Codigo identificador de la categoria';


--
-- TOC entry 178 (class 1259 OID 16432)
-- Name: t_categoria_srl_cod_categoria_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_categoria_srl_cod_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_categoria_srl_cod_categoria_seq OWNER TO postgres;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 178
-- Name: t_categoria_srl_cod_categoria_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_categoria_srl_cod_categoria_seq OWNED BY public.t_categoria.srl_cod_categoria;


--
-- TOC entry 189 (class 1259 OID 16498)
-- Name: t_grupo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_grupo (
    srl_cod_grupo integer NOT NULL,
    var_cod_operador character varying(20) NOT NULL,
    srl_cod_ruta integer NOT NULL,
    dte_fec_programada date,
    dte_fec_visita date,
    int_nro_visitante integer NOT NULL,
    int_nro_inasistente integer,
    num_costo money,
    int_estado integer,
    var_documento character varying(200),
    txt_motivoobservado text,
    dte_fec_observado date,
    var_usuario character varying(30),
    var_usuariomodificacion character varying(30),
    dte_fec_modificacion date
);


ALTER TABLE public.t_grupo OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 16496)
-- Name: t_grupo_srl_cod_grupo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_grupo_srl_cod_grupo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_grupo_srl_cod_grupo_seq OWNER TO postgres;

--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 188
-- Name: t_grupo_srl_cod_grupo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_grupo_srl_cod_grupo_seq OWNED BY public.t_grupo.srl_cod_grupo;


--
-- TOC entry 190 (class 1259 OID 16517)
-- Name: t_grupo_visitante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_grupo_visitante (
    srl_cod_grupo integer,
    srl_cod_visitante integer,
    bol_ingreso boolean
);


ALTER TABLE public.t_grupo_visitante OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16410)
-- Name: t_noticia; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_noticia (
    srl_cod_noticia integer NOT NULL,
    var_titulo character varying(100) NOT NULL,
    txt_contenido text,
    tsp_fec_publicacion date NOT NULL,
    bol_activo boolean,
    var_usuario character varying(30)
);


ALTER TABLE public.t_noticia OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16408)
-- Name: t_noticia_srl_cod_noticia_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_noticia_srl_cod_noticia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_noticia_srl_cod_noticia_seq OWNER TO postgres;

--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 173
-- Name: t_noticia_srl_cod_noticia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_noticia_srl_cod_noticia_seq OWNED BY public.t_noticia.srl_cod_noticia;


--
-- TOC entry 177 (class 1259 OID 16427)
-- Name: t_operador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_operador (
    var_cod_operador character varying(20) NOT NULL,
    var_ruc character varying(11) NOT NULL,
    var_razonsocial character varying(50) NOT NULL,
    var_direccion character varying(150),
    var_telefono character varying(20),
    var_email character varying(50),
    var_web character varying(100),
    num_saldo money,
    bol_estado boolean
);


ALTER TABLE public.t_operador OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 16458)
-- Name: t_pago; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_pago (
    srl_cod_pago integer NOT NULL,
    var_cod_operador character varying(30) NOT NULL,
    var_operacion character varying(30) NOT NULL,
    num_monto money NOT NULL,
    dte_fec_abono date,
    var_comprobante character varying(200) NOT NULL,
    int_estado integer,
    txt_motivorechazo text
);


ALTER TABLE public.t_pago OWNER TO postgres;

--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN t_pago.var_comprobante; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_pago.var_comprobante IS 'Ruta donde se almacena el voucher de pago';


--
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN t_pago.int_estado; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_pago.int_estado IS 'Estado del comprobante';


--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 185
-- Name: COLUMN t_pago.txt_motivorechazo; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_pago.txt_motivorechazo IS 'Motivo del rechazo en caso se de.';


--
-- TOC entry 184 (class 1259 OID 16456)
-- Name: t_pago_srl_cod_pago_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_pago_srl_cod_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_pago_srl_cod_pago_seq OWNER TO postgres;

--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 184
-- Name: t_pago_srl_cod_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_pago_srl_cod_pago_seq OWNED BY public.t_pago.srl_cod_pago;


--
-- TOC entry 183 (class 1259 OID 16450)
-- Name: t_pais; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_pais (
    srl_cod_pais integer NOT NULL,
    var_nombre character varying(30)
);


ALTER TABLE public.t_pais OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 16448)
-- Name: t_pais_srl_cod_pais_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_pais_srl_cod_pais_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_pais_srl_cod_pais_seq OWNER TO postgres;

--
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 182
-- Name: t_pais_srl_cod_pais_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_pais_srl_cod_pais_seq OWNED BY public.t_pais.srl_cod_pais;


--
-- TOC entry 172 (class 1259 OID 16402)
-- Name: t_ruta; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_ruta (
    srl_cod_ruta integer NOT NULL,
    var_nombre character varying(50) NOT NULL,
    bol_estado boolean NOT NULL
);


ALTER TABLE public.t_ruta OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16400)
-- Name: t_ruta_srl_cod_ruta_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_ruta_srl_cod_ruta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_ruta_srl_cod_ruta_seq OWNER TO postgres;

--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 171
-- Name: t_ruta_srl_cod_ruta_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_ruta_srl_cod_ruta_seq OWNED BY public.t_ruta.srl_cod_ruta;


--
-- TOC entry 181 (class 1259 OID 16442)
-- Name: t_tip_documento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_tip_documento (
    srl_cod_documento integer NOT NULL,
    var_nombre character varying(20) NOT NULL
);


ALTER TABLE public.t_tip_documento OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16440)
-- Name: t_tip_documento_srl_cod_documento_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_tip_documento_srl_cod_documento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_tip_documento_srl_cod_documento_seq OWNER TO postgres;

--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 180
-- Name: t_tip_documento_srl_cod_documento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_tip_documento_srl_cod_documento_seq OWNED BY public.t_tip_documento.srl_cod_documento;


--
-- TOC entry 176 (class 1259 OID 16421)
-- Name: t_usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_usuario (
    srl_cod_usuario integer NOT NULL,
    var_usuario character varying(30) NOT NULL,
    var_clave character varying(100),
    bol_estado boolean,
    var_email character varying(50)
);


ALTER TABLE public.t_usuario OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 16419)
-- Name: t_usuario_srl_cod_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_usuario_srl_cod_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_usuario_srl_cod_usuario_seq OWNER TO postgres;

--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 175
-- Name: t_usuario_srl_cod_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_usuario_srl_cod_usuario_seq OWNED BY public.t_usuario.srl_cod_usuario;


--
-- TOC entry 187 (class 1259 OID 16474)
-- Name: t_visitante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE public.t_visitante (
    srl_cod_visitante integer NOT NULL,
    srl_cod_documento integer NOT NULL,
    srl_cod_categoria integer,
    srl_cod_pais integer,
    var_nombre character varying(50) NOT NULL,
    var_apellido character varying(50) NOT NULL,
    var_nro_documento character varying(20),
    dte_fec_nacimiento date,
    bol_sexo integer
);


ALTER TABLE public.t_visitante OWNER TO postgres;

--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN t_visitante.srl_cod_visitante; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_visitante.srl_cod_visitante IS 'Codigo del visitante';


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN t_visitante.srl_cod_documento; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_visitante.srl_cod_documento IS 'Codigo de documento';


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN t_visitante.srl_cod_categoria; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_visitante.srl_cod_categoria IS 'Codigo de categoria';


--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 187
-- Name: COLUMN t_visitante.srl_cod_pais; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.t_visitante.srl_cod_pais IS 'Codigo del pais';


--
-- TOC entry 186 (class 1259 OID 16472)
-- Name: t_visitante_srl_cod_visitante_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.t_visitante_srl_cod_visitante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.t_visitante_srl_cod_visitante_seq OWNER TO postgres;

--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 186
-- Name: t_visitante_srl_cod_visitante_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.t_visitante_srl_cod_visitante_seq OWNED BY public.t_visitante.srl_cod_visitante;


--
-- TOC entry 1885 (class 2604 OID 16437)
-- Name: srl_cod_categoria; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_categoria ALTER COLUMN srl_cod_categoria SET DEFAULT nextval('public.t_categoria_srl_cod_categoria_seq'::regclass);


--
-- TOC entry 1890 (class 2604 OID 16501)
-- Name: srl_cod_grupo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_grupo ALTER COLUMN srl_cod_grupo SET DEFAULT nextval('public.t_grupo_srl_cod_grupo_seq'::regclass);


--
-- TOC entry 1883 (class 2604 OID 16413)
-- Name: srl_cod_noticia; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_noticia ALTER COLUMN srl_cod_noticia SET DEFAULT nextval('public.t_noticia_srl_cod_noticia_seq'::regclass);


--
-- TOC entry 1888 (class 2604 OID 16461)
-- Name: srl_cod_pago; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_pago ALTER COLUMN srl_cod_pago SET DEFAULT nextval('public.t_pago_srl_cod_pago_seq'::regclass);


--
-- TOC entry 1887 (class 2604 OID 16453)
-- Name: srl_cod_pais; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_pais ALTER COLUMN srl_cod_pais SET DEFAULT nextval('public.t_pais_srl_cod_pais_seq'::regclass);


--
-- TOC entry 1882 (class 2604 OID 16405)
-- Name: srl_cod_ruta; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_ruta ALTER COLUMN srl_cod_ruta SET DEFAULT nextval('public.t_ruta_srl_cod_ruta_seq'::regclass);


--
-- TOC entry 1886 (class 2604 OID 16445)
-- Name: srl_cod_documento; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_tip_documento ALTER COLUMN srl_cod_documento SET DEFAULT nextval('public.t_tip_documento_srl_cod_documento_seq'::regclass);


--
-- TOC entry 1884 (class 2604 OID 16424)
-- Name: srl_cod_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_usuario ALTER COLUMN srl_cod_usuario SET DEFAULT nextval('public.t_usuario_srl_cod_usuario_seq'::regclass);


--
-- TOC entry 1889 (class 2604 OID 16477)
-- Name: srl_cod_visitante; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_visitante ALTER COLUMN srl_cod_visitante SET DEFAULT nextval('public.t_visitante_srl_cod_visitante_seq'::regclass);


--
-- TOC entry 2034 (class 0 OID 16434)
-- Dependencies: 179
-- Data for Name: t_categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_categoria VALUES (1, 'Adulto');
INSERT INTO public.t_categoria VALUES (2, 'Estudiante');


--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 178
-- Name: t_categoria_srl_cod_categoria_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_categoria_srl_cod_categoria_seq', 2, true);


--
-- TOC entry 2044 (class 0 OID 16498)
-- Dependencies: 189
-- Data for Name: t_grupo; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 188
-- Name: t_grupo_srl_cod_grupo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_grupo_srl_cod_grupo_seq', 1, false);


--
-- TOC entry 2045 (class 0 OID 16517)
-- Dependencies: 190
-- Data for Name: t_grupo_visitante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2029 (class 0 OID 16410)
-- Dependencies: 174
-- Data for Name: t_noticia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_noticia VALUES (1, 'NOTICIA 1', 'Texto de prueba de noticia', '2018-04-09', true, 'ADMIN');
INSERT INTO public.t_noticia VALUES (2, 'NOTICIA 2', 'Texto de prueba de noticia 2', '2018-04-14', false, 'ADMIN');


--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 173
-- Name: t_noticia_srl_cod_noticia_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_noticia_srl_cod_noticia_seq', 2, true);


--
-- TOC entry 2032 (class 0 OID 16427)
-- Dependencies: 177
-- Data for Name: t_operador; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2040 (class 0 OID 16458)
-- Dependencies: 185
-- Data for Name: t_pago; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 184
-- Name: t_pago_srl_cod_pago_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_pago_srl_cod_pago_seq', 1, false);


--
-- TOC entry 2038 (class 0 OID 16450)
-- Dependencies: 183
-- Data for Name: t_pais; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_pais VALUES (1, 'AFGANISTAN');
INSERT INTO public.t_pais VALUES (2, 'ISLAS ALAND');
INSERT INTO public.t_pais VALUES (3, 'ALBANIA');
INSERT INTO public.t_pais VALUES (4, 'ARGELIA');
INSERT INTO public.t_pais VALUES (5, 'SAMOA ESTADOUNIDENSE');
INSERT INTO public.t_pais VALUES (6, 'ANDORRA');
INSERT INTO public.t_pais VALUES (7, 'ANGOLA');
INSERT INTO public.t_pais VALUES (8, 'ANGUILLA');
INSERT INTO public.t_pais VALUES (9, 'ANTARTIDA');
INSERT INTO public.t_pais VALUES (10, 'ANTIGUA Y BARBUDA');
INSERT INTO public.t_pais VALUES (11, 'ARGENTINA');
INSERT INTO public.t_pais VALUES (12, 'ARMENIA');
INSERT INTO public.t_pais VALUES (13, 'ARUBA');
INSERT INTO public.t_pais VALUES (14, 'AUSTRALIA');
INSERT INTO public.t_pais VALUES (15, 'AUSTRIA');
INSERT INTO public.t_pais VALUES (16, 'AZERBAIYAN');
INSERT INTO public.t_pais VALUES (17, 'BAHAMAS');
INSERT INTO public.t_pais VALUES (18, 'BAHREIN');
INSERT INTO public.t_pais VALUES (19, 'BANGLADESH');
INSERT INTO public.t_pais VALUES (20, 'BARBADOS');
INSERT INTO public.t_pais VALUES (21, 'BIELORRUSIA');
INSERT INTO public.t_pais VALUES (22, 'BELGICA');
INSERT INTO public.t_pais VALUES (23, 'BELICE');
INSERT INTO public.t_pais VALUES (24, 'BENIN');
INSERT INTO public.t_pais VALUES (25, 'BERMUDAS');
INSERT INTO public.t_pais VALUES (26, 'BHUTAN');
INSERT INTO public.t_pais VALUES (27, 'BOLIVIA');
INSERT INTO public.t_pais VALUES (28, 'BOSNIA Y HERZEGOVINA');
INSERT INTO public.t_pais VALUES (29, 'BOTSWANA');
INSERT INTO public.t_pais VALUES (30, 'ISLA BOUVET');
INSERT INTO public.t_pais VALUES (31, 'BRASIL');


--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 182
-- Name: t_pais_srl_cod_pais_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_pais_srl_cod_pais_seq', 31, true);


--
-- TOC entry 2027 (class 0 OID 16402)
-- Dependencies: 172
-- Data for Name: t_ruta; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_ruta VALUES (1, 'Ruta 1', true);
INSERT INTO public.t_ruta VALUES (2, 'Ruta 2', true);
INSERT INTO public.t_ruta VALUES (3, 'Ruta 3', true);
INSERT INTO public.t_ruta VALUES (4, 'Ruta 4', true);


--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 171
-- Name: t_ruta_srl_cod_ruta_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_ruta_srl_cod_ruta_seq', 4, true);


--
-- TOC entry 2036 (class 0 OID 16442)
-- Dependencies: 181
-- Data for Name: t_tip_documento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.t_tip_documento VALUES (1, 'PSAPORTE');
INSERT INTO public.t_tip_documento VALUES (2, 'DNI');
INSERT INTO public.t_tip_documento VALUES (3, 'CN');
INSERT INTO public.t_tip_documento VALUES (4, 'CE');
INSERT INTO public.t_tip_documento VALUES (5, 'TI');
INSERT INTO public.t_tip_documento VALUES (6, 'CES');
INSERT INTO public.t_tip_documento VALUES (7, 'RUC');


--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 180
-- Name: t_tip_documento_srl_cod_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_tip_documento_srl_cod_documento_seq', 7, true);


--
-- TOC entry 2031 (class 0 OID 16421)
-- Dependencies: 176
-- Data for Name: t_usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 175
-- Name: t_usuario_srl_cod_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_usuario_srl_cod_usuario_seq', 1, false);


--
-- TOC entry 2042 (class 0 OID 16474)
-- Dependencies: 187
-- Data for Name: t_visitante; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 186
-- Name: t_visitante_srl_cod_visitante_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.t_visitante_srl_cod_visitante_seq', 1, false);


--
-- TOC entry 1900 (class 2606 OID 16439)
-- Name: t_categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_categoria
    ADD CONSTRAINT t_categoria_pkey PRIMARY KEY (srl_cod_categoria);


--
-- TOC entry 1910 (class 2606 OID 16503)
-- Name: t_grupo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_grupo
    ADD CONSTRAINT t_grupo_pkey PRIMARY KEY (srl_cod_grupo);


--
-- TOC entry 1894 (class 2606 OID 16418)
-- Name: t_noticia_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_noticia
    ADD CONSTRAINT t_noticia_pkey PRIMARY KEY (srl_cod_noticia);


--
-- TOC entry 1898 (class 2606 OID 16431)
-- Name: t_operador_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_operador
    ADD CONSTRAINT t_operador_pkey PRIMARY KEY (var_cod_operador);


--
-- TOC entry 1906 (class 2606 OID 16466)
-- Name: t_pago_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_pago
    ADD CONSTRAINT t_pago_pkey PRIMARY KEY (srl_cod_pago);


--
-- TOC entry 1904 (class 2606 OID 16455)
-- Name: t_pais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_pais
    ADD CONSTRAINT t_pais_pkey PRIMARY KEY (srl_cod_pais);


--
-- TOC entry 1892 (class 2606 OID 16407)
-- Name: t_ruta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_ruta
    ADD CONSTRAINT t_ruta_pkey PRIMARY KEY (srl_cod_ruta);


--
-- TOC entry 1902 (class 2606 OID 16447)
-- Name: t_tip_documento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_tip_documento
    ADD CONSTRAINT t_tip_documento_pkey PRIMARY KEY (srl_cod_documento);


--
-- TOC entry 1896 (class 2606 OID 16426)
-- Name: t_usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_usuario
    ADD CONSTRAINT t_usuario_pkey PRIMARY KEY (srl_cod_usuario);


--
-- TOC entry 1908 (class 2606 OID 16479)
-- Name: t_visitante_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_pkey PRIMARY KEY (srl_cod_visitante);


--
-- TOC entry 1916 (class 2606 OID 16512)
-- Name: t_grupo_srl_cod_ruta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_grupo
    ADD CONSTRAINT t_grupo_srl_cod_ruta_fkey FOREIGN KEY (srl_cod_ruta) REFERENCES public.t_ruta(srl_cod_ruta);


--
-- TOC entry 1915 (class 2606 OID 16507)
-- Name: t_grupo_var_cod_operador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_grupo
    ADD CONSTRAINT t_grupo_var_cod_operador_fkey FOREIGN KEY (var_cod_operador) REFERENCES public.t_operador(var_cod_operador);


--
-- TOC entry 1917 (class 2606 OID 16520)
-- Name: t_grupo_visitante_srl_cod_grupo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_grupo_visitante
    ADD CONSTRAINT t_grupo_visitante_srl_cod_grupo_fkey FOREIGN KEY (srl_cod_grupo) REFERENCES public.t_grupo(srl_cod_grupo);


--
-- TOC entry 1918 (class 2606 OID 16525)
-- Name: t_grupo_visitante_srl_cod_visitante_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_grupo_visitante
    ADD CONSTRAINT t_grupo_visitante_srl_cod_visitante_fkey FOREIGN KEY (srl_cod_visitante) REFERENCES public.t_visitante(srl_cod_visitante);


--
-- TOC entry 1911 (class 2606 OID 16467)
-- Name: t_pago_var_cod_operador_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_pago
    ADD CONSTRAINT t_pago_var_cod_operador_fkey FOREIGN KEY (var_cod_operador) REFERENCES public.t_operador(var_cod_operador);


--
-- TOC entry 1912 (class 2606 OID 16480)
-- Name: t_visitante_srl_cod_categoria_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_srl_cod_categoria_fkey FOREIGN KEY (srl_cod_categoria) REFERENCES public.t_categoria(srl_cod_categoria);


--
-- TOC entry 1914 (class 2606 OID 16490)
-- Name: t_visitante_srl_cod_documento_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_srl_cod_documento_fkey FOREIGN KEY (srl_cod_documento) REFERENCES public.t_tip_documento(srl_cod_documento);


--
-- TOC entry 1913 (class 2606 OID 16485)
-- Name: t_visitante_srl_cod_pais_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_srl_cod_pais_fkey FOREIGN KEY (srl_cod_pais) REFERENCES public.t_pais(srl_cod_pais);


--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2018-05-13 10:10:27

--
-- PostgreSQL database dump complete
--

