package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class HotelForm extends FormLayout {

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField ubicacion = new TextField("Ubicación");
    TextField calidad = new TextField("Calidad");

    // Constructor por defecto
    public HotelForm() {
        setSpacing(true);
        addComponents(nombre, ubicacion, calidad);
    }

}