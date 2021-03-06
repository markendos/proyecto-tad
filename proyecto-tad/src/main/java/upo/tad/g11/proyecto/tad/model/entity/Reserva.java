package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
import java.util.Objects;
import org.bson.types.ObjectId;

/**
 * En esta clase se representa la estructura de una Reserva
 *
 * @author Amalio
 */
@Entity
public class Reserva implements Serializable {

    //Definicion de los atributos de la clase
    @Id
    private ObjectId id = null;
    private String fechaReserva = null;
    private String fechaLlegada = null;
    private String fechaSalida = null;
    private Double importe;
    @Reference
    private Habitacion habitacion = null;
    @Reference
    private Hotel hotel = null;
    @Reference
    private Cliente cliente = null;

    /**
     * Constructor
     *
     * @param id
     * @param fechaReserva
     * @param fechaLlegada
     * @param fechaSalida
     * @param importe
     * @param habitacion
     * @param hotel
     */
    public Reserva(ObjectId id, String fechaReserva, String fechaLlegada, String fechaSalida, Double importe, Habitacion habitacion, Hotel hotel, Cliente cliente) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.importe = importe;
        this.habitacion = habitacion;
        this.hotel = hotel;
        this.cliente = cliente;
    }

    /**
     *
     * @param fechaReserva
     * @param fechaLlegada
     * @param fechaSalida
     * @param importe
     * @param habitacion
     * @param hotel
     */
    public Reserva(String fechaReserva, String fechaLlegada, String fechaSalida, Double importe, Habitacion habitacion, Hotel hotel, Cliente cliente) {
        this.fechaReserva = fechaReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.importe = importe;
        this.habitacion = habitacion;
        this.hotel = hotel;
        this.cliente = cliente;
    }

    /**
     *
     * @param fechaLlegada
     * @param fechaSalida
     */
    public Reserva(String fechaLlegada, String fechaSalida) {
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
    }

    public Reserva() {

    }

    /*
    
    GETTERS Y SETTERS
    
     */
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(String fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", fechaReserva=" + fechaReserva + ", fechaLlegada=" + fechaLlegada + ", fechaSalida=" + fechaSalida + ", importe=" + importe + ", habitacion=" + habitacion + ", hotel=" + hotel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
