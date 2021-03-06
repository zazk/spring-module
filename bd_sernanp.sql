PGDMP     *    1                v            sernanp    9.3.23    9.3.23 \    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16393    sernanp    DATABASE     �   CREATE DATABASE sernanp WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Peru.1252' LC_CTYPE = 'Spanish_Peru.1252';
    DROP DATABASE sernanp;
             postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
             postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                  postgres    false    6                       0    0    SCHEMA public    ACL     �   REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
                  postgres    false    6                        3079    11750    plpgsql 	   EXTENSION     ?   CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
    DROP EXTENSION plpgsql;
                  false                       0    0    EXTENSION plpgsql    COMMENT     @   COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';
                       false    1            �            1259    16434    t_categoria    TABLE     {   CREATE TABLE public.t_categoria (
    srl_cod_categoria integer NOT NULL,
    var_nombre character varying(50) NOT NULL
);
    DROP TABLE public.t_categoria;
       public         postgres    false    6                       0    0 $   COLUMN t_categoria.srl_cod_categoria    COMMENT     b   COMMENT ON COLUMN public.t_categoria.srl_cod_categoria IS 'Codigo identificador de la categoria';
            public       postgres    false    179            �            1259    16432 !   t_categoria_srl_cod_categoria_seq    SEQUENCE     �   CREATE SEQUENCE public.t_categoria_srl_cod_categoria_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.t_categoria_srl_cod_categoria_seq;
       public       postgres    false    6    179                       0    0 !   t_categoria_srl_cod_categoria_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.t_categoria_srl_cod_categoria_seq OWNED BY public.t_categoria.srl_cod_categoria;
            public       postgres    false    178            �            1259    16498    t_grupo    TABLE     %  CREATE TABLE public.t_grupo (
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
    DROP TABLE public.t_grupo;
       public         postgres    false    6            �            1259    16496    t_grupo_srl_cod_grupo_seq    SEQUENCE     �   CREATE SEQUENCE public.t_grupo_srl_cod_grupo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.t_grupo_srl_cod_grupo_seq;
       public       postgres    false    189    6                       0    0    t_grupo_srl_cod_grupo_seq    SEQUENCE OWNED BY     W   ALTER SEQUENCE public.t_grupo_srl_cod_grupo_seq OWNED BY public.t_grupo.srl_cod_grupo;
            public       postgres    false    188            �            1259    16517    t_grupo_visitante    TABLE     }   CREATE TABLE public.t_grupo_visitante (
    srl_cod_grupo integer,
    srl_cod_visitante integer,
    bol_ingreso boolean
);
 %   DROP TABLE public.t_grupo_visitante;
       public         postgres    false    6            �            1259    16410 	   t_noticia    TABLE     �   CREATE TABLE public.t_noticia (
    srl_cod_noticia integer NOT NULL,
    var_titulo character varying(100) NOT NULL,
    txt_contenido text,
    tsp_fec_publicacion date NOT NULL,
    bol_activo boolean,
    var_usuario character varying(30)
);
    DROP TABLE public.t_noticia;
       public         postgres    false    6            �            1259    16408    t_noticia_srl_cod_noticia_seq    SEQUENCE     �   CREATE SEQUENCE public.t_noticia_srl_cod_noticia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.t_noticia_srl_cod_noticia_seq;
       public       postgres    false    174    6            	           0    0    t_noticia_srl_cod_noticia_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.t_noticia_srl_cod_noticia_seq OWNED BY public.t_noticia.srl_cod_noticia;
            public       postgres    false    173            �            1259    16427 
   t_operador    TABLE     �  CREATE TABLE public.t_operador (
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
    DROP TABLE public.t_operador;
       public         postgres    false    6            �            1259    16458    t_pago    TABLE     A  CREATE TABLE public.t_pago (
    srl_cod_pago integer NOT NULL,
    var_cod_operador character varying(30) NOT NULL,
    var_operacion character varying(30) NOT NULL,
    dte_fec_abono date,
    var_comprobante character varying(200) NOT NULL,
    int_estado integer,
    txt_motivorechazo text,
    num_monto integer
);
    DROP TABLE public.t_pago;
       public         postgres    false    6            
           0    0    COLUMN t_pago.var_comprobante    COMMENT     `   COMMENT ON COLUMN public.t_pago.var_comprobante IS 'Ruta donde se almacena el voucher de pago';
            public       postgres    false    185                       0    0    COLUMN t_pago.int_estado    COMMENT     H   COMMENT ON COLUMN public.t_pago.int_estado IS 'Estado del comprobante';
            public       postgres    false    185                       0    0    COLUMN t_pago.txt_motivorechazo    COMMENT     Z   COMMENT ON COLUMN public.t_pago.txt_motivorechazo IS 'Motivo del rechazo en caso se de.';
            public       postgres    false    185            �            1259    16456    t_pago_srl_cod_pago_seq    SEQUENCE     �   CREATE SEQUENCE public.t_pago_srl_cod_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.t_pago_srl_cod_pago_seq;
       public       postgres    false    6    185                       0    0    t_pago_srl_cod_pago_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.t_pago_srl_cod_pago_seq OWNED BY public.t_pago.srl_cod_pago;
            public       postgres    false    184            �            1259    16450    t_pais    TABLE     h   CREATE TABLE public.t_pais (
    srl_cod_pais integer NOT NULL,
    var_nombre character varying(30)
);
    DROP TABLE public.t_pais;
       public         postgres    false    6            �            1259    16448    t_pais_srl_cod_pais_seq    SEQUENCE     �   CREATE SEQUENCE public.t_pais_srl_cod_pais_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.t_pais_srl_cod_pais_seq;
       public       postgres    false    183    6                       0    0    t_pais_srl_cod_pais_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.t_pais_srl_cod_pais_seq OWNED BY public.t_pais.srl_cod_pais;
            public       postgres    false    182            �            1259    16402    t_ruta    TABLE     �   CREATE TABLE public.t_ruta (
    srl_cod_ruta integer NOT NULL,
    var_nombre character varying(50) NOT NULL,
    bol_estado boolean NOT NULL
);
    DROP TABLE public.t_ruta;
       public         postgres    false    6            �            1259    16400    t_ruta_srl_cod_ruta_seq    SEQUENCE     �   CREATE SEQUENCE public.t_ruta_srl_cod_ruta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.t_ruta_srl_cod_ruta_seq;
       public       postgres    false    172    6                       0    0    t_ruta_srl_cod_ruta_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.t_ruta_srl_cod_ruta_seq OWNED BY public.t_ruta.srl_cod_ruta;
            public       postgres    false    171            �            1259    16442    t_tip_documento    TABLE        CREATE TABLE public.t_tip_documento (
    srl_cod_documento integer NOT NULL,
    var_nombre character varying(20) NOT NULL
);
 #   DROP TABLE public.t_tip_documento;
       public         postgres    false    6            �            1259    16440 %   t_tip_documento_srl_cod_documento_seq    SEQUENCE     �   CREATE SEQUENCE public.t_tip_documento_srl_cod_documento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 <   DROP SEQUENCE public.t_tip_documento_srl_cod_documento_seq;
       public       postgres    false    181    6                       0    0 %   t_tip_documento_srl_cod_documento_seq    SEQUENCE OWNED BY     o   ALTER SEQUENCE public.t_tip_documento_srl_cod_documento_seq OWNED BY public.t_tip_documento.srl_cod_documento;
            public       postgres    false    180            �            1259    16421 	   t_usuario    TABLE     �   CREATE TABLE public.t_usuario (
    srl_cod_usuario integer NOT NULL,
    var_usuario character varying(30) NOT NULL,
    var_clave character varying(100),
    bol_estado boolean,
    var_email character varying(50)
);
    DROP TABLE public.t_usuario;
       public         postgres    false    6            �            1259    16419    t_usuario_srl_cod_usuario_seq    SEQUENCE     �   CREATE SEQUENCE public.t_usuario_srl_cod_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 4   DROP SEQUENCE public.t_usuario_srl_cod_usuario_seq;
       public       postgres    false    176    6                       0    0    t_usuario_srl_cod_usuario_seq    SEQUENCE OWNED BY     _   ALTER SEQUENCE public.t_usuario_srl_cod_usuario_seq OWNED BY public.t_usuario.srl_cod_usuario;
            public       postgres    false    175            �            1259    16474    t_visitante    TABLE     m  CREATE TABLE public.t_visitante (
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
    DROP TABLE public.t_visitante;
       public         postgres    false    6                       0    0 $   COLUMN t_visitante.srl_cod_visitante    COMMENT     R   COMMENT ON COLUMN public.t_visitante.srl_cod_visitante IS 'Codigo del visitante';
            public       postgres    false    187                       0    0 $   COLUMN t_visitante.srl_cod_documento    COMMENT     Q   COMMENT ON COLUMN public.t_visitante.srl_cod_documento IS 'Codigo de documento';
            public       postgres    false    187                       0    0 $   COLUMN t_visitante.srl_cod_categoria    COMMENT     Q   COMMENT ON COLUMN public.t_visitante.srl_cod_categoria IS 'Codigo de categoria';
            public       postgres    false    187                       0    0    COLUMN t_visitante.srl_cod_pais    COMMENT     H   COMMENT ON COLUMN public.t_visitante.srl_cod_pais IS 'Codigo del pais';
            public       postgres    false    187            �            1259    16472 !   t_visitante_srl_cod_visitante_seq    SEQUENCE     �   CREATE SEQUENCE public.t_visitante_srl_cod_visitante_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 8   DROP SEQUENCE public.t_visitante_srl_cod_visitante_seq;
       public       postgres    false    6    187                       0    0 !   t_visitante_srl_cod_visitante_seq    SEQUENCE OWNED BY     g   ALTER SEQUENCE public.t_visitante_srl_cod_visitante_seq OWNED BY public.t_visitante.srl_cod_visitante;
            public       postgres    false    186            ]           2604    16437    srl_cod_categoria    DEFAULT     �   ALTER TABLE ONLY public.t_categoria ALTER COLUMN srl_cod_categoria SET DEFAULT nextval('public.t_categoria_srl_cod_categoria_seq'::regclass);
 L   ALTER TABLE public.t_categoria ALTER COLUMN srl_cod_categoria DROP DEFAULT;
       public       postgres    false    178    179    179            b           2604    16501    srl_cod_grupo    DEFAULT     ~   ALTER TABLE ONLY public.t_grupo ALTER COLUMN srl_cod_grupo SET DEFAULT nextval('public.t_grupo_srl_cod_grupo_seq'::regclass);
 D   ALTER TABLE public.t_grupo ALTER COLUMN srl_cod_grupo DROP DEFAULT;
       public       postgres    false    189    188    189            [           2604    16413    srl_cod_noticia    DEFAULT     �   ALTER TABLE ONLY public.t_noticia ALTER COLUMN srl_cod_noticia SET DEFAULT nextval('public.t_noticia_srl_cod_noticia_seq'::regclass);
 H   ALTER TABLE public.t_noticia ALTER COLUMN srl_cod_noticia DROP DEFAULT;
       public       postgres    false    174    173    174            `           2604    16461    srl_cod_pago    DEFAULT     z   ALTER TABLE ONLY public.t_pago ALTER COLUMN srl_cod_pago SET DEFAULT nextval('public.t_pago_srl_cod_pago_seq'::regclass);
 B   ALTER TABLE public.t_pago ALTER COLUMN srl_cod_pago DROP DEFAULT;
       public       postgres    false    184    185    185            _           2604    16453    srl_cod_pais    DEFAULT     z   ALTER TABLE ONLY public.t_pais ALTER COLUMN srl_cod_pais SET DEFAULT nextval('public.t_pais_srl_cod_pais_seq'::regclass);
 B   ALTER TABLE public.t_pais ALTER COLUMN srl_cod_pais DROP DEFAULT;
       public       postgres    false    183    182    183            Z           2604    16405    srl_cod_ruta    DEFAULT     z   ALTER TABLE ONLY public.t_ruta ALTER COLUMN srl_cod_ruta SET DEFAULT nextval('public.t_ruta_srl_cod_ruta_seq'::regclass);
 B   ALTER TABLE public.t_ruta ALTER COLUMN srl_cod_ruta DROP DEFAULT;
       public       postgres    false    172    171    172            ^           2604    16445    srl_cod_documento    DEFAULT     �   ALTER TABLE ONLY public.t_tip_documento ALTER COLUMN srl_cod_documento SET DEFAULT nextval('public.t_tip_documento_srl_cod_documento_seq'::regclass);
 P   ALTER TABLE public.t_tip_documento ALTER COLUMN srl_cod_documento DROP DEFAULT;
       public       postgres    false    180    181    181            \           2604    16424    srl_cod_usuario    DEFAULT     �   ALTER TABLE ONLY public.t_usuario ALTER COLUMN srl_cod_usuario SET DEFAULT nextval('public.t_usuario_srl_cod_usuario_seq'::regclass);
 H   ALTER TABLE public.t_usuario ALTER COLUMN srl_cod_usuario DROP DEFAULT;
       public       postgres    false    176    175    176            a           2604    16477    srl_cod_visitante    DEFAULT     �   ALTER TABLE ONLY public.t_visitante ALTER COLUMN srl_cod_visitante SET DEFAULT nextval('public.t_visitante_srl_cod_visitante_seq'::regclass);
 L   ALTER TABLE public.t_visitante ALTER COLUMN srl_cod_visitante DROP DEFAULT;
       public       postgres    false    186    187    187            �          0    16434    t_categoria 
   TABLE DATA               D   COPY public.t_categoria (srl_cod_categoria, var_nombre) FROM stdin;
    public       postgres    false    179   Sm                  0    0 !   t_categoria_srl_cod_categoria_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.t_categoria_srl_cod_categoria_seq', 2, true);
            public       postgres    false    178            �          0    16498    t_grupo 
   TABLE DATA               &  COPY public.t_grupo (srl_cod_grupo, var_cod_operador, srl_cod_ruta, dte_fec_programada, dte_fec_visita, int_nro_visitante, int_nro_inasistente, num_costo, int_estado, var_documento, txt_motivoobservado, dte_fec_observado, var_usuario, var_usuariomodificacion, dte_fec_modificacion) FROM stdin;
    public       postgres    false    189   �m                  0    0    t_grupo_srl_cod_grupo_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.t_grupo_srl_cod_grupo_seq', 1, false);
            public       postgres    false    188            �          0    16517    t_grupo_visitante 
   TABLE DATA               Z   COPY public.t_grupo_visitante (srl_cod_grupo, srl_cod_visitante, bol_ingreso) FROM stdin;
    public       postgres    false    190   �m       �          0    16410 	   t_noticia 
   TABLE DATA               }   COPY public.t_noticia (srl_cod_noticia, var_titulo, txt_contenido, tsp_fec_publicacion, bol_activo, var_usuario) FROM stdin;
    public       postgres    false    174   �m                  0    0    t_noticia_srl_cod_noticia_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.t_noticia_srl_cod_noticia_seq', 3, true);
            public       postgres    false    173            �          0    16427 
   t_operador 
   TABLE DATA               �   COPY public.t_operador (var_cod_operador, var_ruc, var_razonsocial, var_direccion, var_telefono, var_email, var_web, num_saldo, bol_estado) FROM stdin;
    public       postgres    false    177   0n       �          0    16458    t_pago 
   TABLE DATA               �   COPY public.t_pago (srl_cod_pago, var_cod_operador, var_operacion, dte_fec_abono, var_comprobante, int_estado, txt_motivorechazo, num_monto) FROM stdin;
    public       postgres    false    185   �n                  0    0    t_pago_srl_cod_pago_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.t_pago_srl_cod_pago_seq', 5, true);
            public       postgres    false    184            �          0    16450    t_pais 
   TABLE DATA               :   COPY public.t_pais (srl_cod_pais, var_nombre) FROM stdin;
    public       postgres    false    183   Bo                  0    0    t_pais_srl_cod_pais_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.t_pais_srl_cod_pais_seq', 31, true);
            public       postgres    false    182            �          0    16402    t_ruta 
   TABLE DATA               F   COPY public.t_ruta (srl_cod_ruta, var_nombre, bol_estado) FROM stdin;
    public       postgres    false    172   Qp                  0    0    t_ruta_srl_cod_ruta_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.t_ruta_srl_cod_ruta_seq', 4, true);
            public       postgres    false    171            �          0    16442    t_tip_documento 
   TABLE DATA               H   COPY public.t_tip_documento (srl_cod_documento, var_nombre) FROM stdin;
    public       postgres    false    181   �p                  0    0 %   t_tip_documento_srl_cod_documento_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.t_tip_documento_srl_cod_documento_seq', 7, true);
            public       postgres    false    180            �          0    16421 	   t_usuario 
   TABLE DATA               c   COPY public.t_usuario (srl_cod_usuario, var_usuario, var_clave, bol_estado, var_email) FROM stdin;
    public       postgres    false    176   �p                  0    0    t_usuario_srl_cod_usuario_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.t_usuario_srl_cod_usuario_seq', 2, true);
            public       postgres    false    175            �          0    16474    t_visitante 
   TABLE DATA               �   COPY public.t_visitante (srl_cod_visitante, srl_cod_documento, srl_cod_categoria, srl_cod_pais, var_nombre, var_apellido, var_nro_documento, dte_fec_nacimiento, bol_sexo) FROM stdin;
    public       postgres    false    187   )q                  0    0 !   t_visitante_srl_cod_visitante_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.t_visitante_srl_cod_visitante_seq', 3, true);
            public       postgres    false    186            l           2606    16439    t_categoria_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.t_categoria
    ADD CONSTRAINT t_categoria_pkey PRIMARY KEY (srl_cod_categoria);
 F   ALTER TABLE ONLY public.t_categoria DROP CONSTRAINT t_categoria_pkey;
       public         postgres    false    179    179            v           2606    16503    t_grupo_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.t_grupo
    ADD CONSTRAINT t_grupo_pkey PRIMARY KEY (srl_cod_grupo);
 >   ALTER TABLE ONLY public.t_grupo DROP CONSTRAINT t_grupo_pkey;
       public         postgres    false    189    189            f           2606    16418    t_noticia_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.t_noticia
    ADD CONSTRAINT t_noticia_pkey PRIMARY KEY (srl_cod_noticia);
 B   ALTER TABLE ONLY public.t_noticia DROP CONSTRAINT t_noticia_pkey;
       public         postgres    false    174    174            j           2606    16431    t_operador_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.t_operador
    ADD CONSTRAINT t_operador_pkey PRIMARY KEY (var_cod_operador);
 D   ALTER TABLE ONLY public.t_operador DROP CONSTRAINT t_operador_pkey;
       public         postgres    false    177    177            r           2606    16466    t_pago_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.t_pago
    ADD CONSTRAINT t_pago_pkey PRIMARY KEY (srl_cod_pago);
 <   ALTER TABLE ONLY public.t_pago DROP CONSTRAINT t_pago_pkey;
       public         postgres    false    185    185            p           2606    16455    t_pais_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.t_pais
    ADD CONSTRAINT t_pais_pkey PRIMARY KEY (srl_cod_pais);
 <   ALTER TABLE ONLY public.t_pais DROP CONSTRAINT t_pais_pkey;
       public         postgres    false    183    183            d           2606    16407    t_ruta_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.t_ruta
    ADD CONSTRAINT t_ruta_pkey PRIMARY KEY (srl_cod_ruta);
 <   ALTER TABLE ONLY public.t_ruta DROP CONSTRAINT t_ruta_pkey;
       public         postgres    false    172    172            n           2606    16447    t_tip_documento_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.t_tip_documento
    ADD CONSTRAINT t_tip_documento_pkey PRIMARY KEY (srl_cod_documento);
 N   ALTER TABLE ONLY public.t_tip_documento DROP CONSTRAINT t_tip_documento_pkey;
       public         postgres    false    181    181            h           2606    16426    t_usuario_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.t_usuario
    ADD CONSTRAINT t_usuario_pkey PRIMARY KEY (srl_cod_usuario);
 B   ALTER TABLE ONLY public.t_usuario DROP CONSTRAINT t_usuario_pkey;
       public         postgres    false    176    176            t           2606    16479    t_visitante_pkey 
   CONSTRAINT     i   ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_pkey PRIMARY KEY (srl_cod_visitante);
 F   ALTER TABLE ONLY public.t_visitante DROP CONSTRAINT t_visitante_pkey;
       public         postgres    false    187    187            {           2606    16512    t_grupo_srl_cod_ruta_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_grupo
    ADD CONSTRAINT t_grupo_srl_cod_ruta_fkey FOREIGN KEY (srl_cod_ruta) REFERENCES public.t_ruta(srl_cod_ruta);
 K   ALTER TABLE ONLY public.t_grupo DROP CONSTRAINT t_grupo_srl_cod_ruta_fkey;
       public       postgres    false    189    1892    172            z           2606    16507    t_grupo_var_cod_operador_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_grupo
    ADD CONSTRAINT t_grupo_var_cod_operador_fkey FOREIGN KEY (var_cod_operador) REFERENCES public.t_operador(var_cod_operador);
 O   ALTER TABLE ONLY public.t_grupo DROP CONSTRAINT t_grupo_var_cod_operador_fkey;
       public       postgres    false    189    177    1898            |           2606    16520 $   t_grupo_visitante_srl_cod_grupo_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_grupo_visitante
    ADD CONSTRAINT t_grupo_visitante_srl_cod_grupo_fkey FOREIGN KEY (srl_cod_grupo) REFERENCES public.t_grupo(srl_cod_grupo);
 `   ALTER TABLE ONLY public.t_grupo_visitante DROP CONSTRAINT t_grupo_visitante_srl_cod_grupo_fkey;
       public       postgres    false    1910    189    190            }           2606    16525 (   t_grupo_visitante_srl_cod_visitante_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_grupo_visitante
    ADD CONSTRAINT t_grupo_visitante_srl_cod_visitante_fkey FOREIGN KEY (srl_cod_visitante) REFERENCES public.t_visitante(srl_cod_visitante);
 d   ALTER TABLE ONLY public.t_grupo_visitante DROP CONSTRAINT t_grupo_visitante_srl_cod_visitante_fkey;
       public       postgres    false    187    1908    190            w           2606    16480 "   t_visitante_srl_cod_categoria_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_srl_cod_categoria_fkey FOREIGN KEY (srl_cod_categoria) REFERENCES public.t_categoria(srl_cod_categoria);
 X   ALTER TABLE ONLY public.t_visitante DROP CONSTRAINT t_visitante_srl_cod_categoria_fkey;
       public       postgres    false    187    179    1900            y           2606    16490 "   t_visitante_srl_cod_documento_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_srl_cod_documento_fkey FOREIGN KEY (srl_cod_documento) REFERENCES public.t_tip_documento(srl_cod_documento);
 X   ALTER TABLE ONLY public.t_visitante DROP CONSTRAINT t_visitante_srl_cod_documento_fkey;
       public       postgres    false    181    187    1902            x           2606    16485    t_visitante_srl_cod_pais_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.t_visitante
    ADD CONSTRAINT t_visitante_srl_cod_pais_fkey FOREIGN KEY (srl_cod_pais) REFERENCES public.t_pais(srl_cod_pais);
 S   ALTER TABLE ONLY public.t_visitante DROP CONSTRAINT t_visitante_srl_cod_pais_fkey;
       public       postgres    false    1904    187    183            �   #   x�3�tL)�)��2�t-.)M�L�+I����� rO�      �      x������ � �      �      x������ � �      �   `   x�3����t�tT0�I�(�WHIU((*MMJ���K2�39�-tLt,9K8]|=���8� �Ή�I���% �� vbJ>T��5��qqq 6Qi      �   �   x�]λ�0���S�/`���;46@Zar!���[���q999�w�"�8c��Tq��l�4����T;g��S2P*�+�OsP2A�b	�[S�]�j/����~�d��BO�Y�?������
�)���$�m�{��$*�<��9sK��a��"!�'Jy�A�      �   G   x�3��v2040�4N#C]#] �Ӑ3���L��8�� ��E�)�"Sc#CL���b���� )X�      �   �   x�%�[n� E��*��j ��4�X" �jF��:z���=�;�N\�v.�Zf�8sYhv�#��Ύ[��8��$���^t�bBW�����jf�蚁��mׅɟF�����"��G��/(�MF��B� �n{���/��@��E����E^yc#�D�} Kʼ��Nn�B�(xU2���\���	��S(�Aτ�m����ŵ~�5�1�Y�Xl���T��O@~�������S����f46���ED�[�      �   (   x�3�*-IT0�,�2�0��Lc��4�0M��=... -�m      �   7   x�3�v�
q�2�t���2�t��2�tv�2���2����9�B��b���� ��	^      �   I   x�3��/H-JL�/2�45162�,A�8W����%��rrf�%'�%���8 � 9NC#cS�F�*�b���� ��#�      �   [   x�3�4�4�44���/N�tO,J�L�4�NCKK]]�.#N44���L�HL���/.���65523��0�54�*1�2&Iu� `��     