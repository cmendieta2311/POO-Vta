PGDMP                         z            poo    12.8    14.0 '    C           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            D           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            E           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            F           1262    80880    poo    DATABASE     _   CREATE DATABASE poo WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Spain.1252';
    DROP DATABASE poo;
                postgres    false            ?            1259    80881    articulo    TABLE     ?   CREATE TABLE public.articulo (
    id integer NOT NULL,
    idmarca integer NOT NULL,
    descripcion character varying(45),
    precio_costo numeric DEFAULT 0 NOT NULL,
    precio_venta integer DEFAULT 0 NOT NULL,
    idiva integer
);
    DROP TABLE public.articulo;
       public         heap    postgres    false            ?            1259    80889    cliente    TABLE     ?   CREATE TABLE public.cliente (
    id integer NOT NULL,
    nombre character varying(45),
    apellido character varying(45),
    cedula integer,
    dv integer
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
       public          postgres    false    205            G           0    0    empresa_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.empresa_id_seq OWNED BY public.empresa.id;
          public          postgres    false    206            ?            1259    80904    factura    TABLE     ?   CREATE TABLE public.factura (
    id integer NOT NULL,
    idcliente integer NOT NULL,
    idusuario integer,
    condvta bigint DEFAULT 0 NOT NULL,
    idempresa integer
);
    DROP TABLE public.factura;
       public         heap    postgres    false            ?            1259    80908    iva    TABLE     \   CREATE TABLE public.iva (
    id integer NOT NULL,
    descripcion character varying(45)
);
    DROP TABLE public.iva;
       public         heap    postgres    false            ?            1259    80911    marca    TABLE     ^   CREATE TABLE public.marca (
    id integer NOT NULL,
    descripcion character varying(45)
);
    DROP TABLE public.marca;
       public         heap    postgres    false            ?            1259    80914    usuario    TABLE       CREATE TABLE public.usuario (
    id integer NOT NULL,
    nombre character varying(45) NOT NULL,
    apellido character varying(45) NOT NULL,
    usuario character varying(45) NOT NULL,
    "contraseña" character varying(45) NOT NULL,
    idempresa integer
);
    DROP TABLE public.usuario;
       public         heap    postgres    false            ?
           2604    80917 
   empresa id    DEFAULT     h   ALTER TABLE ONLY public.empresa ALTER COLUMN id SET DEFAULT nextval('public.empresa_id_seq'::regclass);
 9   ALTER TABLE public.empresa ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    206    205            8          0    80881    articulo 
   TABLE DATA           _   COPY public.articulo (id, idmarca, descripcion, precio_costo, precio_venta, idiva) FROM stdin;
    public          postgres    false    202   3-       9          0    80889    cliente 
   TABLE DATA           C   COPY public.cliente (id, nombre, apellido, cedula, dv) FROM stdin;
    public          postgres    false    203   P-       :          0    80892    det_factura 
   TABLE DATA           r   COPY public.det_factura (idfactura, idarticulo, cantidad, descripcion, importe, exentas, iva5, iva10) FROM stdin;
    public          postgres    false    204   ?-       ;          0    80899    empresa 
   TABLE DATA           B   COPY public.empresa (id, nombre, direccion, telefono) FROM stdin;
    public          postgres    false    205   ?-       =          0    80904    factura 
   TABLE DATA           O   COPY public.factura (id, idcliente, idusuario, condvta, idempresa) FROM stdin;
    public          postgres    false    207   ?-       >          0    80908    iva 
   TABLE DATA           .   COPY public.iva (id, descripcion) FROM stdin;
    public          postgres    false    208   ?-       ?          0    80911    marca 
   TABLE DATA           0   COPY public.marca (id, descripcion) FROM stdin;
    public          postgres    false    209   ?-       @          0    80914    usuario 
   TABLE DATA           Z   COPY public.usuario (id, nombre, apellido, usuario, "contraseña", idempresa) FROM stdin;
    public          postgres    false    210   ..       H           0    0    empresa_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.empresa_id_seq', 1, false);
          public          postgres    false    206            ?
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
       public          postgres    false    2727    203    207            ?
           2606    80937    factura fk_empresa_factura    FK CONSTRAINT     ?   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_empresa_factura FOREIGN KEY (idempresa) REFERENCES public.empresa(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 D   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_empresa_factura;
       public          postgres    false    205    2729    207            ?
           2606    80942    det_factura fk_idarticulo    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_idarticulo FOREIGN KEY (idarticulo) REFERENCES public.articulo(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 C   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_idarticulo;
       public          postgres    false    204    202    2725            ?
           2606    80947    usuario fk_idemprresa    FK CONSTRAINT     ?   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT fk_idemprresa FOREIGN KEY (idempresa) REFERENCES public.empresa(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 ?   ALTER TABLE ONLY public.usuario DROP CONSTRAINT fk_idemprresa;
       public          postgres    false    205    2729    210            ?
           2606    80952    det_factura fk_idfactura    FK CONSTRAINT     ?   ALTER TABLE ONLY public.det_factura
    ADD CONSTRAINT fk_idfactura FOREIGN KEY (idfactura) REFERENCES public.factura(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 B   ALTER TABLE ONLY public.det_factura DROP CONSTRAINT fk_idfactura;
       public          postgres    false    204    2731    207            ?
           2606    80957    articulo fk_idiva    FK CONSTRAINT     ?   ALTER TABLE ONLY public.articulo
    ADD CONSTRAINT fk_idiva FOREIGN KEY (idiva) REFERENCES public.iva(id) ON UPDATE CASCADE NOT VALID;
 ;   ALTER TABLE ONLY public.articulo DROP CONSTRAINT fk_idiva;
       public          postgres    false    2733    202    208            ?
           2606    80962    articulo fk_idmarca    FK CONSTRAINT     ?   ALTER TABLE ONLY public.articulo
    ADD CONSTRAINT fk_idmarca FOREIGN KEY (idmarca) REFERENCES public.marca(id) ON UPDATE CASCADE NOT VALID;
 =   ALTER TABLE ONLY public.articulo DROP CONSTRAINT fk_idmarca;
       public          postgres    false    209    2735    202            ?
           2606    80967    factura fk_usuario    FK CONSTRAINT     ?   ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_usuario FOREIGN KEY (idusuario) REFERENCES public.usuario(id) ON UPDATE CASCADE ON DELETE RESTRICT NOT VALID;
 <   ALTER TABLE ONLY public.factura DROP CONSTRAINT fk_usuario;
       public          postgres    false    2737    210    207            8      x?????? ? ?      9       x?3?L.?,??M??4150 "NC?=... X??      :      x?????? ? ?      ;      x?????? ? ?      =      x?????? ? ?      >      x?????? ? ?      ?   *   x?3??M,JNT0?2????L?,.C??`O'G?=...  ?
n      @      x?????? ? ?     