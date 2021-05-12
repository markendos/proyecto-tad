package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Servicio;

/**
 * Este DAO se encarga de aplicar las operaciones de los servicios
 *
 *
 * @author Amalio
 */
public class ServicioDAO implements DAO<Servicio> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     * @param c
     */
    public ServicioDAO() {
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
    public Servicio get(Object id) {
        Servicio servicio = d.createQuery(Servicio.class)
                .field("_id")
                .equal(id).first();

        return servicio;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Servicio> getAll() {
        List<Servicio> servicios
                = d.createQuery(Servicio.class).find().toList();
        return servicios;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Servicio t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param id
     */
    @Override
    public void delete(Servicio t) {
        d.delete(t);
    }

    @Override
    public void saveAll(List<Servicio> t) {
        d.save(t);
    }

}
