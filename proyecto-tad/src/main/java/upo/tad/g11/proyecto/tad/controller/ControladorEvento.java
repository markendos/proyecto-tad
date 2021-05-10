package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.EventoDAO;
import upo.tad.g11.proyecto.tad.model.DAO.DAO;
import upo.tad.g11.proyecto.tad.model.entity.Evento;


/**
 * Este controlador se encarga de gestionar las operaciones de los eventos
 *
 *
 * @author Amalio
 */
public class ControladorEvento implements Controlador<Evento> {

    //Definicion de los atributos
    private DAO<Evento> eventos = new EventoDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    
    public void add(Evento t) {
        if (eventos.get(t.getId()) == null) {
            this.eventos.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Evento t) {
        this.eventos.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Evento t) {
        
        this.eventos.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Evento get(Evento t) {
        Evento c = eventos.get(t.getId());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return eventos.getAll();
    }

    @Override
    public void addAll(List<Evento> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
