package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.DAO;
import upo.tad.g11.proyecto.tad.model.DAO.TipoHabitacionDAO;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class ControladorTipoHabitacion implements Controlador<TipoHabitacion> {

    //Definicion de los atributos
    private DAO<TipoHabitacion> repository = new TipoHabitacionDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    public void add(TipoHabitacion t) {
        if (repository.get(t.getId()) == null) {
            this.repository.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(TipoHabitacion t) {
        this.repository.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(TipoHabitacion t) {

        this.repository.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public TipoHabitacion get(TipoHabitacion t) {
        TipoHabitacion c = repository.get(t.getId());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List<TipoHabitacion> listar() {
        return repository.getAll();
    }

    @Override
    public void addAll(List<TipoHabitacion> t) {
        this.repository.saveAll(t);
    }
}
