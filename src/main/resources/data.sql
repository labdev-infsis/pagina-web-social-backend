insert into role(id_role, name)
    values(1, 'ADMIN'),
    (2, 'STUDENT');

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_portada_path, photo_profile_path)
    values(100, 'j5818068-9280-4055-987c-087f1b1f6635', 'Alicia', 'Mercado Sandoval', 'alicia.mercado@fcyt.umss.edu.bo', '79235628', '$2a$10$d2Re5HES0exoWQkbZ9IKSukZZI3kLokhOWJfRI1LZcuE/WN.zYV6e', null, 'https://i.ibb.co/VMVY21Q/Alicia-Mercado.png' );

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_portada_path, photo_profile_path)
    values(110, 'a0818068-4880-4055-987c-087f1b1f6635', 'Sergio', 'Salazar Velasco', '202004185@est.umss.edu', '62954271', '$2a$10$2ohp0csjbBWQkcidcmGeyuaP21iST1m2Ps7yBUa6uoViJ9eUYOlBi', null, 'https://i.ibb.co/2S6d8Fx/Sergio-Salazar.png' );

insert into user_roles (user_id, role_id)
    values(100, 1),(110, 2);

insert into comment_config (id, uuid, configuration)
    values(20, '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', 'Todos pueden comentar'),
    (21, '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', 'Nadie puede comentar'),
    (22, '492d7d7f-9f4s-8g45-hy34-77a9h46759d0', 'Comentarios con moderador');

insert into institution (id, uuid, name, description, location, category, email, phone, url, logo_url, background_url)
    values(10, '93j203b4-f63b-4c4a-be05-eae84cef0c0c',
        unistr('Departamento de Inform\00E1tica y Sistemas'),
        unistr('Servicios, actividades y noticias del Departamento de Inform\00E1tica - Sistemas de la FCyT de la UMSS'),
        'Campus Central UMSS (Calle Sucre, ingreso vehicular), Cochabamba, Bolivia',
        'Universidad',
        'departamento.inf-sis@fcyt.umss.edu.bo',
        '(591)-(4)233719',
        'cs.umss.edu.bo',
        'https://i.ibb.co/Snf5jz0/Depa-Inf-Sis-Logo.png',
        'https://i.ibb.co/Kw4Lk5w/Default-Back-Depa-Inf-Sis.jpg'),
    (11, '77f803b4-f63b-4c4a-be05-eae84cef0c0c',
        unistr('Ingenier\00EDa El\00E9ctrica'),
        unistr('Servicios, actividades y noticias de la carrera de Ingenier\00EDa El\00E9ctrica de la FCyT(UMSS)'),
        'Campus Central UMSS (frente al Parque la Torre) Cochabamba, Bolivia',
        'Universidad',
        'electrica@fcyt.umss.edu.bo',
        '+591 4 4543232',
        'electrica.fcyt.umss.edu.bo',
        'https://i.ibb.co/5KFg7qb/electrica-fcyt.png',
        'https://i.ibb.co/nsHw0n6/Default-Background.jpg');

insert into content (id, uuid)
    values(100, '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (101, '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (102, '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (103, '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (104, '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f');

insert into text (id, uuid, content_id, text)
    values(100, '3m22c948-o3j9-4e39-92a6-fa761f2410a7', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'El Jefe del Departamento de Inform\00E1tica y Sistemas de la Facultad de Ciencias y Tecnolog\00EDa tiene el placer de invitarle a un evento especial:', CHR(10), U&'\0022Presentaci\00F3n de los Prototipos de la P\00E1gina Web Social Para los Departamentos de la Facultad de Ciencias y Tecnolog\00EDa - UMSS\0022', CHR(10), U&'\+01F4C5Fecha: Jueves, 31 de octubre de 2024', CHR(10), U&'\+01F550Hora: 10:00 am', CHR(10), U&'\+01F4CDLugar: Auditorio Palacio de la Ciencia y Cultura, Facultad de Ciencias y Tecnolog\00EDa', CHR(10), U&'La disertaci\00F3n estar\00E1 a cargo del MSc. Lic. Valent\00EDn Laime Zapata y los estudiantes de pr\00E1ctica empresarial.')),
    (101, '19b6c948-f5f4-4e39-92a6-fa761f2410a7', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'\+01F4E3Inicio Confirmado! Curso de Visual Basic\+01F4E3', CHR(10), U&'\00A1Atenci\00F3n \00FAltimos cupos!')),
    (102, '52o7c948-f5f4-4e39-92a6-fa761f2410a7', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'\+01F4CC El Departamento de Inform\00E1tica y Sistemas, oferta Cursos de formaci\00F3n continua.', CHR(10), U&'Curso: Encaminamiento de Informaci\00F3n en Redes')),
    (103, '25x4c948-f5f4-4e39-92a6-fa761f2410a7', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4CC Distribuci\00F3n de mesas y Aulas para las carreras de INFORM\00C1TICA y SISTEMAS.'),
    (104, '99c1c948-f5f4-4e39-92a6-fa761f2410a7', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'\+01F4CC El Departamento de Inform\00E1tica y Sistemas, oferta Cursos de formaci\00F3n continua.', CHR(10), U&'\+01F4E3 Apertura de un tercer Grupo \+01F4E3', CHR(10), U&'Curso: Encaminamiento de Informaci\00F3n en Redes', CHR(10), U&'\+01F4C5Inicio de clases: 18 de NOV', CHR(10), U&'\+01F550Hora: 15:45 - 17:15 G3', CHR(10), U&'\+01F4BBModalidad: Presencial', CHR(10), U&'Se otorgar\00E1 CERTIFICADO CON VALOR CURRICULAR', CHR(10), U&'Inscripciones: Laboratorio de Computo INF-SIS', CHR(10), U&'Duraci\00F3n: 2 Semanas', CHR(10), U&'Requisitos: Presentar una Fotocopia de C.I.'));

insert into media (id, uuid, content_id, number, file_type, file_path)
values(100, '17ucf8a1-09e0-4435-a850-0613d778897b', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://i.ibb.co/VQxxtQq/Present-Prototipos.jpg'),
    (101, '81k7f8a1-09e0-4435-a850-0613d778897b', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://i.ibb.co/KW2ggF8/Curso-Visual-Basic.jpg'),
    (102, '24l5f8a1-09e0-4435-a850-0613d778897b', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://i.ibb.co/0F6xL5j/Curso-Enc-Redes.jpg'),
    (103, '79a3f8a1-09e0-4435-a850-0613d778897b', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://i.ibb.co/F6hn3kk/Distrib-Mesas.jpg'),
    (104, '38j1f8a1-09e0-4435-a850-0613d778897b', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'https://i.ibb.co/0rT5WJ5/Encaminamiento-Redes-G3.jpg');

insert into post (id, uuid, institution_id, user_id, comment_config_id, content_id, post_date)
    values(100, '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635','875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-30T12:52:22'),
        (101, '7h3ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-31T07:34:22'),
        (102, '2k9db4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-07T16:38:22'),
        (103, '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-11T07:05:22'),
        (104, '3g9ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-14T09:21:22');

insert into emoji_type (id, uuid, emoji_name, emoji_code)
    values(100, '3f696a78-c73f-475c-80a6-f5a858648af1', 'thumbs-up', U&'\+01F44D'),
    (101, '7v236a78-c73f-475c-80a6-f5a858648af1', 'red-heart', U&'\+002764\+00FE0F'),
    (102, 'n1596a78-c73f-475c-80a6-f5a858648af1', 'crying-face', U&'\+01F622'),
    (103, '4c806a78-c73f-475c-80a6-f5a858648af1', 'angry-face', U&'\+01F620');

insert into post_reaction (id, uuid, user_id, post_id, emoji_type_id, reaction_date)
    values(100, 'c31d5d56-b6f5-41a4-97d2-e797a6b0aa0e', 'a0818068-4880-4055-987c-087f1b1f6635', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-10-30T15:23:22'),
    (101, 'e4l98068-4880-4055-987c-087f1b1f6635', 'a0818068-4880-4055-987c-087f1b1f6635', '2k9db4e8-0856-4aad-b3aa-747e2dba76d9', 'n1596a78-c73f-475c-80a6-f5a858648af1', '2024-11-07T19:50:22'),
    (102, 'i7258068-4880-4055-987c-087f1b1f6635', 'a0818068-4880-4055-987c-087f1b1f6635', '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-11-12T12:10:22'),
    (103, 'j1338068-4880-4055-987c-087f1b1f6635', 'a0818068-4880-4055-987c-087f1b1f6635', '3g9ab4e8-0856-4aad-b3aa-747e2dba76d9', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-11-14T20:14:22');



INSERT INTO comment (id, uuid, user_id, post_id, content, created_date, last_modified_date)
VALUES (10,'d3b07384-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 100,
        (SELECT id FROM post WHERE uuid = '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9'),
        'Este es un comentario de prueba',
        NOW(),
        NOW());
INSERT INTO comment (id, uuid, user_id, post_id, content, created_date, last_modified_date)
VALUES (11,'d3b07384-d9a0-4c4a-8d9d-0c9a9f8b7c7c', 100,
        (SELECT id FROM post WHERE uuid = '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9'),
        'Este es un comentario de felicitaciones',
        NOW(),
        NOW());
