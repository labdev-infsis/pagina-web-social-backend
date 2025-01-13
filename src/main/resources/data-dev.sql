insert into role(id_role, name)
    values(1, 'ADMIN'),
    (2, 'STUDENT'),
    (3, 'MODERATOR');

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_cover_path, photo_profile_path)
    values(100, 'j5818068-9280-4055-987c-087f1b1f6635', 'Alicia', 'Mercado Sandoval', 'alicia.mercado@fcyt.umss.edu.bo', '79235628', '$2a$10$d2Re5HES0exoWQkbZ9IKSukZZI3kLokhOWJfRI1LZcuE/WN.zYV6e', null, 'http://localhost:9090/api/v1/images/users/fc6cae6d-a3d4-48dd-8b3a-6572944dab5d' ),
       (101, 'eab72365-d8c1-45df-9b48-274f64c65b86', 'Valentin', 'Laime Zapata', 'valentin.laime@umss.edu.bo', '70755958', '$2a$10$g46noOikL88uT7pNfj4Q/OVOyBtgfh6yFqixUxd4lww3UZrLeIOly', null, 'http://localhost:9090/api/v1/images/users/e867fa32-75b7-4c3c-8943-4a2808c10ac0'),
       (110, 'a0818068-4880-4055-987c-087f1b1f6635', 'Sergio', 'Salazar Velasco', '202004185@est.umss.edu', '62954271', '$2a$10$APlIB7eBowR1Q00m.dEEZOKy3WzzWxtcpXogLThPH.MVAlx0mMwui', null, 'http://localhost:9090/api/v1/images/users/13035150-f880-4475-808c-26086775ad47' ),
       (111, '9bae1fe0-2c56-4091-883d-15458e051500', 'Emely', 'Fernandez Soliz', '202004181@est.umss.edu', '69577142', '$2a$10$ElXlBz1WzNDhanqtCmszb.bub/iKGcNQgcbr31MQe9wjsiim7Q5zG', null, 'http://localhost:9090/api/v1/images/users/733b01ba-198e-42db-b9ca-d7ace9a7abad'),
       (112, '844b324d-2f5e-41c9-b726-0149eeb01157', 'Javier', 'Lopez Canedo', '202394182@est.umss.edu', '71852821', '$2a$10$Gm0O/HK8qXeqa/XPvrm4/uAQj2.G3XdKOHg8XTxG4Gif1UYDPcbEa', null, 'http://localhost:9090/api/v1/images/users/06b8fbe5-9d91-422b-bd20-6d89d23e5608'),
       (113, '2e121a51-24b5-4aad-92c2-150997ec4266', 'Leonardo', 'Beltran Ramirez', '202120735@est.umss.edu', '75891148', '$2a$10$vs8E1jwz6Z3BBitXHP.Y/uVQ0pNkqHhXf0oQCFH7Nf/rjccHL2lCO', null, 'http://localhost:9090/api/v1/images/users/b5de2b13-4720-41e7-87d2-d31abd590fac'),
       (114, 'c332c4ff-49e8-4e0e-a7f4-d59907d8cda3', 'Valeria', 'Gonzales Vargas', 'svaleria.gonzales.vargas@gmail.com', '78823541', '$2a$10$tt2avNxEODCYrI9qHFqr5elrI5O87dVRNQZGUgAHVMUi8XtWnwXE.', null, 'http://localhost:9090/api/v1/images/users/5e9f9c0d-4c31-4335-a11e-b9c7f6f8a2b1'),
       (115, '9f32985a-f108-4b19-9bda-cab7c501ae68', 'Jose', U&'Monta\00F1o Laura', '202001823@est.umss.edu', '72566218', '$2a$10$6HOIAlIZ2.KXIi8qL1AHxOIs3zHYydtV3QnZ.wJ9wrr18qhJEi576', null, 'http://localhost:9090/api/v1/images/users/1bed8052-ba51-41c2-88b5-e39e78dbf420'),
       (116, 'ab6e1a7f-4494-4251-8177-dc5c4fe18740', 'Marcos', 'Illanes Martinez', '201800513@est.umss.edu', '74269527', '$2a$10$96.BNIsnUH7u.eVTeWi7Ju/exv59yfngw.TdVVOv5ladZ87KBZLaK', null, 'http://localhost:9090/api/v1/images/users/28e3d103-76e8-4b0d-b04c-cf2e8821cf6e'),
       (117, 'f8d1c969-af76-4161-b0d7-9c2dfc47e75c', 'Adriana', 'Boza Ruiz', '201904940@est.umss.edu', '70551293', '$2a$10$LRWTNQMxRoDEGUKNeZe86OOg7.aqxc1TE/HhGUJ01zhPBB2E1O/A.', null, 'http://localhost:9090/api/v1/images/users/f9c89556-cd27-481a-a8bd-59f4915f7dfa'),
       (118, 'd33ded75-c9f3-4fef-9762-0ba4a905efa8', 'Antonio', 'Monje Aranibar', '201604527@est.umss.edu', '62933721', '$2a$10$m8FJFWAPDKIAU8SykyKmme7FcaQqrkHtg0zczh3Gn0lSgVeTZNaAy', null, 'http://localhost:9090/api/v1/images/users/b3a64fc9-3d28-4b92-b296-2324f9c4e061'),
       (119, 'e54bd4dc-d8f6-42e9-8e94-5d56bf42416f', 'Jeyson', 'Valdivia Bernal', 'jeyson.valdivia@gmail.com', '76834814', '$2a$10$anTAKIK4bm55/HYFWVoER.zu1eNL7Iwy0UCGi3THN.wDW77WaLCKa', null, 'http://localhost:9090/api/v1/images/users/e326bdb8-6eee-4814-a877-9e063183f410'),
       (120, 'ae542968-1335-425e-a206-283c38a20190', 'Omar', 'Argenes Quispe', 'argenes77@gmail.com', '65692585', '$2a$10$bO6cOzFhvWjqRRwn7a1/xupq3LxGO/hG.7cLpS14JAbksNjBGJCjW', null, 'http://localhost:9090/api/v1/images/users/004fca51-d313-41c4-94ff-5908ddb58dc2'),
       (121, '7f9264d7-ca8d-41ca-be1f-d24c9dd244a2', 'Bianca', 'Antelo Dominguez', 'bianca.dominguez@gmail.com', '73597236', '$2a$10$QUoVqY/j3bdW5rOMO8aTY.Eaq2Ifju9a8Hm2ht7qex2ALF0tWVc1q', null, 'http://localhost:9090/api/v1/images/users/2148f392-4f16-40b6-88e7-bd0ca32bd84a'),
       (122, '7f9264d7-ca8d-41ca-be1f-d24c9dd12345', 'Zoe', 'Quispe Dominguez', 'zoedominguez@gmail.com', '73597236', '$2a$10$QUoVqY/j3bdW5rOMO8aTY.Eaq2Ifju9a8Hm2ht7qex2ALF0tWVc1q', null, 'http://localhost:9090/api/v1/images/users/2148f392-4f16-40b6-88e7-bd0ca32bd84a');


insert into user_roles (user_id, role_id)
     values(100, 1),
     (101, 1),
     (110, 2),
     (111, 2),
     (112, 2),
     (113, 2),
     (114, 2),
     (115, 2),
     (116, 2),
     (117, 2),
     (118, 2),
     (119, 2),
     (120, 2),
     (121, 2),
     (122, 3);
insert into comment_config (id, uuid, name, configuration_type)
    values(20, '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', 'Todos pueden comentar', 'FREE_COMMENTS'),
    (21, '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', 'Nadie puede comentar', 'RESTRICTED_COMMENTS'),
    (22, '492d7d7f-9f4s-8g45-hy34-77a9h46759d0', 'Comentarios con moderador', 'MODERATED_COMMENTS');

insert into institution (id, uuid, name, description, location, category, email, phone, url, logo_url, background_url)
    values(10, '93j203b4-f63b-4c4a-be05-eae84cef0c0c',
        unistr('Departamento de Inform\00E1tica y Sistemas'),
        unistr('Servicios, actividades y noticias del Departamento de Inform\00E1tica - Sistemas de la FCyT de la UMSS'),
        'Campus Central UMSS (Calle Sucre, ingreso vehicular), Cochabamba, Bolivia',
        'Universidad',
        'departamento.inf-sis@fcyt.umss.edu.bo',
        '(591)-(4)233719',
        'cs.umss.edu.bo',
        'http://localhost:9090/api/v1/images/inst-profile/012aa435-6925-4ba8-ba90-af529c8344ef',
        'http://localhost:9090/api/v1/images/inst-cover/1cf06b76-d37a-47a1-8c73-6c9e4df0e8fe'),
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
    (109, '9vm3n7a3-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (110, 'n5x8f0j2-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (111, 'g7k3v8d5-9a6c-4d9e-aea7-da7e80bd5c6f'),
    (112, 'k3n6m1a9-9a6c-4d9e-aea7-da7e80bd5c6f');

insert into text (id, uuid, content_id, text)
    values(100, '3m22c948-o3j9-4e39-92a6-fa761f2410a7', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'El Jefe del Departamento de Inform\00E1tica y Sistemas de la Facultad de Ciencias y Tecnolog\00EDa tiene el placer de invitarle a un evento especial: <br>\0022Presentaci\00F3n de los Prototipos de la P\00E1gina Web Social Para los Departamentos de la Facultad de Ciencias y Tecnolog\00EDa - UMSS\0022 <br>\+01F4C5Fecha: Jueves, 31 de octubre de 2024 <br>\+01F550Hora: 10:00 am <br>\+01F4CDLugar: Auditorio Palacio de la Ciencia y Cultura, Facultad de Ciencias y Tecnolog\00EDa <br>La disertaci\00F3n estar\00E1 a cargo del MSc. Lic. Valent\00EDn Laime Zapata y los estudiantes de pr\00E1ctica empresarial.'),
    (101, '19b6c948-f5f4-4e39-92a6-fa761f2410a7', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4E3 \00A1Inicio Confirmado! Curso de Visual Basic\+01F4E3 <br>\00A1Atenci\00F3n \00FAltimos cupos!'),
    (102, '52o7c948-f5f4-4e39-92a6-fa761f2410a7', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4CC El Departamento de Inform\00E1tica y Sistemas, oferta Cursos de formaci\00F3n continua. <br>Curso: Encaminamiento de Informaci\00F3n en Redes'),
    (103, '25x4c948-f5f4-4e39-92a6-fa761f2410a7', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4CC Distribuci\00F3n de mesas y Aulas para las carreras de INFORM\00C1TICA y SISTEMAS.'),
    (104, '99c1c948-f5f4-4e39-92a6-fa761f2410a7', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4CC El Departamento de Inform\00E1tica y Sistemas, oferta Cursos de formaci\00F3n continua. <br>\+01F4E3 Apertura de un tercer Grupo \+01F4E3 <br>Curso: Encaminamiento de Informaci\00F3n en Redes <br>\+01F4C5Inicio de clases: 18 de NOV <br>\+01F550Hora: 15:45 - 17:15 G3 <br>\+01F4BBModalidad: Presencial <br>Se otorgar\00E1 CERTIFICADO CON VALOR CURRICULAR <br>Inscripciones: Laboratorio de Computo INF-SIS <br>Duraci\00F3n: 2 Semanas <br>Requisitos: Presentar una Fotocopia de C.I.'),
    (105, '4mx8c948-f5f4-4e39-92a6-fa761f2410a7', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F3C6 \00A1Prep\00E1rate para la SanSi Cup 2024! \+01F3C6'),
    (106, '6vk5x297-f5f4-4e39-92a6-fa761f2410a7', '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F389 \00A1Gran Rifa Universitaria! \+01F389 <br>Apoya a los equipos ICPC de la UMSS que se presentaron en Tarija <br>Por solo 10 Bs. estar\00E1s  participando en incre\00EDbles premios: <ul> <li> 1 Cup\00F3n de hasta 49 USD para cursos en Cursera </li> <li> 3 Aud\00EDfonos </li> <li> 2 Mouse Gamer </li> <li> 4 Memorias USB </li> </ul> <br> Sorteo: Miércoles 4 de diciembre.<br>Lugar: Auditorio de la FCyT "Palacio de Ciencia y la Cultura" <br> Puntos de Venta:<br>Comunícate al : 591 76995925'),
    (107, '2cm9z8n5-f5f4-4e39-92a6-fa761f2410a7', '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f', U&'Solicitud de pasant\00EDa Direcci\00F3n General de Aeron\00E1utica Civil Gesti\00F3n 2025'),
    (108, 'h4k1c9z3-f5f4-4e39-92a6-fa761f2410a7', 'n5x8f0j2-9a6c-4d9e-aea7-da7e80bd5c6f', U&'\+01F4E2 CONVOCATORIA A CONCURSO DE M\00C9RITOS Y EXAMEN DE COMPETENCIA PARA LA PROVISI\00D3N DE AUXILIARES SERVICIO/LABORATORIO PARA EL DEPARTAMENTO DE INFORM\00C1TICA Y SISTEMAS <br>Gesti\00F3n Acad\00E9mica 2025 <br>\+01F4BBDescargar convocatoria: https://acortar.link/xe4RYC <br> Ingresar con cuenta Institucional'),
    (109, 'l5n2f9n4-f5f4-4e39-92a6-fa761f2410a7', 'g7k3v8d5-9a6c-4d9e-aea7-da7e80bd5c6f', U&'TOMAR EN CUENTA <br> Pr\00F3ximamente CONVOCATORIA PARA PROVISI\00D3N DE AUXILIARES DE DOCENCIA'),
    (110, 'p9n3a8h2-f5f4-4e39-92a6-fa761f2410a7', 'k3n6m1a9-9a6c-4d9e-aea7-da7e80bd5c6f', U&'CRONOGRAMA EXAMEN DE MESA 2da OPCI\00D3N Semestre II/2024 <br> Materias que corresponden al Departamento de Inform\00E1tica y Sistemas');


insert into media (id, uuid, content_id, number, file_name, file_type, file_path)
values(100, '17ucf8a1-09e0-4435-a850-0613d778897b', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img01', 'image', 'http://localhost:9090/api/v1/images/posts/3fddcbec-4bbe-411d-949a-4e4ab9fa986a'),
    (101, '81k7f8a1-09e0-4435-a850-0613d778897b', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img02', 'image', 'http://localhost:9090/api/v1/images/posts/6e8fb6b1-fa25-43c8-a4f7-97c5fe8d8286'),
    (102, '24l5f8a1-09e0-4435-a850-0613d778897b', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img03', 'image', 'http://localhost:9090/api/v1/images/posts/e970d0cc-a76f-4884-9d05-574316eb7f94'),
    (103, '79a3f8a1-09e0-4435-a850-0613d778897b', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img04', 'image', 'http://localhost:9090/api/v1/images/posts/eec636b7-f44b-4bac-b471-aacda7566a01'),
    (104, '38j1f8a1-09e0-4435-a850-0613d778897b', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img05', 'image', 'http://localhost:9090/api/v1/images/posts/8dce74bc-bf71-4d65-8c0d-f447dfa0ad21'),
    (105, '2k9zf8a1-09e0-4435-a850-0613d778897b', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img06', 'image', 'http://localhost:9090/api/v1/images/posts/d07799d0-e0ac-4da2-b1fe-c36387f3e0b8'),
    (106, '6m1vf8a1-09e0-4435-a850-0613d778897b', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', 2, 'img07', 'image', 'http://localhost:9090/api/v1/images/posts/44251647-1ea0-4e8a-bbbf-fba398448519'),
    (107, '17b9m4z3-09e0-4435-a850-0613d778897b', '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img08', 'image', 'http://localhost:9090/api/v1/images/posts/8c017615-a405-4b01-a9bd-7796710552ba'),
    (108, '5jc8x7v4-09e0-4435-a850-0613d778897b', '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img09', 'image', 'http://localhost:9090/api/v1/images/posts/703a9df8-fb13-4e02-acf0-12726d175380'),
    (109, '8xm4v1n3-09e0-4435-a850-0613d778897b', '4g9dn2m6-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img10', 'image', 'http://localhost:9090/api/v1/images/posts/e30629ef-d0e2-47f2-8fbb-cb3344e4f319'),
    (110, '3v7c2gs8-09e0-4435-a850-0613d778897b', '9vm3n7a3-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img11', 'image', 'http://localhost:9090/api/v1/images/posts/b96f6ec8-655f-45de-943b-107300a8e25a'),
    (111, 'l2n5c0f7-09e0-4435-a850-0613d778897b', 'n5x8f0j2-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img12', 'image', 'http://localhost:9090/api/v1/images/posts/3bcb7ab3-af96-4342-b342-0e7b841d76a5'),
    (112, 'j4v9e6l1-09e0-4435-a850-0613d778897b', 'g7k3v8d5-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img13', 'image', 'http://localhost:9090/api/v1/images/posts/b65f8646-ebf4-4bec-9403-df64908ac870'),
    (113, 'h6m2s9c7-09e0-4435-a850-0613d778897b', 'k3n6m1a9-9a6c-4d9e-aea7-da7e80bd5c6f', 1, 'img14', 'image', 'http://localhost:9090/api/v1/images/posts/18e154d3-c772-4dd0-ac24-847cefc0679a');


insert into post (id, uuid, institution_id, user_id, comment_config_id, content_id, post_date)
    values(100, '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635','875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '1e5294f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-30T12:52:22'),
        (101, '7h3ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '6i2494f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-10-31T07:34:22'),
        (102, '2k9db4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', '8f3194f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-07T16:38:22'),
        (103, '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '5l6n94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-11T07:05:22'),
        (104, '3g9ab4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '2v4z94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-14T09:21:22'),
        (105, '5n1ib4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '7o1b94f8-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-15T09:59:12'),
        (106, '9v4mb4e8-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '492d7d7f-9f4s-8g45-hy34-77a9h46759d0', '3m8c52v4-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-11-27T15:19:26'),
        (107, '1ib6k3c5-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '1om5x7n2-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-05T10:14:18'),
        (108, '4jc8m3b6-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '4g9dn2m6-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-05T11:53:12'),
        (109, '2ox7z5n1-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', '9vm3n7a3-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-05T11:53:15'),
        (110, 'l6b4m1z9-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '492d7d7f-9f4s-8g45-hy34-77a9h46759d0', 'n5x8f0j2-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-10T10:01:03'),
        (111, 'k5b3n5m1-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', 'g7k3v8d5-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-10T10:51:03'),
        (112, 'f8j2n5m6-0856-4aad-b3aa-747e2dba76d9', '93j203b4-f63b-4c4a-be05-eae84cef0c0c', 'j5818068-9280-4055-987c-087f1b1f6635', '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', 'k3n6m1a9-9a6c-4d9e-aea7-da7e80bd5c6f', '2024-12-11T16:47:03');


insert into emoji_type (id, uuid, emoji_name, emoji_code)
    values(100, '3f696a78-c73f-475c-80a6-f5a858648af1', 'thumbs-up', U&'\+01F44D'),
    (101, '7v236a78-c73f-475c-80a6-f5a858648af1', 'red-heart', U&'\+002764\+00FE0F'),
    (102, 'n1596a78-c73f-475c-80a6-f5a858648af1', 'crying-face', U&'\+01F622'),
    (103, '4c806a78-c73f-475c-80a6-f5a858648af1', 'angry-face', U&'\+01F620'),
    (104, 'l6m3bd82-c73f-475c-80a6-f5a858648af1', 'grinning-squinting-face', U&'\+01F606'),
    (105, 'c5n1m4f0-c73f-475c-80a6-f5a858648af1', 'astonished-face', U&'\+01F632');

insert into post_reaction (id, uuid, user_id, post_id, emoji_type_id, reaction_date)
    values(100, 'c31d5d56-b6f5-41a4-97d2-e797a6b0aa0e', 'd33ded75-c9f3-4fef-9762-0ba4a905efa8', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-10-30T15:23:22'),
    (101, 'e4l98068-4880-4055-987c-087f1b1f6635', 'a0818068-4880-4055-987c-087f1b1f6635', '2k9db4e8-0856-4aad-b3aa-747e2dba76d9', 'n1596a78-c73f-475c-80a6-f5a858648af1', '2024-11-07T19:50:22'),
    (102, 'i7258068-4880-4055-987c-087f1b1f6635', '9f32985a-f108-4b19-9bda-cab7c501ae68', '8s2ib4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-11-12T12:10:22'),
    (103, 'j1338068-4880-4055-987c-087f1b1f6635', 'ae542968-1335-425e-a206-283c38a20190', '3g9ab4e8-0856-4aad-b3aa-747e2dba76d9', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-11-14T20:14:22'),
    (104, 'l5m1n8s0-4880-4055-987c-087f1b1f6635', '9f32985a-f108-4b19-9bda-cab7c501ae68', 'k5b3n5m1-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-10T20:14:22'),
    (105, 'n5m1x9f5-4880-4055-987c-087f1b1f6635', 'd33ded75-c9f3-4fef-9762-0ba4a905efa8', 'k5b3n5m1-0856-4aad-b3aa-747e2dba76d9', 'l6m3bd82-c73f-475c-80a6-f5a858648af1', '2024-12-10T23:16:22'),
    (106, 'l5mv6a93-4880-4055-987c-087f1b1f6635', 'ab6e1a7f-4494-4251-8177-dc5c4fe18740', 'f8j2n5m6-0856-4aad-b3aa-747e2dba76d9', 'c5n1m4f0-c73f-475c-80a6-f5a858648af1', '2024-12-11T22:04:22'),
    (107, 'a8h3k1l3-4880-4055-987c-087f1b1f6635', '844b324d-2f5e-41c9-b726-0149eeb01157', 'f8j2n5m6-0856-4aad-b3aa-747e2dba76d9', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T08:27:22'),
    (108, 'e7j3j4m5-4880-4055-987c-087f1b1f6635', 'a0818068-4880-4055-987c-087f1b1f6635', 'f8j2n5m6-0856-4aad-b3aa-747e2dba76d9', 'l6m3bd82-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (109, 'y3o8m3n2-4880-4055-987c-087f1b1f6635', 'c332c4ff-49e8-4e0e-a7f4-d59907d8cda3', 'l6b4m1z9-0856-4aad-b3aa-747e2dba76d9', 'l6m3bd82-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (110, 'q8j4n6m4-4880-4055-987c-087f1b1f6635', 'e54bd4dc-d8f6-42e9-8e94-5d56bf42416f', 'l6b4m1z9-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (111, 'r5n4l2x8-4880-4055-987c-087f1b1f6635', 'ae542968-1335-425e-a206-283c38a20190', 'l6b4m1z9-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (112, 'j5b4l1y3-4880-4055-987c-087f1b1f6635', '9f32985a-f108-4b19-9bda-cab7c501ae68', 'l6b4m1z9-0856-4aad-b3aa-747e2dba76d9', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (113, 'n4o9t8j2-4880-4055-987c-087f1b1f6635', 'f8d1c969-af76-4161-b0d7-9c2dfc47e75c', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (114, 'a8h4m2c9-4880-4055-987c-087f1b1f6635', 'ab6e1a7f-4494-4251-8177-dc5c4fe18740', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (115, 'r9b3z8h3-4880-4055-987c-087f1b1f6635', '9f32985a-f108-4b19-9bda-cab7c501ae68', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (116, 'o4k4k7s8-4880-4055-987c-087f1b1f6635', 'e54bd4dc-d8f6-42e9-8e94-5d56bf42416f', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22'),
    (117, 'z8b5m2s9-4880-4055-987c-087f1b1f6635', 'c332c4ff-49e8-4e0e-a7f4-d59907d8cda3', '5f9ab4e8-0856-4aad-b3aa-747e2dba76d9', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T11:31:22');

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

insert into image_file (id, uuid, name, url_resource, status, type)
    values (100, '3fddcbec-4bbe-411d-949a-4e4ab9fa986a', 'img01', 'http://localhost:9090/api/v1/images/posts/3fddcbec-4bbe-411d-949a-4e4ab9fa986a', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (101, '6e8fb6b1-fa25-43c8-a4f7-97c5fe8d8286', 'img02', 'http://localhost:9090/api/v1/images/posts/6e8fb6b1-fa25-43c8-a4f7-97c5fe8d8286', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (102, 'e970d0cc-a76f-4884-9d05-574316eb7f94', 'img03', 'http://localhost:9090/api/v1/images/posts/e970d0cc-a76f-4884-9d05-574316eb7f94', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (103, 'eec636b7-f44b-4bac-b471-aacda7566a01', 'img04', 'http://localhost:9090/api/v1/images/posts/eec636b7-f44b-4bac-b471-aacda7566a01', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (104, '8dce74bc-bf71-4d65-8c0d-f447dfa0ad21', 'img05', 'http://localhost:9090/api/v1/images/posts/8dce74bc-bf71-4d65-8c0d-f447dfa0ad21', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (105, 'd07799d0-e0ac-4da2-b1fe-c36387f3e0b8', 'img06', 'http://localhost:9090/api/v1/images/posts/d07799d0-e0ac-4da2-b1fe-c36387f3e0b8', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (106, '44251647-1ea0-4e8a-bbbf-fba398448519', 'img07', 'http://localhost:9090/api/v1/images/posts/44251647-1ea0-4e8a-bbbf-fba398448519', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (107, '8c017615-a405-4b01-a9bd-7796710552ba', 'img08', 'http://localhost:9090/api/v1/images/posts/8c017615-a405-4b01-a9bd-7796710552ba', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (108, '703a9df8-fb13-4e02-acf0-12726d175380', 'img09', 'http://localhost:9090/api/v1/images/posts/703a9df8-fb13-4e02-acf0-12726d175380', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (109, 'e30629ef-d0e2-47f2-8fbb-cb3344e4f319', 'img10', 'http://localhost:9090/api/v1/images/posts/e30629ef-d0e2-47f2-8fbb-cb3344e4f319', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (110, 'b96f6ec8-655f-45de-943b-107300a8e25a', 'img11', 'http://localhost:9090/api/v1/images/posts/b96f6ec8-655f-45de-943b-107300a8e25a', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (111, '012aa435-6925-4ba8-ba90-af529c8344ef', 'img12', 'http://localhost:9090/api/v1/images/inst-profile/012aa435-6925-4ba8-ba90-af529c8344ef', 'SAVED_SUCCESSFULLY','image/png'),
     (112, '1cf06b76-d37a-47a1-8c73-6c9e4df0e8fe', 'img13', 'http://localhost:9090/api/v1/images/inst-cover/1cf06b76-d37a-47a1-8c73-6c9e4df0e8fe', 'SAVED_SUCCESSFULLY','image/jpg'),
     (113, '3bcb7ab3-af96-4342-b342-0e7b841d76a5', 'img14', 'http://localhost:9090/api/v1/images/posts/3bcb7ab3-af96-4342-b342-0e7b841d76a5', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (114, 'fc6cae6d-a3d4-48dd-8b3a-6572944dab5d', 'img15', 'http://localhost:9090/api/v1/images/users/fc6cae6d-a3d4-48dd-8b3a-6572944dab5d', 'SAVED_SUCCESSFULLY','image/png'),
     (115, '13035150-f880-4475-808c-26086775ad47', 'img16', 'http://localhost:9090/api/v1/images/users/13035150-f880-4475-808c-26086775ad47', 'SAVED_SUCCESSFULLY','image/png'),
     (116, 'b65f8646-ebf4-4bec-9403-df64908ac870', 'img17', 'http://localhost:9090/api/v1/images/posts/b65f8646-ebf4-4bec-9403-df64908ac870', 'SAVED_SUCCESSFULLY','image/jpeg'),
     (117, 'e867fa32-75b7-4c3c-8943-4a2808c10ac0', 'img18', 'http://localhost:9090/api/v1/images/users/e867fa32-75b7-4c3c-8943-4a2808c10ac0', 'SAVED_SUCCESSFULLY','image/png'),
     (118, '733b01ba-198e-42db-b9ca-d7ace9a7abad', 'img19', 'http://localhost:9090/api/v1/images/users/733b01ba-198e-42db-b9ca-d7ace9a7abad', 'SAVED_SUCCESSFULLY','image/png'),
     (119, '06b8fbe5-9d91-422b-bd20-6d89d23e5608', 'img20', 'http://localhost:9090/api/v1/images/users/06b8fbe5-9d91-422b-bd20-6d89d23e5608', 'SAVED_SUCCESSFULLY','image/png'),
     (120, 'b5de2b13-4720-41e7-87d2-d31abd590fac', 'img21', 'http://localhost:9090/api/v1/images/users/b5de2b13-4720-41e7-87d2-d31abd590fac', 'SAVED_SUCCESSFULLY','image/png'),
     (121, '5e9f9c0d-4c31-4335-a11e-b9c7f6f8a2b1', 'img22', 'http://localhost:9090/api/v1/images/users/5e9f9c0d-4c31-4335-a11e-b9c7f6f8a2b1', 'SAVED_SUCCESSFULLY','image/png'),
     (122, '1bed8052-ba51-41c2-88b5-e39e78dbf420', 'img23', 'http://localhost:9090/api/v1/images/users/1bed8052-ba51-41c2-88b5-e39e78dbf420', 'SAVED_SUCCESSFULLY','image/png'),
     (123, '28e3d103-76e8-4b0d-b04c-cf2e8821cf6e', 'img24', 'http://localhost:9090/api/v1/images/users/28e3d103-76e8-4b0d-b04c-cf2e8821cf6e', 'SAVED_SUCCESSFULLY','image/png'),
     (124, 'f9c89556-cd27-481a-a8bd-59f4915f7dfa', 'img25', 'http://localhost:9090/api/v1/images/users/f9c89556-cd27-481a-a8bd-59f4915f7dfa', 'SAVED_SUCCESSFULLY','image/png'),
     (125, 'b3a64fc9-3d28-4b92-b296-2324f9c4e061', 'img26', 'http://localhost:9090/api/v1/images/users/b3a64fc9-3d28-4b92-b296-2324f9c4e061', 'SAVED_SUCCESSFULLY','image/png'),
     (126, 'e326bdb8-6eee-4814-a877-9e063183f410', 'img27', 'http://localhost:9090/api/v1/images/users/e326bdb8-6eee-4814-a877-9e063183f410', 'SAVED_SUCCESSFULLY','image/png'),
     (127, '004fca51-d313-41c4-94ff-5908ddb58dc2', 'img28', 'http://localhost:9090/api/v1/images/users/004fca51-d313-41c4-94ff-5908ddb58dc2', 'SAVED_SUCCESSFULLY','image/png'),
     (128, '2148f392-4f16-40b6-88e7-bd0ca32bd84a', 'img29', 'http://localhost:9090/api/v1/images/users/2148f392-4f16-40b6-88e7-bd0ca32bd84a', 'SAVED_SUCCESSFULLY','image/png'),
     (129, '18e154d3-c772-4dd0-ac24-847cefc0679a', 'img30', 'http://localhost:9090/api/v1/images/posts/18e154d3-c772-4dd0-ac24-847cefc0679a', 'SAVED_SUCCESSFULLY', 'image/jpeg');

insert into video_file (id, uuid, name, url_resource, status, type)
    values (100, '200504be-c220-4932-9810-126fe8590a9c', 'video01', 'http://localhost:9090/api/v1/videos/posts/200504be-c220-4932-9810-126fe8590a9c', 'SAVED_SUCCESSFULLY', 'video/mp4');

INSERT INTO comment (id, uuid, user_id, post_id, content, moderated, state, created_date, last_modified_date)
VALUES (10,'d3b07384-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 113, 100, 'A que hora empieza?', false, 'VISIBLE', NOW(), NOW()),
       (11,'m4c9b0x2-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 118, 100, 'Transmitiran for facebook?', false, 'VISIBLE', NOW(), NOW()),
       (12,'z9n3d8j1-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 120, 100, 'Estare ahi', false, 'VISIBLE', NOW(), NOW()),
       (13,'f8k1b6s9-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 119, 102, 'Hay algun descuento si soy externo?', false, 'VISIBLE', NOW(), NOW()),
       (14,'v9s7h5k2-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 117, 102, 'En que horarios puedo inscribirme?', false, 'VISIBLE', NOW(), NOW()),
       (15,'l4m6v9a0-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 110, 104, 'Hay cupos para la tarde todavia?', false, 'VISIBLE', NOW(), NOW()),
       (16,'m5d8f8b1-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 116, 105, 'Donde puedo inscribirme xf', false, 'VISIBLE', NOW(), NOW()),
       (17,'d9m2f2i5-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 113, 106, 'Muy buenos premios!', true, 'PENDING_APPROVAL', NOW(), NOW()),
       (18,'i4v8m1a9-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 114, 106, 'No logre ir a la rifa, que numeros salieron premiados?', true, 'PENDING_APPROVAL', NOW(), NOW()),
       (19,'n5d9b5a8-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 117, 107, 'La fecha limite de postulacion segun la imagen ya paso XD', false, 'VISIBLE', NOW(), NOW()),
       (20,'l6m3b9f7-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 115, 110, 'Si ya fui auxiliar antes, puedo postular todavia?', true, 'PENDING_APPROVAL', NOW(), NOW());


INSERT INTO replies (reply_id, uuid, user_id, comment_id, content, created_at)
        VALUES (20,'f8d2a52b-c1dc-4919-8610-76057df842c0', 113,11, 'X2', NOW()),
       (30,'b0315608-e21b-4096-8cb9-3cf3b518892b', 114,14, 'Tengo la misma duda.', NOW()),
       (40,'l4m2x9b5-c1dc-4919-8610-76057df842c0', 116,19, 'si corrijan pues xd', NOW()),
       (41,'a9h4k7l1-c1dc-4919-8610-76057df842c0', 118,17, 'confirmo, me gane el teclado gamer.', NOW()),
       (42,'s9n5g8m2-c1dc-4919-8610-76057df842c0', 115,15, 'ayer fui en la tarde y me dijeron que se lleno xd', NOW());

INSERT INTO reply_reactions (id, uuid, user_id, reply_id, emoji_type_id, reaction_date)
        VALUES (10,'819d23be-25c9-49c8-9e8a-d5070bbf57ef', 119,20,100, NOW()),
        (11,'ce87a375-8fe4-4c98-a86d-2d02e0d6d6d7', 112,40,104, NOW()),
        (12,'l4m9sd83-8fe4-4c98-a86d-2d02e0d6d6d7', 115,40,104, NOW()),
        (13,'v9m3a8c0-8fe4-4c98-a86d-2d02e0d6d6d7', 121,30,100, NOW()),
        (14,'f3n5d9k2-8fe4-4c98-a86d-2d02e0d6d6d7', 118,30,100, NOW()),
        (15,'h5j23k69-8fe4-4c98-a86d-2d02e0d6d6d7', 113,30,100, NOW()),
        (16,'z5m2c5v6-8fe4-4c98-a86d-2d02e0d6d6d7', 117,20,105, NOW());

INSERT INTO followers (id, user_id, institution_id, followed_since)
values(1, 110, 10, NOW()),
(2, 112, 10, NOW()),
(3, 111, 10, NOW()),
(4, 113, 10, NOW()),
(5, 114, 10, NOW()),
(6, 115, 10, NOW()),
(7, 116, 10, NOW()),
(8, 117, 10, NOW()),
(9, 118, 10, NOW()),
(10, 119, 10, NOW()),
(11, 120, 10, NOW());

-- Actualizar la secuencia para la tabla followers
SELECT setval('followers_id_seq', (SELECT MAX(id) FROM followers));

insert into comment_reaction (id, uuid, user_id, comment_id, emoji_type_id, reaction_date)
    values(100, '07063785-4327-49ff-86b7-2bf1e9596290', 'a0818068-4880-4055-987c-087f1b1f6635', 'd3b07384-d9a0-4c4a-8d9d-0c9a9f8b9c9c', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-10-30T15:23:22'),
    (101, 'bce50d37-3ada-45ac-a556-a17cc33464d9', 'f8d1c969-af76-4161-b0d7-9c2dfc47e75c', 'f8k1b6s9-d9a0-4c4a-8d9d-0c9a9f8b9c9c', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-08T20:14:22'),
    (102, 'j6m3c0g5-3ada-45ac-a556-a17cc33464d9', 'd33ded75-c9f3-4fef-9762-0ba4a905efa8', 'd9m2f2i5-d9a0-4c4a-8d9d-0c9a9f8b9c9c', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-10T16:19:22'),
    (103, 'i5m2v9f4-3ada-45ac-a556-a17cc33464d9', '9f32985a-f108-4b19-9bda-cab7c501ae68', 'd9m2f2i5-d9a0-4c4a-8d9d-0c9a9f8b9c9c', '7v236a78-c73f-475c-80a6-f5a858648af1', '2024-12-11T20:46:22'),
    (104, 'z9n4d0k1-3ada-45ac-a556-a17cc33464d9', '2e121a51-24b5-4aad-92c2-150997ec4266', 'l6m3b9f7-d9a0-4c4a-8d9d-0c9a9f8b9c9c', 'l6m3bd82-c73f-475c-80a6-f5a858648af1', '2024-12-11T12:25:22'),
    (105, 'c8n3k5f9-3ada-45ac-a556-a17cc33464d9', 'c332c4ff-49e8-4e0e-a7f4-d59907d8cda3', 'l6m3b9f7-d9a0-4c4a-8d9d-0c9a9f8b9c9c', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-12T08:14:22'),
    (106, 'h6m3x9h3-3ada-45ac-a556-a17cc33464d9', 'c332c4ff-49e8-4e0e-a7f4-d59907d8cda3', 'v9s7h5k2-d9a0-4c4a-8d9d-0c9a9f8b9c9c', '3f696a78-c73f-475c-80a6-f5a858648af1', '2024-12-13T10:51:22');

