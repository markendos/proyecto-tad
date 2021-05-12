package upo.tad.g11.proyecto.tad.view.form;

import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import java.util.List;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorCliente;
import upo.tad.g11.proyecto.tad.model.entity.Cliente;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

public class ClienteForm extends FormLayout {

    // Campos del formulario
    Label descripcion = new Label("Gestion de clientes");
    TextField dni = new TextField("DNI");
    TextField nombre = new TextField("Nombre");
    TextField email = new TextField("Correo electronico");
    TextField telefono = new TextField("Teléfono");

    // Constructor por defecto
    public ClienteForm() {
        dni.addValidator(new BeanValidator(Cliente.class, "dni"));
        nombre.addValidator(new BeanValidator(Cliente.class, "nombre"));
        email.addValidator(new BeanValidator(Cliente.class, "email"));
        telefono.addValidator(new BeanValidator(Cliente.class, "telefono"));
        
        setSpacing(true);
        addComponents(descripcion,dni, nombre, email, telefono);
    }

    private List<Hotel> getClientes() {
        Controlador c = new ControladorCliente();
        return c.listar();
    }

}
