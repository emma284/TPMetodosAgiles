INSERT INTO usuario (id,apellido,contrasenia,domicilio,fecha_de_nacimiento,nombre,numero_documento,rol,sexo,tipo_de_documento,username) VALUES 
    (1, 'rootencio', 'admin', 'Fake Street 123', DATE('1995/5/23'),'admin',11223344,'A','M','DNI','admin');

INSERT INTO titular (id,apellido,domicilio,es_donante,fecha_emision_licencia_tipo_b,fecha_entrada_sistema,fecha_de_nacimiento,grupo_sanguinio,nombre,numero_documento,observaciones,sexo,tipo_de_documento,id_usuario) VALUES
    (1, 'Raimondi', 'Sarmiento%1139% % ', false, DATE('2014/11/10'), DATE('2019/11/13'), DATE('1994/03/08'), 'A+', 'Gino',37569776,'Ninguna','M','DNI',1),
    (2, 'Argento', '25 de Mayo%32050% % ', false, DATE('1995/7/23'), DATE('2019/11/13'), DATE('1962/5/13'), 'A+', 'Pepe',12565646,'Ninguna','M','DNI',1);

INSERT INTO licencia (id,clase_licencia,fecha_emision,fecha_vencimiento,numero_copia,numero_renovacion,id_titular,id_usuario) VALUES
    (1,'A',DATE('2015/11/10'),DATE('2020/1/10'),1,1,1,1),
    (2,'B',DATE('2014/11/10'),DATE('2019/11/10'),1,1,1,1);