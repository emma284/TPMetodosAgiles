CREATE TABLE IF NOT EXISTS costoLicencias (
    id_costo INT AUTO_INCREMENT PRIMARY KEY,
    clase ENUM ('A', 'B', 'C', 'D', 'E' , 'F', 'G') NOT NULL,
    vigencia5anios DOUBLE NOT NULL,
    vigencia4anios DOUBLE NOT NULL,
    vigencia3anios DOUBLE NOT NULL,
    vigencia1anio DOUBLE NOT NULL
);

INSERT INTO costoLicencias (clase,vigencia5anios,vigencia4anios,vigencia3anios,vigencia1anio) VALUES 
('A', 40, 30, 25, 20),
('B', 40, 30, 25, 20),
('D', 50, 40, 35, 25),
('C', 47, 35, 30, 23),
('E', 59, 44, 39, 29),
('F', 46, 34, 22, 20),
('G', 40, 30, 25, 20);

CREATE TABLE IF NOT EXISTS gastosGenerales (
    id_gasto INT AUTO_INCREMENT PRIMARY KEY,
    tipo_gasto VARCHAR(100) NOT NULL,
    valor DOUBLE NOT NULL
)

INSERT INTO gastosGenerales (tipo_gasto, valor) VALUES ("administrativo", 8);