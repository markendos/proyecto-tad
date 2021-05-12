package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.InstalacionDAO;
import upo.tad.g11.proyecto.tad.model.DAO.DAO;
import upo.tad.g11.proyecto.tad.model.entity.Instalacion;


/**
 * Este controlador se encarga de gestionar las operaciones de los instalaciones
 *
 *
 * @author Amalio
 */
public class ControladorInstalacion implements Controlador<Instalacion> {

    //Definicion de los atributos
    private DAO<Instalacion> instalaciones = new InstalacionDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    
    public void add(Instalacion t) {
        if (instalaciones.get(t.getId()) == null) {
            this.instalaciones.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Instalacion t) {
        this.instalaciones.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Instalacion t) {
        
        this.instalaciones.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Instalacion get(Instalacion t) {
        Instalacion c = instalaciones.get(t.getId());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return instalaciones.getAll();
    }

    @Override
    public void addAll(List<Instalacion> t) {
        instalaciones.saveAll(t);
    }

   
}
