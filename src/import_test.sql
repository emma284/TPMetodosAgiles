INSERT INTO usuario (id,apellido,contrasenia,domicilio,fecha_de_nacimiento,nombre,numero_documento,rol,sexo,tipo_de_documento,username) VALUES 
    (1, 'rootencio', 'admin', 'Fake Street 123', DATE('1995/5/23'),'admin',11223344,'A','M','DNI','admin');

INSERT INTO titular (id,apellido,domicilio,es_donante,fecha_emision_licencia_tipo_b,fecha_entrada_sistema,fecha_de_nacimiento,grupo_sanguinio,nombre,numero_documento,observaciones,sexo,tipo_de_documento,id_usuario) VALUES
    (1, 'Raimondi', 'Sarmiento%1139% % ', false, DATE('2014/11/10'), DATE('2019/11/13'), DATE('1994/03/08'), 'A+', 'Gino',37569776,'Ninguna','M','DNI',1),
    (2, 'Argento', '25 de Mayo%32050% % ', false, DATE('1995/7/23'), DATE('2019/11/13'), DATE('1962/5/13'), 'A+', 'Pepe',12565646,'Ninguna','M','DNI',1),
    (3, 'Uno', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2002/11/27'), 'A+', 'Uno',11111111,'Ninguna','M','DNI',1),
    (4, 'Dos', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2002/02/07'), 'A+', 'Dos',22222222,'Ninguna','M','DNI',1),
    (5, 'Tres', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2003/01/07'), 'A+', 'Tres',33333333,'Ninguna','M','DNI',1),
    (6, 'Cuatro', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2003/07/07'), 'A+', 'Cuatro',44444444,'Ninguna','M','DNI',1),
    (7, 'Cinco', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1999/02/06'), 'A+', 'Cinco',55555555,'Ninguna','M','DNI',1),
    (8, 'Seis', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1999/05/07'), 'A+', 'Seis',66666666,'Ninguna','M','DNI',1),
    (9, 'Siete', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1998/08/07'), 'A+', 'Siete',77777777,'Ninguna','M','DNI',1),
    (10, 'Ocho', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1973/07/07'), 'A+', 'Ocho',88888888,'Ninguna','M','DNI',1),
    (11, 'Nueve', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1974/01/07'), 'A+', 'Nueve',99999999,'Ninguna','M','DNI',1),
    (12, 'Diez', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1949/01/07'), 'A+', 'Diez',00000010,'Ninguna','M','DNI',1),
    (13, 'Once', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1949/08/07'), 'A+', 'Once',00000011,'Ninguna','M','DNI',1),
    (14, 'Doce', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2000/03/07'), 'A+', 'Doce',00000012,'Ninguna','M','DNI',1),
    (15, 'Trece', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2000/01/07'), 'A+', 'Trece',00000013,'Ninguna','M','DNI',1),
    (16, 'Catorce', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('2001/10/07'), 'A+', 'Catorce',00000014,'Ninguna','M','DNI',1),
    (17, 'Quince', 'Avda siempre viva% % ', false, DATE('2018/7/23'), DATE('2019/11/13'), DATE('1999/03/07'), 'A+', 'Quince',00000015,'Ninguna','M','DNI',1);

INSERT INTO licencia (id,clase_licencia,fecha_emision,fecha_vencimiento,numero_copia,numero_renovacion,id_titular,id_usuario) VALUES
    (1,'A',DATE('2015/11/10'),DATE('2020/11/10'),1,1,1,1),
    (2,'B',DATE('2014/11/10'),DATE('2019/11/10'),1,1,1,1),
    (3,'B',DATE('2014/11/10'),DATE('2019/11/10'),1,1,16,1),
    (4,'A',DATE('2014/11/10'),DATE('2019/11/10'),1,1,17,1);