package upo.tad.g11.proyecto.tad.controller;

import java.util.List;
import upo.tad.g11.proyecto.tad.model.DAO.HotelDAO;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

/**
 *
 * @author Alvaro
 */
public class ControladorHotel implements Controlador {
    
    HotelDAO hotelDAO = new HotelDAO();

    @Override
    public void add(String[] args) {
        Hotel h = new Hotel(args[0], args[1], args[2], args[3]);
        hotelDAO.save(h);
    }

    @Override
    public void update(int id, String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Hotel> getListado(){
        return hotelDAO.getAll();
    }
    
}
