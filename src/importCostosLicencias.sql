CREATE TABLE IF NOT EXISTS costo_licencias (
    id_costo INT AUTO_INCREMENT PRIMARY KEY,
    clase ENUM ('A', 'B', 'C', 'D', 'E' , 'F', 'G') NOT NULL,
    vigencia5anios DOUBLE NOT NULL,
    vigencia4anios DOUBLE NOT NULL,
    vigencia3anios DOUBLE NOT NULL,
    vigencia1anio DOUBLE NOT NULL
);

INSERT INTO costo_licencias (id_costo,clase,vigencia5anios,vigencia4anios,vigencia3anios,vigencia1anio) VALUES ON DUPLICATE KEY UPDATE
(1,'A', 40, 30, 25, 20),
(2,'B', 40, 30, 25, 20),
(3,'D', 50, 40, 35, 25),
(4,'C', 47, 35, 30, 23),
(5,'E', 59, 44, 39, 29),
(6,'F', 46, 34, 22, 20),
(7,'G', 40, 30, 25, 20);

CREATE TABLE IF NOT EXISTS gastos_generales (
    id_gasto INT AUTO_INCREMENT PRIMARY KEY,
    tipo_gasto VARCHAR(100) NOT NULL,
    valor DOUBLE NOT NULL
);

INSERT INTO gastos_generales (id_gasto, tipo_gasto, valor) VALUES ON DUPLICATE KEY UPDATE (1,"Administrativo", 8);