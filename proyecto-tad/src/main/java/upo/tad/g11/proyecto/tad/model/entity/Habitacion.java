package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import org.bson.types.ObjectId;

@Entity
public class Habitacion implements Serializable {

    @Id
    private ObjectId id; // Identificador unico de una instancia
    
    private Integer numero; //Numero de la habitacion
 
    private Boolean fumador; // True si la habitacion es apta para fumadores

    @Reference
    private TipoHabitacion tipo; // Referencia al tipo de habitacion de la instancia

    // Constructor por defecto
    public Habitacion() {
    }

    // Constructor con atributos
    public Habitacion(ObjectId id, Integer numero, Boolean fumador,
            TipoHabitacion tipo) {
        this.id = id;
        this.numero = numero;
        this.fumador = fumador;
        this.tipo = tipo;
    }

    // Getters y setters
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", fumador="
                + fumador + ", tipo=" + tipo + '}';
    }
}
