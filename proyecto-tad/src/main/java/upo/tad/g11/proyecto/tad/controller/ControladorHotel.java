package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.DAO;
import upo.tad.g11.proyecto.tad.model.DAO.HotelDAO;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

/**
 *
 * @author Alvaro
 */
public class ControladorHotel implements Controlador<Hotel> {

    //Definicion de los atributos
    private DAO<Hotel> hoteles = new HotelDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    public void add(Hotel t) {
        if (hoteles.get(t.getId()) == null) {
            this.hoteles.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Hotel t) {
        this.hoteles.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Hotel t) {

        this.hoteles.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Hotel get(Hotel t) {
        Hotel c = hoteles.get(t.getId());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return hoteles.getAll();
    }

}
