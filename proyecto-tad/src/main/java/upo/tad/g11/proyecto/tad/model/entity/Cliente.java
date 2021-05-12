package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * En esta clase se representa la estructura de un Cliente
 *
 * @author Amalio
 */
@Entity
public class Cliente implements Serializable {

    //Definicion de los atributos de la clase
    @Id
    private String dni;
    private String nombre;
    private String email;
    private String telefono;
    @Reference
    private List<Reserva> reservas;

    /**
     * Constructor
     *
     * @param dni
     * @param nombre
     * @param email
     * @param telefono
     * @param reservas
     */
    public Cliente(String dni, String nombre, String email, String telefono, List<Reserva> reservas) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.reservas = reservas;
    }
    public Cliente(String dni,List<Reserva> reservas) {
        this.dni = dni;
        this.reservas = reservas;
    }
    public Cliente() {

    }

    /**
     *
     * @param nombre
     * @param email
     * @param telefono
     * @param reservas
     */
    public Cliente(String nombre, String email, String telefono, List<Reserva> reservas) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.reservas = reservas;
    }

    /**
     *
     * @param nombre
     * @param email
     * @param telefono
     */
    public Cliente(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.reservas = new ArrayList<Reserva>();
    }
    /**
     *
     * @param nombre
     * @param email
     * @param telefono
     * @param dni
     */
    public Cliente(String dni, String nombre, String email, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.reservas = new ArrayList<Reserva>();
    }
    
    /*
    
    GETTERS Y SETTERS
    
     */
    public String getDni() {
        return dni;
    }

    public void setId(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.dni);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "\nClienteDTO{" + "id=" + dni + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono + ", reservas=" + Arrays.toString(reservas.toArray()) + '}';
    }

}
