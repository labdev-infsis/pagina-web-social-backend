insert into role(id_role, name)
    values(1, 'ADMIN'),
    (2, 'STUDENT');

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_portada_path, photo_profile_path)
    values(100, 'j5818068-9280-4055-987c-087f1b1f6635', 'Alicia', 'Mercado Sandoval', 'alicia.mercado@fcyt.umss.edu.bo', '79235628', '$2a$10$d2Re5HES0exoWQkbZ9IKSukZZI3kLokhOWJfRI1LZcuE/WN.zYV6e', null, 'https://ibb.co/cwvQby0' );

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_portada_path, photo_profile_path)
    values(110, 'a0818068-4880-4055-987c-087f1b1f6635', 'Sergio', 'Salazar Velasco', '202004185@est.umss.edu', '62954271', '$2a$10$2ohp0csjbBWQkcidcmGeyuaP21iST1m2Ps7yBUa6uoViJ9eUYOlBi', null, 'https://ibb.co/cwvQby0' );

insert into user_roles (user_id, role_id)
    values(100, 1),(110, 2);

insert into comment_config (id, uuid, configuration)
    values(20, '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', 'Todos pueden comentar'),
    (21, '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', 'Nadie puede comentar'),
    (22, '492d7d7f-9f4s-8g45-hy34-77a9h46759d0', 'Comentarios con moderador');

insert into institution (id, uuid, name, description, location, category, email, phone, url, logo_url, background_url)
    values(10, '77f803b4-f63b-4c4a-be05-eae84cef0c0c',
            unistr('Ingenier\00EDa El\00E9ctrica'),
            unistr('Servicios, actividades y noticias de la carrera de Ingenier\00EDa El\00E9ctrica de la FCyT(UMSS)'),
            'Campus Central UMSS (frente al Parque la Torre) Cochabamba, Bolivia', 
            'Universidad', 
            'electrica@fcyt.umss.edu.bo',
            '+591 4 4543232',
            'electrica.fcyt.umss.edu.bo',
            '/images/77f803b4o4534d',
            '/images/eae84cef0c0cd3');

insert into content (id, uuid)
    values(100, '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (101, '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (102, '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (103, '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f');

insert into text (id, uuid, content_id, text)
    values(100, '99c1c948-f5f4-4e39-92a6-fa761f2410a7', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', unistr('\00BFQuieres estudiar Ingenier\00EDa El\00E9ctrica? \00A1Conoce el plan de estudios de la carrera!')),
        (101, '52o7c948-f5f4-4e39-92a6-fa761f2410a7', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', unistr('La carrera de Ingenier\00EDa El\00E9ctrica cuenta con varios convenios interinstitucionales para la realizaci\00F3n de pr\00E1cticas pre-profesionales.')),
        (102, '19b6c948-f5f4-4e39-92a6-fa761f2410a7', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', unistr('Si eres estudiante de Ingenier\00EDa El\00E9ctrica en la UMSS, te invitamos a conocer los beneficios que te brinda est\00E1 instituci\00F3n.')),
        (103, '3m22c948-o3j9-4e39-92a6-fa761f2410a7', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', unistr('Convocatoria Acto de Colaci\00F3n 02-2024'));

insert into media (id, uuid, content_id, number, file_type, file_path)
values(100, '38j1f8a1-09e0-4435-a850-0613d778897b', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://ibb.co/JFT4wbS'),
    (101, '79a3f8a1-09e0-4435-a850-0613d778897b', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', 2, 'image', 'https://ibb.co/Gkb3d2W'),
    (102, '24l5f8a1-09e0-4435-a850-0613d778897b', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://ibb.co/986DJky'),
    (103, '81k7f8a1-09e0-4435-a850-0613d778897b', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://ibb.co/Kmjy634'),
    (104, '17ucf8a1-09e0-4435-a850-0613d778897b', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://ibb.co/8z2mFq2'),
    (105, '95n3f8a1-09e0-4435-a850-0613d778897b', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 2, 'image', 'https://ibb.co/6rMGC4S'),
    (106, '44k7f8a1-09e0-4435-a850-0613d778897b', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 3, 'image', 'https://ibb.co/5nb0Rvx');

-- Inserción de datos en la tabla post con el campo title
insert into post (id, uuid, institution_id, user_id, comment_config_id, content_id, post_date, title)
    values
    (101, '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '77f803b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-06-25T10:00:22', 'Primera publicación'),
    (102, '7h3ab4e8-0856-4aad-b3aa-747e2dba76d9', '77f803b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-09-18T15:07:22', 'Segunda publicación'),
    (103, '2k9db4e8-0856-4aad-b3aa-747e2dba76d9', '77f803b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-21T14:21:22', 'Tercera publicación'),
    (104, '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9', '77f803b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-06T11:35:22', 'Cuarta publicación');
