package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import java.io.Serializable;
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
    private ObjectId id;
    private String fechaReserva;
    private String fechaLlegada;
    private String fechaSalida;
    private Double importe;
    @Reference
    private Habitacion habitacion;

    /**
     * Constructor
     *
     * @param id
     * @param fechaReserva
     * @param fechaLlegada
     * @param fechaSalida
     * @param importe
     * @param habitacion
     */
    public Reserva(ObjectId id, String fechaReserva, String fechaLlegada, String fechaSalida, Double importe, Habitacion habitacion) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.importe = importe;
        this.habitacion = habitacion;
    }

    /**
     *
     * @param fechaReserva
     * @param fechaLlegada
     * @param fechaSalida
     * @param importe
     * @param habitacion
     */
    public Reserva(String fechaReserva, String fechaLlegada, String fechaSalida, Double importe, Habitacion habitacion) {
        this.fechaReserva = fechaReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.importe = importe;
        this.habitacion = habitacion;
    }

    public Reserva(){
        
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

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", fechaReserva=" + fechaReserva + ", fechaLlegada=" + fechaLlegada + ", fechaSalida=" + fechaSalida + ", importe=" + importe + ", habitacion=" + habitacion.toString() + '}';
    }

}
