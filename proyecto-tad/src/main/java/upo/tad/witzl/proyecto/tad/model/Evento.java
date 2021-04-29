package upo.tad.witzl.proyecto.tad.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author marwi
 * 
 * Esta entidad representa un evento que tendra lugar en alguna de las instalaciones
 * del hotel en una fecha concreta
 */
@Entity
public class Evento implements Serializable {

    @Id
    private Long id; // Identificador unico de una instancia
    
    private String nombre; // Nombre del evento
    
    private String descripcion; // Descripcion del evento
    
    private Date fecha; // Fecha de realizacion del evento

    // Constructor por defecto
    public Evento() {
    }

    // Constructor con atributos
    public Evento(Long id, String nombre, String descripcion, Date fecha) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    // Getters y setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        return "Evento{" + "id=" + id + ", nombre=" + nombre + ", descripcion="
                + descripcion + ", fecha=" + fecha + '}';
    }
}
