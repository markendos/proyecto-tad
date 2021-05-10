package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import upo.tad.g11.proyecto.tad.controller.ControladorTipoHabitacion;

public class HabitacionForm extends FormLayout {

    // Campos del formulario
    TextField numero = new TextField("Nº de habitación");
    CheckBox fumador = new CheckBox("¿Fumador?");
    ComboBox tipo = new ComboBox("Tipo de habitación");

    // Constructor por defecto
    public HabitacionForm() {
        Controlador c = new ControladorTipoHabitacion();
        tipo.addItems(t);
        setSpacing(true);
        addComponents(camas, metros, fumador, terraza, precio);
    }
    
    public void cargarTipos() {
        tipo.
    }
}
