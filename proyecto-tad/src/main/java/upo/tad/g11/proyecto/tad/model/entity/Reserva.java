/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model.entity;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import java.io.Serializable;
import org.bson.Document;
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
    private double importe;

    /**
     * Constructor
     *
     * @param id
     * @param fechaReserva
     * @param fechaLlegada
     * @param fechaSalida
     * @param importe
     */
    public Reserva(ObjectId id, String fechaReserva, String fechaLlegada, String fechaSalida, double importe) {
        this.id = id;
        this.fechaReserva = fechaReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.importe = importe;
    }

    /**
     *
     * @param fechaReserva
     * @param fechaLlegada
     * @param fechaSalida
     * @param importe
     */
    public Reserva(String fechaReserva, String fechaLlegada, String fechaSalida, double importe) {
        this.fechaReserva = fechaReserva;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.importe = importe;
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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * Metodo que transforma un DTO en Documento
     *
     * @return new Document()
     */
    public Document toDocument() {

        Document d = new Document("id", getId());
        d.append("fechaReserva", getFechaReserva());
        d.append("fechaLlegada", getFechaLlegada());
        d.append("fechaSalida", getFechaSalida());
        d.append("importe", getImporte());
        return d;

    }

    /**
     * Metodo que transforma el objeto DTO en cadena
     *
     * @return String
     */
    @Override
    public String toString() {
        return "ReservaDTO{" + "id=" + id + ", fechaReserva=" + fechaReserva + ", fechaLlegada=" + fechaLlegada + ", fechaSalida=" + fechaSalida + ", importe=" + importe + '}';
    }

}
