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
    
    public Titular(){
        
    }
    
    public Titular(String tipoDeDocumento, int numeroDocumento, 
            String apellido, String nombre, Date fechaNacimiento, String domicilio, String grupoSanguinio, 
            Boolean esDonante, char sexo, Date fechaEntradaSistema, Date fechaEmisionLicenciaTipoB){
        
        this.tipoDeDocumento = tipoDeDocumento;
        this.numeroDocumento = numeroDocumento;
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.grupoSanguinio = grupoSanguinio;
        this.esDonante = esDonante;
        this.sexo = sexo;
        this.fechaEntradaSistema = fechaEntradaSistema;
        this.fechaEmisionLicenciaTipoB = fechaEmisionLicenciaTipoB;
        
    }

    public Integer getIdTitular() {
        return idTitular;
    }

    public void setIdTitular(Integer idTitular) {
        this.idTitular = idTitular;
    }

    public String getTipoDeDocumento() {
        return tipoDeDocumento;
    }

    public void setTipoDeDocumento(String tipoDeDocumento) {
        this.tipoDeDocumento = tipoDeDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getGrupoSanguinio() {
        return grupoSanguinio;
    }

    public void setGrupoSanguinio(String grupoSanguinio) {
        this.grupoSanguinio = grupoSanguinio;
    }

    public Boolean getEsDonante() {
        return esDonante;
    }

    public void setEsDonante(Boolean esDonante) {
        this.esDonante = esDonante;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getFechaEntradaSistema() {
        return fechaEntradaSistema;
    }

    public void setFechaEntradaSistema(Date fechaEntradaSistema) {
        this.fechaEntradaSistema = fechaEntradaSistema;
    }

    public Date getFechaEmisionLicenciaTipoB() {
        return fechaEmisionLicenciaTipoB;
    }

    public void setFechaEmisionLicenciaTipoB(Date fechaEmisionLicenciaTipoB) {
        this.fechaEmisionLicenciaTipoB = fechaEmisionLicenciaTipoB;
    }

    public List<Licencia> getLicencias() {
        return licencias;
    }

    public void setLicencias(List<Licencia> licencias) {
        this.licencias = licencias;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

}
