package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private String name;
    private String email;
    private String telefono;
    @Reference
    private List<Reserva> reservas;

    /**
     * Constructor
     *
     * @param dni
     * @param name
     * @param email
     * @param telefono
     * @param reservas
     */
    public Cliente(String dni, String name, String email, String telefono, List<Reserva> reservas) {
        this.dni = dni;
        this.name = name;
        this.email = email;
        this.telefono = telefono;
        this.reservas = reservas;
    }

    public Cliente() {

    }

    /**
     *
     * @param name
     * @param email
     * @param telefono
     * @param reservas
     */
    public Cliente(String name, String email, String telefono, List<Reserva> reservas) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
        this.reservas = reservas;
    }

    /**
     *
     * @param name
     * @param email
     * @param telefono
     */
    public Cliente(String name, String email, String telefono) {
        this.name = name;
        this.email = email;
        this.telefono = telefono;
        this.reservas = new ArrayList<Reserva>();
    }
    /**
     *
     * @param name
     * @param email
     * @param telefono
     */
    public Cliente(String dni, String name, String email, String telefono) {
        this.dni = dni;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "\nClienteDTO{" + "id=" + dni + ", name=" + name + ", email=" + email + ", telefono=" + telefono + ", reservas=" + Arrays.toString(reservas.toArray()) + '}';
    }

}
