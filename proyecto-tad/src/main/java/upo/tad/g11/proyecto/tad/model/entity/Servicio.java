package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import org.bson.types.ObjectId;

/**
 * En esta clase se representa la estructura de un Servicio
 *
 * @author Amalio
 */
@Entity
public class Servicio implements Serializable{

    //Definicion de los atributos de la clase
    @Id
    private ObjectId id;
    private String nombre;
    private String descripcion;
    private String horario;
    private Double tarifa;
    @Reference
    private Hotel hotel;

    /**
     * Constructor
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param horario
     * @param tarifa
     * @param hotel
     */
    public Servicio(ObjectId id, String nombre, String descripcion, String horario, Double tarifa, Hotel hotel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.tarifa = tarifa;
        this.hotel = hotel;
    }
    
    /**
     * 
     * @param nombre
     * @param descripcion
     * @param horario
     * @param tarifa
     * @param hotel
     */
    public Servicio(String nombre, String descripcion, String horario, Double tarifa, Hotel hotel) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.tarifa = tarifa;
        this.hotel = hotel;
    }

    public Servicio(){
        
    }
    
    /*
    
    GETTERS Y SETTERS
    
     */

    public Double getTarifa() {
        return tarifa;
    }

    public void setTarifa(Double tarifa) {
        this.tarifa = tarifa;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Servicio{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", horario=" + horario + ", tarifa=" + tarifa + ", hotel=" + hotel.toString() + '}';
    }

}
