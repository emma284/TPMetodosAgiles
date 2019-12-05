/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpmetodosagiles.entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name = "id")
    private int idUsuario;
    
    @Column (name = "username")
    private String username;
    
    @Column (name = "contrasenia")
    private String contrasenia;
    
    @Column (name = "rol")
    private char rol;
    
    @Column (name = "tipo_de_documento")
    private String tipoDeDocumento;
    
    @Column (name = "numero_documento")
    private int numeroDocumento;
    
    @Column (name = "apellido")
    private String apellido;
    
    @Column (name = "nombre")
    private String nombre;
    
    //TODO: Emir-Luciano: fotografia;
    //private String fotografia;
    
    @Column (name = "fecha_de_nacimiento")
    private Date fechaNacimiento;
    
    @Column (name = "domicilio")
    private String domicilio;
    
    @Column (name = "sexo")
    private char sexo;
    
    
    
    
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
