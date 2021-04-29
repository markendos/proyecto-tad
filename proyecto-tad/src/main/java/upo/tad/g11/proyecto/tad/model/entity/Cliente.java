/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.witzl.proyecto.tad.model.Entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import java.io.Serializable;

/**
 * En esta clase se representa la estructura de un Cliente
 *
 * @author Amalio
 */
@Entity
public class Cliente implements Serializable {

    //Definicion de los atributos de la clase
    @Id
    private String dni;
    private String name;
    private String email;
    private String telefono;

    /**
     * Constructor
     *
     * @param dni
     * @param name
     * @param email
     * @param telefono
     */
    public Cliente(String dni, String name, String email, String telefono) {
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     *
     * @param name
     * @param email
     * @param telefono
     */
    public Cliente(String name, String email, String telefono) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
    }

    /*
    
    GETTERS Y SETTERS
    
     */
    public String getDni() {
        return dni;
    }

    public void setId(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "\nClienteDTO{" + "id=" + dni + ", name=" + name + ", email=" + email + ", telefono=" + telefono + '}';
    }

}
