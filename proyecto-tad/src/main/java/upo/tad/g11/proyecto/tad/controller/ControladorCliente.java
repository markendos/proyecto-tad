package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.ClienteDAO;
import upo.tad.g11.proyecto.tad.model.DAO.DAO;
import upo.tad.g11.proyecto.tad.model.entity.Cliente;


/**
 * Este controlador se encarga de gestionar las operaciones de los clientes
 *
 *
 * @author Amalio
 */
public class ControladorCliente implements Controlador<Cliente> {

    //Definicion de los atributos
    private DAO<Cliente> clientes = new ClienteDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    
    public void add(Cliente t) {
        if (clientes.get(t.getDni()) == null) {
            this.clientes.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Cliente t) {
        this.clientes.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Cliente t) {
        
        this.clientes.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Cliente get(Cliente t) {
        Cliente c = clientes.get(t.getDni());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List listar() {
        return clientes.getAll();
    }

    @Override
    public void addAll(List<Cliente> t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
