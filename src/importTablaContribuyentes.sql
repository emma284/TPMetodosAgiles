CREATE TABLE IF NOT EXISTS contribuyente (
    id_contribuyente INT AUTO_INCREMENT PRIMARY KEY,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    tipo_documento ENUM ('DNI', 'LE', 'LC', 'DE') NOT NULL,
    nro_documento INT NOT NULL,
    fecha_nacimiento DATE,
    sexo ENUM ('M','F'),
    calle VARCHAR(255),
    altura SMALLINT,
    departamento VARCHAR(10)
);

INSERT INTO contribuyente(nombres,apellidos,tipo_documento,nro_documento,fecha_nacimiento,sexo,calle,altura,departamento) VALUES
    ('Marcelo','Perez','DNI',12345678,'1990/10/24','M','25 de Mayo','1234',NULL),
    ('Juana','Rupertina','DNI',11111111,'1985/7/13','F','Sarmiento','1111',NULL),
    ('Carlos','Alcides','DNI',64531212,'1952/04/27','M','Sarmiento','12332',NULL),
    ('Rebeca','Barbosa','DNI',32165498,'1981/03/26','F','25 de Mayo','654',NULL),
    ('Thiago','Fernández','DNI',96325874,'2003/11/15','M','Primera Junta','841',NULL),
    ('Beatriz','Pinto','DNI',25896314,'1957/09/27','F','San Martín','7412',NULL),
    ('Fernanda','Ribeiro','DNI',15975364,'1995/10/01','F','Manuel Belgrano','2874',NULL),
    ('Luis','Oliveira','DNI',85241236,'1999/12/25','M','9 de Julio','6584',NULL),
    ('Pablo','Carvalho','DNI',12365478,'1957/07/15','M','Primera Junta','312',NULL),
    ('Gino','Raimondi','DNI',37569776,'1994/01/10','M','Sarmiento','1139',NULL);
