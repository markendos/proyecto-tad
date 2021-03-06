package upo.tad.g11.proyecto.tad.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.HabitacionDAO;
import upo.tad.g11.proyecto.tad.model.DAO.ReservaDAO;
import upo.tad.g11.proyecto.tad.model.DAO.TipoHabitacionDAO;
import upo.tad.g11.proyecto.tad.model.entity.Habitacion;
import upo.tad.g11.proyecto.tad.model.entity.Reserva;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

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
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    public Reserva prepararReserva(Reserva t, TipoHabitacion tipo) {
        if (reservas.get(t.getId()) == null) {
            //Obtenemos las habitaciones de un tipo concreto y de un hotel concreto
            HabitacionDAO habs = new HabitacionDAO();
            Habitacion h = habs.getHabitacionesby(t.getHotel(), tipo);
            //Guardamos la hora de la reserva
            String pattern = "dd/MM/yyyy HH:mm:ss";

            // Create an instance of SimpleDateFormat used for formatting 
            // the string representation of date according to the chosen pattern
            DateFormat df = new SimpleDateFormat(pattern);
            // Get the today date using Calendar object.
            Calendar.getInstance().getTime();
            
            t.setFechaReserva(df.format(Calendar.getInstance().getTime()));
            t.setHabitacion(h);
        }
        return t;
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
        reservas.saveAll(t);
    }

}
