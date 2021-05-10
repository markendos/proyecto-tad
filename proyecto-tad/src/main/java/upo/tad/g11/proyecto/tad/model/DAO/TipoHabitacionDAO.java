package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;

public class TipoHabitacionDAO implements DAO<TipoHabitacion> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private final Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     */
    public TipoHabitacionDAO() {
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
    public TipoHabitacion get(Object id) {
        TipoHabitacion tipoHabitacion = d.createQuery(TipoHabitacion.class)
                .field("_id")
                .equal(id).first();

        return tipoHabitacion;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<TipoHabitacion> getAll() {
        List<TipoHabitacion> tipoHabitacions
                = d.createQuery(TipoHabitacion.class).find().toList();
        return tipoHabitacions;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(TipoHabitacion t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param t
     */
    @Override
    public void delete(TipoHabitacion t) {
        d.delete(t);
    }

    @Override
    public void saveAll(List<TipoHabitacion> t) {
        d.save(t);
    }
}
