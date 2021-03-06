package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.PersonalDAO;
import upo.tad.g11.proyecto.tad.model.entity.Personal;

public class ControladorPersonal implements Controlador<Personal> {

    //Definicion de los atributos
    private PersonalDAO personal = new PersonalDAO();

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera agregado
     * en la BD
     *
     */
    public void add(Personal t) {
        if (personal.get(t.getId()) == null) {
            this.personal.save(t);
        }
    }

    /**
     * Metodo que formatea los datos y prepara un nuevo objeto que sera
     * actualizado en la BD
     *
     */
    public void update(Personal t) {
        this.personal.save(t);

    }

    /**
     * Metodo que recibe un id y lo elimina de la BD
     *
     */
    public void delete(Personal t) {

        this.personal.delete(t);
    }

    /**
     * Metodo que pide un id al DAO y lo devuelve a la pantalla principal
     *
     */
    public Personal get(Personal t) {
        Personal c = personal.get(t.getId());
        return c;
    }

    /**
     * Metodo que pide al DAO todos los objetos DTO para mostrarselos al usuario
     * como lista
     *
     */
    @Override
    public List<Personal> listar() {
        return personal.getAll();
    }

    
    public boolean check(String email, String pass) {
        return this.personal.check(email, pass);
    }

    @Override
    public void addAll(List<Personal> t) {
        personal.saveAll(t);
    }
}
