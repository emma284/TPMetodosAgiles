/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.clases;

import java.sql.Date;


public class Licencia {
    private String idLicencia;
    private Date fechaEmision;
    private char claseLicencia;
    private int numeroDeRenovacion;
    private int numeroDeCopia;
    private Titular titular;

    public String getIdLicencia() {
        return idLicencia;
    }

    private void setIdLicencia(String idLicencia) {
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

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    
    
}
