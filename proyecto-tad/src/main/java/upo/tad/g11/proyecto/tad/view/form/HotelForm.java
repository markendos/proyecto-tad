package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class HotelForm extends FormLayout {

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField ubicacion = new TextField("Ubicación");
    TextField calidad = new TextField("Calidad");

    // Constructor por defecto
    public HotelForm() {
        nombre.addValidator(new BeanValidator(Hotel.class, "nombre"));
        ubicacion.addValidator(new BeanValidator(Hotel.class, "ubicacion"));
        calidad.addValidator(new BeanValidator(Hotel.class, "calidad"));
        
        setSpacing(true);
        addComponents(nombre, ubicacion, calidad);
    }

}
