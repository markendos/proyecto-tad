package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.DAO;
import upo.tad.g11.proyecto.tad.model.DAO.HabitacionDAO;
import upo.tad.g11.proyecto.tad.model.entity.Habitacion;

public class ControladorHabitacion implements Controlador<Habitacion> {
    
    //Definicion de los atributos
    private DAO<Habitacion> repository = new HabitacionDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    
    public void add(Habitacion t) {
        if (repository.get(t.getId()) == null) {
            this.repository.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Habitacion t) {
        this.repository.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Habitacion t) {
        
        this.repository.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Habitacion get(Habitacion t) {
        Habitacion c = repository.get(t.getId());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return repository.getAll();
    }

    @Override
    public void addAll(List<Habitacion> t) {
        repository.saveAll(t);
    }

}
