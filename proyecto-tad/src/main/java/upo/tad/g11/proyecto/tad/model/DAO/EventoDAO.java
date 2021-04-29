package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Evento;

public class EventoDAO implements DAO<Evento> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private final Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     */
    public EventoDAO() {
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
    public Evento get(Object id) {
        Evento evento = d.createQuery(Evento.class)
                .field("_id")
                .equal(id).first();

        return evento;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Evento> getAll() {
        List<Evento> eventos
                = d.createQuery(Evento.class).find().toList();
        return eventos;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Evento t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param t
     */
    @Override
    public void delete(Evento t) {
        d.delete(t);
    }
}
