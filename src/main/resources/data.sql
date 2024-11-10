
insert into role(id_role, name)
    values(1, 'ADMIN'),
    (2, 'STUDENT');

insert into comment_config (id, uuid, configuration)
    values(1, '875d7d7f-7a1c-4b77-ab63-77a9f76759d0', 'Todos pueden comentar'),
    (2, '587d7d7f-5g3n-4b77-cf98-77a9h46759d0', 'Nadie puede comentar'),
    (3, '492d7d7f-9f4s-8g45-hy34-77a9h46759d0', 'Comentarios con moderador');

insert into institution (id, uuid, name, description, location, category, email, phone, url, logo_url, background_url)
    values(1, '77f803b4-f63b-4c4a-be05-eae84cef0c0c',
            'Ingeniería Eléctrica',
            'Servicios, actividades y noticias de la carrera de Ingeniería Eléctrica de la FCyT(UMSS)',
            'Campus Central UMSS (frente al Parque la Torre) Cochabamba, Bolivia', 
            'Universidad', 
            'electrica@fcyt.umss.edu.bo',
            '+591 4 4543232',
            'electrica.fcyt.umss.edu.bo',
            '/images/77f803b4o4534d',
            '/images/eae84cef0c0cd3');