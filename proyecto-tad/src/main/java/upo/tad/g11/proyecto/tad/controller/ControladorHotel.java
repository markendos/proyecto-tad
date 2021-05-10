package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.HotelDAO;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class ControladorHotel implements Controlador<Hotel> {

    //Definicion de los atributos
    private HotelDAO hoteles = new HotelDAO();

    @Override
    public void add(Hotel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hotel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Hotel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Hotel get(Hotel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hotel> listar() {
        return hoteles.getAll();
    }
}
