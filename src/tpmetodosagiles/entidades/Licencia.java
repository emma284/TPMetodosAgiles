/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;

import java.util.Date;
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
    private Date fechaEmision;
    
    @Column (name = "fecha_vencimiento")
    private Date fechaVencimiento;
    
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
    
    public Licencia(Date fechaEmision, Date fechaVencimiento, char claseLicencia, int numeroDeRenovacion, 
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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public char getClaseLicencia() {
        return claseLicencia;
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
    
    
}
