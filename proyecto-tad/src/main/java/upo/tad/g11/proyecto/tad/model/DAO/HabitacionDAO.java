package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Habitacion;

public class HabitacionDAO implements DAO<Habitacion> {

    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private final Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     */
    public HabitacionDAO() {
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
    public Habitacion get(Object id) {
        Habitacion habitacion = d.createQuery(Habitacion.class)
                .field("_id")
                .equal(id).first();

        return habitacion;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Habitacion> getAll() {
        List<Habitacion> habitacions
                = d.createQuery(Habitacion.class).find().toList();
        return habitacions;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Habitacion t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param t
     */
    @Override
    public void delete(Habitacion t) {
        d.delete(t);
    }

    @Override
    public void saveAll(List<Habitacion> t) {
        d.save(t);
    }
}
