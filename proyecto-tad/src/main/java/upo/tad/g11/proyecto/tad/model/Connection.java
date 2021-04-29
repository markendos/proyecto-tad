/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upo.tad.g11.proyecto.tad.model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Amalio
 */
public final class Connection {

    private static Connection instance;
    public String value;

    // Conectar al servidor MongoDB
    public MongoClient mongoClient;// = new MongoClient("localhost", 27017);

    // Conectar a la base de datos
    public MongoDatabase db;// = mongoClient.getDatabase("EPD_EV_2");

    private Connection(String host, int port, String DBname) {

        // Conectar al servidor MongoDB
        this.mongoClient = new MongoClient(host, port);

        // Conectar a la base de datos
        this.db = mongoClient.getDatabase(DBname);

    }

    public static Connection getInstance(String host, int port, String DBname) {
        if (instance == null) {
            instance = new Connection(host, port, DBname);
        }
        return instance;
    }
    
    public static void close(){
        instance.close();
    }
}
