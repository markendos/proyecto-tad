package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;

@Entity
public class Evento implements Serializable {

    @Id
    private ObjectId id; // Identificador unico de una instancia
    
    private String nombre; // Nombre del evento
    
    private String descripcion; // Descripcion del evento
    
    private Date fecha; // Fecha de realizacion del evento
    
    @Reference
    private Hotel hotel;

    // Constructor por defecto
    public Evento() {
    }

    // Constructor con atributos
    public Evento(ObjectId id, String nombre, String descripcion, Date fecha, Hotel hotel) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hotel = hotel;
    }

    // Getters y setters
    
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        return !((this.id == null && other.id != null) || (this.id != null
                && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Evento{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha + ", hotel=" + hotel.toString() + '}';
    }
    
}
