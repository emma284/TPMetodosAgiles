INSERT INTO usuario (id,apellido,contrasenia,domicilio,fecha_de_nacimiento,nombre,numero_documento,rol,sexo,tipo_de_documento,username) VALUES 
    (1, 'root', 'admin', 'Fake Street 123', DATE('1995/5/23'),'admin',11223344,'A','M','DNI','admin');

INSERT INTO titular (id, apellido, domicilio, es_donante, fecha_emision_licencia_tipo_b, fecha_entrada_sistema, fecha_de_nacimiento, grupo_sanguinio, nombre, numero_documento, observaciones, foto, sexo, tipo_de_documento, id_usuario) VALUES
(1, 'Raimondi', 'Sarmiento%1139% % ', b'0', '2014-11-10', '2019-11-13', '1994-01-10', 'A+', 'Gino', 37569776, 'Ninguna', NULL, 'M', 'DNI', 1),
(2, 'Argento', '25 de Mayo%32050% % ', b'0', '1995-07-23', '2019-11-13', '1962-05-13', 'A+', 'Pepe', 12565646, 'Ninguna', NULL, 'M', 'DNI', 1),
(3, 'Perez', '25 de Mayo%1234% % %', b'0', NULL, '2019-12-09', '1990-10-24', 'B-', 'Marcelo', 12345678, 'Ninguna', NULL, 'F', 'DNI', 1),
(4, 'Rupertina', 'Sarmiento%1111% % %', b'1', NULL, '2019-12-09', '1985-07-13', 'O+', 'Juana', 11111111, 'Conducción no permitida en ruta/autopista', NULL, 'F', 'DNI', 1),
(5, 'Alcides', 'Sarmiento%12332% % %', b'1', '2019-12-09', '2019-12-09', '1952-04-27', 'AB+', 'Carlos', 64531212, 'Prótesis de los miembros superiores', NULL, 'M', 'DNI', 1),
(6, 'Barbosa', '25 de Mayo%654% % %', b'0', NULL, '2019-12-09', '1981-03-26', 'A-', 'Rebeca', 32165498, 'Prótesis auditiva o de ayuda a la comunicación', NULL, 'F', 'DNI', 1),
(7, 'Carvalho', 'Primera Junta%312% % %', b'1', '2015-12-09', '2015-12-09', '1957-07-15', 'O+', 'Pablo', 12365478, 'Conducción no permitida en ruta/autopista', NULL, 'M', 'DNI', 1);

INSERT INTO licencia (id, clase_licencia, fecha_emision, fecha_vencimiento, numero_copia, numero_renovacion, id_titular, id_usuario) VALUES
(1, 'A', '2015-11-10', '2020-01-10', 1, 1, 1, 1),
(2, 'B', '2014-11-10', '2020-01-10', 1, 1, 1, 1),
(3, 'A', '2019-12-09', '2024-10-24', 1, 1, 3, 1),
(4, 'F', '2019-12-09', '2024-07-13', 1, 1, 4, 1),
(5, 'B', '2019-12-09', '2022-04-27', 1, 1, 5, 1),
(6, 'G', '2019-12-09', '2024-03-26', 1, 1, 6, 1),
(7, 'B', '2015-12-09', '2020-07-15', 1, 1, 7, 1),
(8, 'B', '2019-12-09', '2023-05-13', 1, 1, 2, 1),
(9, 'C', '2019-12-09', '2023-05-13', 1, 1, 2, 1);