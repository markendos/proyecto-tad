package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import java.util.Arrays;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class HotelForm extends FormLayout {

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField ubicacion = new TextField("Ubicación");
    OptionGroup tipo = new OptionGroup("Tipo", Arrays.asList("Hotel", "Hostal", "Resort"));
    ComboBox zona = new ComboBox("Zona", Arrays.asList("Playa", "Rural", "Ciudad", "Aeropuerto", "Otro"));
    ComboBox calidad = new ComboBox("Calidad", Arrays.asList("1", "2", "3", "4", "5"));
    

    // Constructor por defecto
    public HotelForm() {
        nombre.addValidator(new BeanValidator(Hotel.class, "nombre"));
        ubicacion.addValidator(new BeanValidator(Hotel.class, "ubicacion"));
        tipo.addValidator(new BeanValidator(Hotel.class, "tipo"));
        zona.addValidator(new BeanValidator(Hotel.class, "zona"));
        calidad.addValidator(new BeanValidator(Hotel.class, "calidad"));
        
        setSpacing(true);
        addComponents(nombre, ubicacion, tipo, zona, calidad);
    }

}
