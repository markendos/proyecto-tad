/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import java.util.Objects;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * En esta clase se representa la estructura de un Hotel
 *
 * @author Alvaro
 */
@Entity
public class Hotel {

    @Id
    private ObjectId id;      //Id para diferenciar los hoteles

    @NotEmpty
    private String nombre;  //Nombre del hotel

    @NotEmpty
    private String ubicacion;   //Ubicación del hotel

    @NotEmpty
    private String tipo;

    @NotEmpty
    private String zona;

    @NotEmpty
    private String calidad;     //Calidad del hotel

    /**
     * Constructor
     *
     * @param id
     * @param nombre
     * @param ubicacion
     * @param tipo
     * @param zona
     * @param calidad
     */
    public Hotel(ObjectId id, String nombre, String ubicacion, String tipo,
            String zona, String calidad) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.zona = zona;
        this.calidad = calidad;
    }

    public Hotel() {

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Hotel other = (Hotel) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", nombre=" + nombre + ", ubicacion="
                + ubicacion + ", tipo=" + tipo + ", zona=" + zona + ", calidad=" + calidad + '}';
    }

}
