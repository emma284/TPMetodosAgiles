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

INSERT INTO contribuyente (nombres,apellidos,tipo_documento,nro_documento,fecha_nacimiento,sexo,calle,altura,departamento) VALUES 
	('Marcelo','Iconocelo','DNI',123456,'1990/10/24','M','25 de Mayo','1234',null),
	('Juana','Rupertina','DNI',111111,'1985/7/13','F','Sarmiento','1111',null);