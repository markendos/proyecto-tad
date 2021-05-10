package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.ReservaDAO;
import upo.tad.g11.proyecto.tad.model.entity.Reserva;

/**
 * Este controlador se encarga de gestionar las operaciones de las reservas
 *
 *
 * @author Amalio
 */
public class ControladorReserva implements Controlador<Reserva> {

    //Definicion de los atributos
    private ReservaDAO reservas = new ReservaDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    public void add(Reserva t) {
        if (reservas.get(t.getId()) == null) {
            this.reservas.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Reserva t) {
        this.reservas.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Reserva t) {

        this.reservas.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Reserva get(Reserva t) {
        Reserva r = reservas.get(t.getId());
        return r;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return reservas.getAll();
    }

    @Override
    public void addAll(List<Reserva> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
