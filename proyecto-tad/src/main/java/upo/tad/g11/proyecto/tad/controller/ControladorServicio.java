package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.ServicioDAO;
import upo.tad.g11.proyecto.tad.model.entity.Servicio;

/**
 * Este controlador se encarga de gestionar las operaciones de los servicios
 *
 *
 * @author Amalio
 */
public class ControladorServicio implements Controlador<Servicio> {

    //Definicion de los atributos
    private ServicioDAO servicios = new ServicioDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    
    public void add(Servicio t) {
        if (servicios.get(t.getId()) == null) {
            this.servicios.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Servicio t) {
        this.servicios.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Servicio t) {
        
        this.servicios.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Servicio get(Servicio t) {
        Servicio r = servicios.get(t.getId());
        return r;
    }
    
    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return servicios.getAll();
    }

    @Override
    public void addAll(List<Servicio> t) {
        servicios.saveAll(t);
    }
}
