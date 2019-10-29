/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;


import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "titular")
public class Titular {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer idTitular;
    
    @Column (name = "tipo_de_documento")
    private String tipoDeDocumento;
    
    @Column (name = "apellido")
    private String apellido;
    
    @Column (name = "nombre")
    private String nombre;
    
    //fotografia;
    
    @Column (name = "fecha_de_nacimiento")
    private LocalDate fechaNacimiento;
    
    //direccion;
    
    @Column (name = "grupo_sanguinio")
    private String grupoSanguinio;
    
    @Column (name = "es_donante")
    private Boolean esDonante;
    
    @Column (name = "fecha_entrada_sistema")
    private LocalDateTime fechaEntradaSistema;
    
    //TODO Seguir completando atributos
    private Usuario usuarioResponsable;

}
