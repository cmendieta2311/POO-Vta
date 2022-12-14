PGDMP     3                
    z           poo    12.8    14.0 5    ^           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            _           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            `           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            a           1262    80880    poo    DATABASE     _   CREATE DATABASE poo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE poo;
                postgres    false            ?            1259    80991    articulo_id    SEQUENCE     t   CREATE SEQUENCE public.articulo_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.articulo_id;
       public          postgres    false            ?            1259    80881    articulo    TABLE       CREATE TABLE public.articulo (
    id integer DEFAULT nextval('public.articulo_id'::regclass) NOT NULL,
    idmarca integer NOT NULL,
    descripcion character varying(45),
    precio_costo integer DEFAULT 0 NOT NULL,
    precio_venta integer DEFAULT 0 NOT NULL,
    idiva integer
);
    DROP TABLE public.articulo;
       public         heap    postgres    false    212            ?            1259    80889    cliente    TABLE     ?   CREATE TABLE public.cliente (
    id integer NOT NULL,
    nombre character varying(45),
    apellido character varying(45),
    ruc_cedula character(20)
);
    DROP TABLE public.cliente;
       public         heap    postgres    false            ?            1259    80892    det_factura    TABLE     +  CREATE TABLE public.det_factura (
    idfactura integer,
    idarticulo integer,
    cantidad integer,
    descripcion character varying(45),
    importe integer DEFAULT 0 NOT NULL,
    exentas integer DEFAULT 0 NOT NULL,
    iva5 integer DEFAULT 0 NOT NULL,
    iva10 integer DEFAULT 0 NOT NULL
);
    DROP TABLE public.det_factura;
       public         heap    postgres    false            ?            1259    80899    empresa    TABLE     ?   CREATE TABLE public.empresa (
    id integer NOT NULL,
    nombre character varying(45),
    direccion character varying(45),
    telefono character varying(45)
);
    DROP TABLE public.empresa;
       public         heap    postgres    false            ?            1259    80902    empresa_id_seq    SEQUENCE     ?   CREATE SEQUENCE public.empresa_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.empresa_id_seq;
       public          postgres    false    205            b           0    0    empresa_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.empresa_id_seq OWNED BY public.empresa.id;
          public          postgres    false    206            ?            1259    80904    factura    TABLE       CREATE TABLE public.factura (
    id integer NOT NULL,
    idcliente integer NOT NULL,
    idusuario integer,
    condvta bigint DEFAULT 0 NOT NULL,
    idempresa integer,
    fecha date,
    nro integer DEFAULT 0 NOT NULL,
    ruc character(20),
    razon_social character(45)
);
    DROP TABLE public.factura;
       public         heap    postgres    false            ?            1259    81001 	   idcliente    SEQUENCE     r   CREATE SEQUENCE public.idcliente
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.idcliente;
       public          postgres    false            ?            1259    80994    idmarca    SEQUENCE     p   CREATE SEQUENCE public.idmarca
    START WITH 7
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.idmarca;
       public          postgres    false            ?            1259    80998    idperfil    SEQUENCE     q   CREATE SEQUENCE public.idperfil
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    DROP SEQUENCE public.idperfil;
       public          postgres    false            ?            1259    81003 	   idusuario    SEQUENCE     r   CREATE SEQUENCE public.idusuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
     DROP SEQUENCE public.idusuario;
       public          postgres    false            ?            1259    80908    iva    TABLE     \   CREATE TABLE public.iva (
    id integer NOT NULL,
    descripcion character varying(45)
);
    DROP TABLE public.iva;
       public         heap    postgres    false            ?            1259    80911    marca    TABLE     ?   CREATE TABLE public.marca (
    id integer DEFAULT nextval('public.idmarca'::regclass) NOT NULL,
    descripcion character varying(45)
);
    DROP TABLE public.marca;
       public         heap    postgres    false    213            ?            1259    80973    perfil    TABLE     ?   CREATE TABLE public.perfil (
    id integer DEFAULT nextval('public.idperfil'::regclass) NOT NULL,
    descripcion character(45)
);
    DROP TABLE public.perfil;
       public         heap    postgres    false    214            ?            1259    80914    usuario    TABLE     J  CREATE TABLE public.usuario (
    id integer DEFAULT nextval('public.idusuario'::regclass) NOT NULL,
    nombre character varying(45) NOT NULL,
    apellido character varying(45) NOT NULL,
    usuario character varying(45) NOT NULL,
    "contraseña" character varying(45) NOT NULL,
    idempresa integer,
    idperfil integer
);
    DROP TABLE public.usuario;
       public         heap    postgres    false    216            ?
           2604    80917 
   empresa id    DEFAULT     h   ALTER TABLE ONLY public.empresa ALTER COLUMN id SET DEFAULT nextval('public.empresa_id_seq'::regclass);
 9   ALTER TABLE public.empresa ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205            M          0    80881    articulo 
   TABLE DATA           _   COPY public.articulo (id, idmarca, descripcion, precio_costo, precio_venta, idiva) FROM stdin;
    public          postgres    false    202   Q<       N          0    80889    cliente 
   TABLE DATA           C   COPY public.cliente (id, nombre, apellido, ruc_cedula) FROM stdin;
    public          postgres    false    203   ?<       O          0    80892    det_factura 
   TABLE DATA           r   COPY public.det_factura (idfactura, idarticulo, cantidad, descripcion, importe, exentas, iva5, iva10) FROM stdin;
    public          postgres    false    204   ?<       P          0    80899    empresa 
   TABLE DATA           B   COPY public.empresa (id, nombre, direccion, telefono) FROM stdin;
    public          postgres    false    205   ?<       R          0    80904    factura 
   TABLE DATA           n   COPY public.factura (id, idcliente, idusuario, condvta, idempresa, fecha, nro, ruc, razon_social) FROM stdin;
    public          postgres    false    207   |=       S          0    80908    iva 
   TABLE DATA           .   COPY public.iva (id, descripcion) FROM stdin;
    public          postgres    false    208   ?=       T          0    80911    marca 
   TABLE DATA           0   COPY public.marca (id, descripcion) FROM stdin;
    public          postgres    false    209   ?=       V          0    80973    perfil 
   TABLE DATA           1   COPY public.perfil (id, descripcion) FROM stdin;
    public          postgres    false    211   O>       U          0    80914    usuario 
   TABLE DATA           d   COPY public.usuario (id, nombre, apellido, usuario, "contraseña", idempresa, idperfil) FROM stdin;
    public          postgres    false    210   ?>       c           0    0    articulo_id    SEQUENCE SET     9   SELECT pg_catalog.setval('public.articulo_id', 4, true);
          public          postgres    false    212            d           0    0    empresa_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.empresa_id_seq', 1, false);
          public          postgres    false    206            e           0    0 	   idcliente    SEQUENCE SET     8   SELECT pg_catalog.setval('public.idcliente', 1, false);
          public          postgres    false    215            f           0    0    idmarca    SEQUENCE SET     6   SELECT pg_catalog.setval('public.idmarca', 10, true);
          public          postgres    false    213            g           0    0    idperfil    SEQUENCE SET     6   SELECT pg_catalog.setval('public.idperfil', 2, true);
          public          postgres    false    214            h           0    0 	   idusuario    SEQUENCE SET     8   SELECT pg_catalog.setval('public.idusuario', 1, false);
          public          postgres    false    216            ?
           2606    80919    cliente cliente_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.cliente
    ADD CONSTRAINT cliente_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.cliente DROP CONSTRAINT cliente_pkey;
       public            postgres    false    203            ?
           2606    80921    empresa empresa_pk 
   CONSTRAINT     P   ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresa_pk PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.empresa DROP CONSTRAINT empresa_pk;
       public            postgres    false    205            ?
           2606    80923    iva iva_pkey 
   CONSTRAINT     J   ALTER TABLE ONLY public.iva
    ADD CONSTRAINT iva_pkey PRIMARY KEY (id);
 6   ALTER TABLE ONLY public.iva DROP CONSTRAINT iva_pkey;
       public            postgres    false    208            ?
           2606    80925    marca marca_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.marca
    ADD CONSTRAINT marca_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.marca DROP CONSTRAINT marca_pkey;
       public            postgres    false    209            ?
           2606    80980    perfil perfil_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.perfil
    ADD CONSTRAINT perfil_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.perfil DROP CONSTRAINT perfil_pkey;
       public            postgres    false    211            ?
           2606    80927    factura pk_idfactura 
   CONSTRAINT     R   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT pk_idfactura PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.factura DROP CONSTRAINT pk_idfactura;
       public            postgres    false    207            ?
           2606    80929    articulo producto_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.articulo
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.articulo DROP CONSTRAINT producto_pkey;
       public            postgres    false    202            ?
           2606    80931    usuario usuario_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            postgres    false    210            ?
           2606    80932    factura fk_cliente    FK CONSTRAINT     ?   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_cliente FOREIGN KEY (idcliente) REFERENCES public.cliente(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 <   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_cliente;
       public          postgres    false    207    2745    203            ?
           2606    80937    factura fk_empresa_factura    FK CONSTRAINT     ?   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_empresa_factura FOREIGN KEY (idempresa) REFERENCES public.empresa(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 D   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_empresa_factura;
       public          postgres    false    207    2747    205            ?
           2606    80942    det_factura fk_idarticulo    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_idarticulo FOREIGN KEY (idarticulo) REFERENCES public.articulo(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 C   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_idarticulo;
       public          postgres    false    2743    202    204            ?
           2606    80947    usuario fk_idemprresa    FK CONSTRAINT     ?   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_idemprresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 ?   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_idemprresa;
       public          postgres    false    2747    210    205            ?
           2606    80952    det_factura fk_idfactura    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_idfactura FOREIGN KEY (idfactura) REFERENCES public.factura(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 B   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_idfactura;
       public          postgres    false    204    2749    207            ?
           2606    80957    articulo fk_idiva    FK CONSTRAINT     ?   ALTER TABLE ONLY public.articulo
    ADD CONSTRAINT fk_idiva FOREIGN KEY (idiva) REFERENCES public.iva(id) ON UPDATE CASCADE NOT VALID;
 ;   ALTER TABLE ONLY public.articulo DROP CONSTRAINT fk_idiva;
       public          postgres    false    202    2751    208            ?
           2606    80962    articulo fk_idmarca    FK CONSTRAINT     ?   ALTER TABLE ONLY public.articulo
    ADD CONSTRAINT fk_idmarca FOREIGN KEY (idmarca) REFERENCES public.marca(id) ON UPDATE CASCADE NOT VALID;
 =   ALTER TABLE ONLY public.articulo DROP CONSTRAINT fk_idmarca;
       public          postgres    false    202    209    2753            ?
           2606    80981    usuario fk_idperfil    FK CONSTRAINT     ?   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_idperfil FOREIGN KEY (idperfil) REFERENCES public.perfil(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 =   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_idperfil;
       public          postgres    false    210    211    2757            ?
           2606    80967    factura fk_usuario    FK CONSTRAINT     ?   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_usuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 <   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_usuario;
       public          postgres    false    2755    210    207            M   -   x?3?4?(?O)M.?W0T(-HI,I?44 NCSi????? ?`	?      N   )   x?3???/JO?t?ϫJ?I??4150 "]C?????? ?c	<      O   (   x?3?4?4?(?O)M.?W0?4700?As#?=... ?1?      P   m   x?3???Tp?-(J-NT?s?t?K??S?/?LM??4?47?500 a.#N?:#?*?".c??1gXfNNbQQfr"'?	\܄?911?4????˔??7 ?5?Q????+F??? ??$5      R   2   x?3?4B 6202?54?54rML?H?P8??2??\1z\\\ ?r?      S   '   x?3?t?H?+I?2?t/J,KLIT0?2???b???? ܨ
      T   J   x?3??M,JNT0?2??L???,#.C(?P?? %?$??*`?e??[?d?sY@?,?,9Ss??8S@t? ?L      V   1   x?3?tL???S pq:;z9?\?˘???YO???7 ?m\1z\\\ ???      U   C   x?3?tN,??/???LL????LNKN12?0?4M153MM33K5OIK?L??073II?4?4?????? ?'     