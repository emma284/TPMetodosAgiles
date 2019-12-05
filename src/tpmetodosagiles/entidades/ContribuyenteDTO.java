/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import tpmetodosagiles.enums.SexoEnum;
import tpmetodosagiles.enums.TipoDocumentoEnum;

@Entity
@Table(name = "contribuyente")
public class ContribuyenteDTO {
    @Id
    @Column (name="id_contribuyente")
    public int idContribuyente;
    
    @Column (name = "nombres")
    public String nombres;
    
    @Column (name = "apellidos")
    public String apellidos;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "tipo_documento" , columnDefinition="ENUM('DNI', 'LE', 'LC', 'DE')" ,nullable = false )
    public TipoDocumentoEnum tipoDocumento;
    
    @Column (name = "nro_documento")
    public int nroDocumento;
    
    @Column (name = "fecha_nacimiento")
    public LocalDate fechaNacimiento;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "sexo" , columnDefinition="ENUM('M','F')" ,nullable = true )
    public SexoEnum sexo;
    
    @Column (name = "calle")
    public String calle;
    
    @Column (name = "altura")
    public short altura;
    
    @Column (name = "departamento")
    public String departamento;
    
}
