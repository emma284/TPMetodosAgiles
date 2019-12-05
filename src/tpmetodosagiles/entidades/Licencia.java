/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "licencia")
public class Licencia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="id")
    private int idLicencia;
    
    @Column (name = "fecha_emision")
    private LocalDate fechaEmision;
    
    @Column (name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;
    
    @Column (name = "clase_licencia")
    private char claseLicencia;
    
    @Column (name = "numero_renovacion")
    private int numeroDeRenovacion;
    
    @Column (name = "numero_copia")
    private int numeroDeCopia;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_titular")
    private Titular titular;
    
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    private Usuario usuarioResponsable;
    
    public Licencia(){
        
    }
    
    public Licencia(LocalDate fechaEmision, LocalDate fechaVencimiento, char claseLicencia, int numeroDeRenovacion, 
            int numeroDeCopia){
        
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.claseLicencia = claseLicencia;
        this.numeroDeRenovacion = numeroDeRenovacion;
        this.numeroDeCopia = numeroDeCopia;
        
    }

    public int getIdLicencia() {
        return idLicencia;
    }

    public void setIdLicencia(int idLicencia) {
        this.idLicencia = idLicencia;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public char getClaseLicencia() {
        return claseLicencia;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioRespon) {
        this.usuarioResponsable = usuarioRespon;
    }

    public void setClaseLicencia(char claseLicencia) {
        this.claseLicencia = claseLicencia;
    }

    public int getNumeroDeRenovacion() {
        return numeroDeRenovacion;
    }

    public void setNumeroDeRenovacion(int numeroDeRenovacion) {
        this.numeroDeRenovacion = numeroDeRenovacion;
    }

    public int getNumeroDeCopia() {
        return numeroDeCopia;
    }

    public void setNumeroDeCopia(int numeroDeCopia) {
        this.numeroDeCopia = numeroDeCopia;
    }

//    public Titular getTitular() {
//        return titular;
//    }

//    public void setTitular(Titular titular) {
//        this.titular = titular;
//    }

//    public Usuario getUsuario() {
//        return usuario;
//    }

//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    @Override
    public String toString() {
        return "Licencia{" + "idLicencia=" + idLicencia + ", fechaEmision=" + fechaEmision + ", fechaVencimiento=" + fechaVencimiento + ", claseLicencia=" + claseLicencia + ", numeroDeRenovacion=" + numeroDeRenovacion + ", numeroDeCopia=" + numeroDeCopia + ", titular=" + titular + ", usuarioResponsable=" + usuarioResponsable + '}';
    }
    
    
}
