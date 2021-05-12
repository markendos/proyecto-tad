/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * En esta clase se representa la estructura del Personal
 *
 * @author Grupo XI
 */
@Entity
public class Personal {

    @Id
    private ObjectId id;      //Id del empleado

    @NotEmpty
    private String nombre;  //Nombre del empleado

    @NotEmpty
    private String puesto;   //Puesto del empleado

    private Float salario;    //Salario del empleado

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 5, max = 10)
    private String password;

    @NotNull
    @Reference
    private Hotel hotel; // Referencia al hotel de la instancia

    /**
     * Constructor
     *
     * @param id
     * @param nombre
     * @param puesto
     * @param salario
     * @param email
     * @param password
     */
    public Personal(ObjectId id, String nombre, String puesto, Float salario, Hotel hotel, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
        this.hotel = hotel;
        this.email = email;
        this.password = password;
    }

    public Personal() {

    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Personal{" + "id=" + id + ", nombre=" + nombre + ", puesto=" + puesto + ", salario=" + salario + ", email=" + email + ", password=*****" + ", hotel=" + hotel.toString() + '}';
    }

}
