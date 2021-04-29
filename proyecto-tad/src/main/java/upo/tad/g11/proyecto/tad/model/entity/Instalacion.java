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
public class Instalacion {
    private String id;      //Id de la instalación
    private String nombre;  //Nombre de la instalacion
    private String tipo;   //Tipo de instalación
    private int aforo;      //Aforo de la instalación

    public Instalacion(String id, String nombre, String tipo, int aforo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.aforo = aforo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    @Override
    public String toString() {
        return "Instalacion{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", aforo=" + aforo + '}';
    }
    
    
}
