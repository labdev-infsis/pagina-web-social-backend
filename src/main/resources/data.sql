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
    (104, '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (105, '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (106, '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (107, '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (108, '4g9dn2m6-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (109, '9vm3n7a3-9a6c-4d9e-aea7-da7e80bd5c6f');

insert into text (id, uuid, content_id, text)
    values(100, '3m22c948-o3j9-4e39-92a6-fa761f2410a7', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'El Jefe del Departamento de Inform\00E1tica y Sistemas de la Facultad de Ciencias y Tecnolog\00EDa tiene el placer de invitarle a un evento especial:', CHR(10), U&'\0022Presentaci\00F3n de los Prototipos de la P\00E1gina Web Social Para los Departamentos de la Facultad de Ciencias y Tecnolog\00EDa - UMSS\0022', CHR(10), U&'\+01F4C5Fecha: Jueves, 31 de octubre de 2024', CHR(10), U&'\+01F550Hora: 10:00 am', CHR(10), U&'\+01F4CDLugar: Auditorio Palacio de la Ciencia y Cultura, Facultad de Ciencias y Tecnolog\00EDa', CHR(10), U&'La disertaci\00F3n estar\00E1 a cargo del MSc. Lic. Valent\00EDn Laime Zapata y los estudiantes de pr\00E1ctica empresarial.')),
    (101, '19b6c948-f5f4-4e39-92a6-fa761f2410a7', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'\+01F4E3 \00A1Inicio Confirmado! Curso de Visual Basic\+01F4E3', CHR(10), U&'\00A1Atenci\00F3n \00FAltimos cupos!')),
    (102, '52o7c948-f5f4-4e39-92a6-fa761f2410a7', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'\+01F4CC El Departamento de Inform\00E1tica y Sistemas, oferta Cursos de formaci\00F3n continua.', CHR(10), U&'Curso: Encaminamiento de Informaci\00F3n en Redes')),
    (103, '25x4c948-f5f4-4e39-92a6-fa761f2410a7', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4CC Distribuci\00F3n de mesas y Aulas para las carreras de INFORM\00C1TICA y SISTEMAS.'),
    (104, '99c1c948-f5f4-4e39-92a6-fa761f2410a7', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', CONCAT(U&'\+01F4CC El Departamento de Inform\00E1tica y Sistemas, oferta Cursos de formaci\00F3n continua.', CHR(10), U&'\+01F4E3 Apertura de un tercer Grupo \+01F4E3', CHR(10), U&'Curso: Encaminamiento de Informaci\00F3n en Redes', CHR(10), U&'\+01F4C5Inicio de clases: 18 de NOV', CHR(10), U&'\+01F550Hora: 15:45 - 17:15 G3', CHR(10), U&'\+01F4BBModalidad: Presencial', CHR(10), U&'Se otorgar\00E1 CERTIFICADO CON VALOR CURRICULAR', CHR(10), U&'Inscripciones: Laboratorio de Computo INF-SIS', CHR(10), U&'Duraci\00F3n: 2 Semanas', CHR(10), U&'Requisitos: Presentar una Fotocopia de C.I.')),
    (105, '4mx8c948-f5f4-4e39-92a6-fa761f2410a7', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F3C6 \00A1Prep\00E1rate para la SanSi Cup 2024! \+01F3C6'),
    (106, '6vk5x297-f5f4-4e39-92a6-fa761f2410a7', '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F389 \00A1Gran Rifa Universitaria! \+01F389'),
    (107, '2cm9z8n5-f5f4-4e39-92a6-fa761f2410a7', '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f', U&'Solicitud de pasant\00EDa Direcci\00F3n General de Aeron\00E1utica Civil Gesti\00F3n 2025');

insert into media (id, uuid, content_id, number, file_type, file_path)
values(100, '17ucf8a1-09e0-4435-a850-0613d778897b', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/3fddcbec-4bbe-411d-949a-4e4ab9fa986a'),
    (101, '81k7f8a1-09e0-4435-a850-0613d778897b', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/6e8fb6b1-fa25-43c8-a4f7-97c5fe8d8286'),
    (102, '24l5f8a1-09e0-4435-a850-0613d778897b', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/e970d0cc-a76f-4884-9d05-574316eb7f94'),
    (103, '79a3f8a1-09e0-4435-a850-0613d778897b', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/eec636b7-f44b-4bac-b471-aacda7566a01'),
    (104, '38j1f8a1-09e0-4435-a850-0613d778897b', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/8dce74bc-bf71-4d65-8c0d-f447dfa0ad21'),
    (105, '2k9zf8a1-09e0-4435-a850-0613d778897b', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/d07799d0-e0ac-4da2-b1fe-c36387f3e0b8'),
    (106, '6m1vf8a1-09e0-4435-a850-0613d778897b', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 2, 'image', 'http://localhost:9090/api/v1/images/44251647-1ea0-4e8a-bbbf-fba398448519'),
    (107, '17b9m4z3-09e0-4435-a850-0613d778897b', '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/8c017615-a405-4b01-a9bd-7796710552ba'),
    (108, '5jc8x7v4-09e0-4435-a850-0613d778897b', '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'image', 'http://localhost:9090/api/v1/images/703a9df8-fb13-4e02-acf0-12726d175380'),
    (109, '8xm4v1n3-09e0-4435-a850-0613d778897b', '4g9dn2m6-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'document', 'http://localhost:9090/api/v1/documents/2787bda6-2ce8-490f-8474-896ae931a9bb'),
    (110, '3v7c2gs8-09e0-4435-a850-0613d778897b', '9vm3n7a3-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'document', 'http://localhost:9090/api/v1/documents/8ee4bbee-7d08-4276-b555-dbae6b0872c3');

insert into post (id, uuid, institution_id, user_id, comment_config_id, content_id, post_date)
    values(100, '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635','875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-30T12:52:22'),
        (101, '7h3ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-31T07:34:22'),
        (102, '2k9db4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-07T16:38:22'),
        (103, '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-11T07:05:22'),
        (104, '3g9ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-14T09:21:22'),
        (105, '5n1ib4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-15T09:59:12'),
        (106, '9v4mb4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-27T15:19:26'),
        (107, '1ib6k3c5-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-05T10:14:18'),
        (108, '4jc8m3b6-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '4g9dn2m6-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-05T11:53:12'),
        (109, '2ox7z5n1-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '9vm3n7a3-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-05T11:53:15');

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

insert into social_network (id, uuid, institution_id, name, link)

    values(100, 'm2c5z1l3-4880-4055-987c-087f1b1f6635', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'Facebook', 'https://www.facebook.com/DptoInformaticaSistemas'),
    (101, 'b2n6s9h4-4880-4055-987c-087f1b1f6635', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'Telegram', 'https://t.me/isumss');

insert into groups (id, uuid, name, status)
    values (100, '5ad28ac2-bd48-4fc5-8be0-92cd24448708', 'Fijados', 'CREATED'),
    (101, 'd7cc6017-1a74-40e5-9d9c-41d4d271259b', 'Destacados', 'CREATED');

insert into post_group (group_id, post_id)
    values ('5ad28ac2-bd48-4fc5-8be0-92cd24448708', '4jc8m3b6-0856-4aad-b3aa-747e2dba76d9'),
    ('5ad28ac2-bd48-4fc5-8be0-92cd24448708', '2ox7z5n1-0856-4aad-b3aa-747e2dba76d9'),
    ('5ad28ac2-bd48-4fc5-8be0-92cd24448708', '1ib6k3c5-0856-4aad-b3aa-747e2dba76d9'),
    ('d7cc6017-1a74-40e5-9d9c-41d4d271259b', '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9'),
    ('d7cc6017-1a74-40e5-9d9c-41d4d271259b', '1ib6k3c5-0856-4aad-b3aa-747e2dba76d9');

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


INSERT INTO replies (reply_id, uuid, user_id, comment_id, content, created_at)
VALUES (20,'f8d2a52b-c1dc-4919-8610-76057df842c0', 100,10,
        'No estoy de acuerdo con tu comentario',
        NOW());
INSERT INTO replies (reply_id, uuid, user_id, comment_id, content, created_at)
VALUES (30,'b0315608-e21b-4096-8cb9-3cf3b518892b', 100,10,
        'tampoco estoy de acuerdo con tu comentario',
        NOW());

INSERT INTO reply_reactions (id, uuid, user_id, reply_id, emoji_type_id, reaction_date)
VALUES (10,'819d23be-25c9-49c8-9e8a-d5070bbf57ef', 100,20,101,
        NOW());
INSERT INTO reply_reactions (id, uuid, user_id, reply_id, emoji_type_id, reaction_date)
VALUES (11,'ce87a375-8fe4-4c98-a86d-2d02e0d6d6d7', 100,20,101,
        NOW());