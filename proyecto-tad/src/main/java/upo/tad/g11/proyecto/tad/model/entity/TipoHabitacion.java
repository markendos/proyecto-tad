package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import java.io.Serializable;
import java.util.Objects;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class TipoHabitacion implements Serializable {

    @Id
    private ObjectId id; // Identificador unico de una instancia

    @NotEmpty
    private String nombre; // Nombre del tipo de habitacion: simple, doble, suite...

    private Float metros; // Metros cuadrados de las habitaciones de ese tipo

    private Boolean terraza; // True si el tipo de habitacion dispone de terraza

    private Float precio; // El precio por noche de las habitaciones de ese tipo

    // Constructor por defecto
    public TipoHabitacion() {
    }

    // Constructor con atributos
    public TipoHabitacion(ObjectId id, String nombre, Float metros, Boolean terraza,
            Float precio) {
        this.id = id;
        this.nombre = nombre;
        this.metros = metros;
        this.terraza = terraza;
        this.precio = precio;
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

    public Float getMetros() {
        return metros;
    }

    public void setMetros(Float metros) {
        this.metros = metros;
    }

    public Boolean getTerraza() {
        return terraza;
    }

    public void setTerraza(Boolean terraza) {
        this.terraza = terraza;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
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
        final TipoHabitacion other = (TipoHabitacion) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "TipoHabitacion{" + "id=" + id + ", nombre=" + nombre
                + ", metros=" + metros + ", terraza=" + terraza
                + ", precio=" + precio + '}';
    }
}
