/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;


import java.time.LocalDate;
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
    @Column (name = "idTitular")
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
    private LocalDate fechaNacimiento;
    
    @Column (name = "domicilio")
    private String domicilio;
    
    @Column (name = "grupo_sanguinio")
    private String grupoSanguinio;
    
    @Column (name = "es_donante")
    private Boolean esDonante;
    
    @Column (name = "sexo")
    private char sexo;
    
    @Column (name = "fecha_entrada_sistema")
    private LocalDate fechaEntradaSistema;
    
    @Column (name = "fecha_emision_licencia_tipo_b")
    private LocalDate fechaEmisionLicenciaTipoB;
    
    @Column (name = "observaciones")
    private String observaciones;
    
    //TODO: Emir-Luciano: Agregar Observaciones 
    
    @OneToMany(mappedBy = "titular", cascade= CascadeType.ALL)
    private List<Licencia> licencias;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario usuarioResponsable;
    
    public Titular(){
        this.idTitular = -1;
        this.tipoDeDocumento = "";
        this.numeroDocumento = 0;
        this.apellido = "";
        this.nombre = "";
        this.fechaNacimiento = null;
        this.domicilio = "";
        this.grupoSanguinio = "";
        this.esDonante = false;
        this.sexo = 'M';
        this.fechaEntradaSistema = null;
        this.fechaEmisionLicenciaTipoB = null;
        this.observaciones = "";
    }
    
    public Titular(String tipoDeDocumento, int numeroDocumento, 
            String apellido, String nombre, LocalDate fechaNacimiento, String domicilio, String grupoSanguinio, 
            Boolean esDonante, char sexo, LocalDate fechaEntradaSistema, LocalDate fechaEmisionLicenciaTipoB, String observaciones){
        
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
        this.observaciones = observaciones;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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

    public LocalDate getFechaEntradaSistema() {
        return fechaEntradaSistema;
    }

    public void setFechaEntradaSistema(LocalDate fechaEntradaSistema) {
        this.fechaEntradaSistema = fechaEntradaSistema;
    }

    public LocalDate getFechaEmisionLicenciaTipoB() {
        return fechaEmisionLicenciaTipoB;
    }

    public void setFechaEmisionLicenciaTipoB(LocalDate fechaEmisionLicenciaTipoB) {
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    @Override
    public String toString(){
        String titular = "";
        titular += this.apellido;
        titular += this.nombre;
        return titular;
    }
}
