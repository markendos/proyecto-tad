package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * En esta clase se representa la estructura de una Instalacion
 *
 * @author Alvaro
 */
@Entity
public class Instalacion {

    @Id
    private ObjectId id;      //Id de la instalación
    @NotEmpty
    private String nombre;  //Nombre de la instalacion
    @NotEmpty
    private String tipo;   //Tipo de instalación
    @NotEmpty
    private int aforo;      //Aforo de la instalación
    @NotNull
    @Reference
    private Hotel hotel; // Referencia al hotel de la instancia

    /**
     * Constructor
     *
     * @param dni
     * @param nombre
     * @param tipo
     * @param aforo
     * @param hotel
     */
    public Instalacion(ObjectId id, String nombre, String tipo, int aforo, Hotel hotel) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.aforo = aforo;
        this.hotel = hotel;
    }

    public Instalacion(){
        
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
        return "Instalacion{" + "id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", aforo=" + aforo + ", hotel=" + hotel.toString() + '}';
    }

}
