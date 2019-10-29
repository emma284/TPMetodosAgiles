/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity
public class Licencia {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column
    private int idLicencia;
    private Date fechaEmision;
    private char claseLicencia;
    private int numeroDeRenovacion;
    private int numeroDeCopia;
//    private Titular titular;
//    private Usuario usuario;

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
