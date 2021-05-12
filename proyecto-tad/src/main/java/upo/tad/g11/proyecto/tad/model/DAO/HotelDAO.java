package upo.tad.g11.proyecto.tad.model.DAO;

import dev.morphia.Datastore;
import java.util.List;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

/**
 *
 * @author Alvaro
 */
public class HotelDAO implements DAO<Hotel> {
    //Definicion de los atributos
    //Conexion con la BD y Coleccion de la BD
    private final Datastore d;

    /**
     * Constructor que inicializa la coleccion
     *
     */
    public HotelDAO() {
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
    public Hotel get(Object id) {
        Hotel hotel = d.createQuery(Hotel.class)
                .field("_id")
                .equal(id).first();

        return hotel;
    }

    /**
     * Metodo que recibe todos los Documentos de la coleccion y los devuelve en
     * formato lista
     *
     * @return
     */
    @Override
    public List<Hotel> getAll() {
        List<Hotel> hoteles
                = d.createQuery(Hotel.class).find().toList();
        return hoteles;
    }

    /**
     * Metodo que almacena un nuevo documento en la BD a partir de un objeto DTO
     *
     * @param t
     */
    @Override
    public void save(Hotel t) {
        d.save(t);
    }

    /**
     * Metodo que elimina un Documento en la BD a partir de un id obtenido por
     * parametro
     *
     * @param t
     */
    @Override
    public void delete(Hotel t) {
        d.delete(t);
    }

    @Override
    public void saveAll(List<Hotel> t) {
        d.save(t);
    }
}
