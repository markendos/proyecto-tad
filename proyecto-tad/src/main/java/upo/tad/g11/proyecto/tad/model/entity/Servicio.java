package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
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
    private double tarifa;

    /**
     * Constructor
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param horario
     * @param tarifa
     */
    public Servicio(ObjectId id, String nombre, String descripcion, String horario, double tarifa) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.tarifa = tarifa;
    }
    /**
     * 
     * @param nombre
     * @param descripcion
     * @param horario
     * @param tarifa 
     */
    public Servicio(String nombre, String descripcion, String horario, double tarifa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.horario = horario;
        this.tarifa = tarifa;
    }

    /*
    
    GETTERS Y SETTERS
    
     */

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(float tarifa) {
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

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ServicioDTO{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", horario=" + horario + ", tarifa=" + tarifa + '}';
    }

}
