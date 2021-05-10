package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import java.util.List;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorCliente;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class ClienteForm extends FormLayout {

    // Campos del formulario
    TextField dni = new TextField("DNI");
    TextField name = new TextField("Nombre");
    TextField email = new TextField("Correo electronico");
    TextField telefono = new TextField("Teléfono");

    // Constructor por defecto
    public ClienteForm() {
        //hotel.addItems(getHoteles());
        //tipo.addItems(getTipos());
        setSpacing(true);
        addComponents(dni, name, email, telefono);
    }

    private List<Hotel> getClientes() {
        Controlador c = new ControladorCliente();
        return c.listar();
    }

}
