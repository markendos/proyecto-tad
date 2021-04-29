/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model.DAO;

import com.mongodb.MongoClient;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

/**
 *
 * @author Amalio
 */
public final class Connection {

    private static Datastore datastore = null;

    public static Datastore getConnection() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            datastore = morphia.createDatastore(new MongoClient(), "TAD_Proyecto");
            datastore.ensureIndexes();
        }
        return datastore;
    }

}
