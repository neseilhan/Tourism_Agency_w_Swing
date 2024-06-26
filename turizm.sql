PGDMP  6    $                |            turizm    16.3    16.3      �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24985    turizm    DATABASE     �   CREATE DATABASE turizm WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE turizm;
                postgres    false            �            1259    25003    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_city text,
    hotel_region text,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_star text NOT NULL,
    hotel_carpark boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    25087    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    25011    pension_type    TABLE     �   CREATE TABLE public.pension_type (
    pension_id integer NOT NULL,
    pension_hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
     DROP TABLE public.pension_type;
       public         heap    postgres    false            �            1259    25010    pension_type_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension_type ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_type_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    25032    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    reservation_room_id integer NOT NULL,
    reservation_in_date date NOT NULL,
    reservation_out_date date NOT NULL,
    reservation_name text NOT NULL,
    reservation_mail text NOT NULL,
    reservation_pn text NOT NULL,
    reservation_idnum text NOT NULL,
    reservation_prc text NOT NULL,
    reservation_note text
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    25019    room    TABLE     !  CREATE TABLE public.room (
    room_id integer NOT NULL,
    room_hotel_id integer NOT NULL,
    room_bed integer NOT NULL,
    room_size integer NOT NULL,
    room_minibar boolean NOT NULL,
    room_game_console boolean NOT NULL,
    room_type text NOT NULL,
    room_safebox boolean NOT NULL,
    room_projection boolean NOT NULL,
    room_tv boolean NOT NULL,
    room_stock integer NOT NULL,
    room_child_prc bigint NOT NULL,
    room_adult_prc bigint NOT NULL,
    room_pension_id integer NOT NULL,
    room_season_id integer NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    25018    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    25027    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    season_start_date date NOT NULL,
    season_end_date date NOT NULL,
    season_hotel_id integer NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    25026    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    24987    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    24986    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �          0    25003    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_city, hotel_region, hotel_address, hotel_mail, hotel_star, hotel_carpark, hotel_spa, hotel_room_service, hotel_pool, hotel_wifi, hotel_fitness, hotel_concierge) FROM stdin;
    public          postgres    false    217   �'       �          0    25011    pension_type 
   TABLE DATA           R   COPY public.pension_type (pension_id, pension_hotel_id, pension_type) FROM stdin;
    public          postgres    false    219   K(       �          0    25032    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, reservation_room_id, reservation_in_date, reservation_out_date, reservation_name, reservation_mail, reservation_pn, reservation_idnum, reservation_prc, reservation_note) FROM stdin;
    public          postgres    false    224   h(       �          0    25019    room 
   TABLE DATA           �   COPY public.room (room_id, room_hotel_id, room_bed, room_size, room_minibar, room_game_console, room_type, room_safebox, room_projection, room_tv, room_stock, room_child_prc, room_adult_prc, room_pension_id, room_season_id) FROM stdin;
    public          postgres    false    221   �(       �          0    25027    season 
   TABLE DATA           `   COPY public.season (season_id, season_start_date, season_end_date, season_hotel_id) FROM stdin;
    public          postgres    false    223   �(       �          0    24987    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    216   �(       �           0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 3, true);
          public          postgres    false    225            �           0    0    pension_type_pension_id_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.pension_type_pension_id_seq', 1, false);
          public          postgres    false    218            �           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 1, false);
          public          postgres    false    220            �           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 1, false);
          public          postgres    false    222            �           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 3, true);
          public          postgres    false    215            5           2606    25009    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    217            7           2606    25017    pension_type pension_type_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.pension_type
    ADD CONSTRAINT pension_type_pkey PRIMARY KEY (pension_id);
 H   ALTER TABLE ONLY public.pension_type DROP CONSTRAINT pension_type_pkey;
       public            postgres    false    219            =           2606    25038    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    224            9           2606    25025    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    221            ;           2606    25031    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    223            3           2606    24996    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            �   q   x�3����)��SpO,JI�S����<��*7���)��(1;'��1%1'�DG����\�PO!8[�/��LG�����@���F}�F#c#MKS 20�4�,�L������ U#A      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   4   x�3�LL����L,N�tt����2��K-N���H������G��r��qqq Y�O     