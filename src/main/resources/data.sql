insert into role(id_role, name)
    values(1, 'ADMIN'),
    (2, 'STUDENT');

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_portada_path, photo_profile_path)
    values(100, 'j5818068-9280-4055-987c-087f1b1f6635', 'Alicia', 'Mercado Sandoval', 'alicia.mercado@fcyt.umss.edu.bo', null, '$2a$10$d2Re5HES0exoWQkbZ9IKSukZZI3kLokhOWJfRI1LZcuE/WN.zYV6e', null, null );

insert into users (id_user, uuid, name, last_name, email, phone, password, photo_portada_path, photo_profile_path)
    values(110, 'a0818068-4880-4055-987c-087f1b1f6635', 'Sergio', 'Salazar Velasco', '202004185@est.umss.edu', null, '$2a$10$2ohp0csjbBWQkcidcmGeyuaP21iST1m2Ps7yBUa6uoViJ9eUYOlBi', null, null );

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


