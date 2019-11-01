/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;

import java.util.Date;


public class Usuario {
    private String nombre;
    private String apellido;
    private char rol;
    private int dni;
    private Date fechaNacimiento;
    private char sexo;
    private String domicilio;
    //private String fotografia;
    
    public String getNombre(){
        return this.nombre;
    }
    public void setNombre(String nombreDeUsuario){
        this.nombre = nombreDeUsuario;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public char getRol() {
        return rol;
    }
    public void setRol(char rol) {
        this.rol = rol;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
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
    public char getSexo() {
        return sexo;
    }
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    
    

}
