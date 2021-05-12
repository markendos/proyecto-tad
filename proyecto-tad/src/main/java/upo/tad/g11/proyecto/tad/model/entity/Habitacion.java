package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;

@Entity
public class Habitacion implements Serializable {

    @Id
    private ObjectId id; // Identificador unico de una instancia
    
    private Integer numero; //Numero de la habitacion
 
    private Boolean fumador; // True si la habitacion es apta para fumadores

    @NotNull
    @Reference
    private TipoHabitacion tipo; // Referencia al tipo de habitacion de la instancia

    @NotNull
    @Reference
    private Hotel hotel;
    
    // Constructor por defecto
    public Habitacion() {
    }

    // Constructor con atributos
    public Habitacion(ObjectId id, Integer numero, Boolean fumador,
            TipoHabitacion tipo, Hotel hotel) {
        this.id = id;
        this.numero = numero;
        this.fumador = fumador;
        this.tipo = tipo;
        this.hotel = hotel;
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Habitacion other = (Habitacion) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Habitacion{" + "id=" + id + ", numero=" + numero + ", fumador=" + fumador + ", tipo=" + tipo + ", hotel=" + hotel.toString() + '}';
    }

    
}
