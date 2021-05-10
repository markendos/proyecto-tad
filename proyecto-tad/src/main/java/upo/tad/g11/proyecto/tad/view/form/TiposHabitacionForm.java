package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class TiposHabitacionForm extends FormLayout {

    // Campos del formulario
    TextField nombre = new TextField("Nombre");
    TextField metros = new TextField("Metros");
    CheckBox terraza = new CheckBox("¿Terraza?");
    TextField precio = new TextField("Precio/noche");

    // Constructor por defecto
    public TiposHabitacionForm() {
        setSpacing(true);
        addComponents(nombre, metros, terraza, precio);
    }
}
