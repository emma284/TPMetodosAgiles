/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;


import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    
    @Column (name = "numero_documento")
    private int numeroDocumento;
    
    @Column (name = "apellido")
    private String apellido;
    
    @Column (name = "nombre")
    private String nombre;
    
    //TODO: Emir-Luciano: fotografia;
    
    @Column (name = "fecha_de_nacimiento")
    private Date fechaNacimiento;
    
    @Column (name = "domicilio")
    private String domicilio;
    
    @Column (name = "grupo_sanguinio")
    private String grupoSanguinio;
    
    @Column (name = "es_donante")
    private Boolean esDonante;
    
    @Column (name = "sexo")
    private char sexo;
    
    @Column (name = "fecha_entrada_sistema")
    private Date fechaEntradaSistema;
    
    @Column (name = "fecha_emision_licencia_tipo_b")
    private Date fechaEmisionLicenciaTipoB;
    
    //TODO: Emir-Luciano: Agregar Observaciones 
    
    @OneToMany(mappedBy = "idLicencia", cascade= CascadeType.ALL)
    private List<Licencia> licencias;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioResponsable;

}
