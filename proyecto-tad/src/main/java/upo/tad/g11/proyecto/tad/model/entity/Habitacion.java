package upo.tad.witzl.proyecto.tad.model;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;

/**
 *
 * @author marwi
 *
 * Esta clase representa una habitacion concreta dentro de un hotel.
 */
@Entity
public class Habitacion implements Serializable {

    @Id
    private Long id; // Identificador unico de una instancia

    private Integer planta; // Planta del hotel donde esta ubicada la habitacion
 
    private Boolean fumador; // True si la habitacion es apta para fumadores

    @Reference
    private TipoHabitacion tipo; // Referencia al tipo de habitacion de la instancia

    // Constructor por defecto
    public Habitacion() {
    }

    // Constructor con atributos
    public Habitacion(Long id, Integer planta, Boolean fumador,
            TipoHabitacion tipo) {
        this.id = id;
        this.planta = planta;
        this.fumador = fumador;
        this.tipo = tipo;
    }

    // Getters y setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlanta() {
        return planta;
    }

    public void setPlanta(Integer planta) {
        this.planta = planta;
    }

    public Boolean getFumador() {
        return fumador;
    }

    public void setFumador(Boolean fumador) {
        this.fumador = fumador;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public void setTipo(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Habitacion)) {
            return false;
        }
        Habitacion other = (Habitacion) object;
        return !((this.id == null && other.id != null) || (this.id != null
                && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", planta=" + planta + ", fumador="
                + fumador + ", tipo=" + tipo + '}';
    }
}
