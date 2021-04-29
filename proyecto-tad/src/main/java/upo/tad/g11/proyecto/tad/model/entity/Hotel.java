/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model.entity;

/**
 *
 * @author Propietario
 */
public class Hotel {
    
    private String id;      //Id para diferenciar los hoteles
    private String nombre;  //Nombre del hotel
    private String ubicacion;   //Ubicación del hotel
    private String calidad;     //Calidad del hotel

    //Tenemos el contructor y los getters, setters y toString típicos
    public Hotel(String id, String nombre, String ubicacion, String calidad) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.calidad = calidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    @Override
    public String toString() {
        return "Hotel{id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", calidad=" + calidad + '}';
    }
    
    
}
