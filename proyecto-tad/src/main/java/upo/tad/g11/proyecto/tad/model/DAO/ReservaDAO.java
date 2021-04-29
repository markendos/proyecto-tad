/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Reserva;

/**
 * Este DAO se encarga de aplicar las operaciones de los reservas
 *
 *
 * @author Amalio
 */
public class ReservaDAO implements DAO<Reserva> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     * @param c
     */
    public ReservaDAO() {
        d = Connection.getConnection();
    }

    /**
     * Metodo que recupera un Documento de la coleccion segun el id pasado por
     * parametro
     *
     * @param id
     * @return
     */
    @Override
    public Reserva get(Object id) {
        Reserva reserva = d.createQuery(Reserva.class)
                .field("_id")
                .equal(id).first();

        return reserva;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Reserva> getAll() {
        List<Reserva> reservas
                = d.createQuery(Reserva.class).find().toList();
        return reservas;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Reserva t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param id
     */
    @Override
    public void delete(Reserva t) {
        d.delete(t);
    }

}
