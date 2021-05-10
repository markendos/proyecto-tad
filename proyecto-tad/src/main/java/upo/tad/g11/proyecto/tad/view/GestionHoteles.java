package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Iterator;
import upo.tad.g11.proyecto.tad.controller.ControladorHotel;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;

/**
 *
 * @author Alvaro
 */
@Theme("mytheme")
@Title("Hoteles")
public class GestionHoteles extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Creamos el layout
        final VerticalLayout layout = new VerticalLayout();
        //Layout de la pagina
        layout.setMargin(true);
        ControladorHotel cHotel = new ControladorHotel();
        //Creamos la sesion
        WrappedSession sesion = getSession().getSession();

        if (sesion.getAttribute("usuario") == null) {     //En caso de no existir una sesión activa, redirigimos al login
            UI.getCurrent().getPage().setLocation("/");
        }
        
        Label lblInicio = new Label("Gestión de los hoteles");

        //Botón para cerrar la sesión
        Button cerrarSesion = new Button("Cerrar sesión");
        
        TextField tfID = new TextField("ID:");
        TextField tfNombre = new TextField("Nombre:");
        TextField tfUbicacion = new TextField("Ubicación:");
        TextField tfCalidad = new TextField("Calidad:");
        
        Button crearHotel = new Button("Crear Hotel");

        //Añadimos los componentes creados
        layout.addComponents(lblInicio, tfID, tfNombre, tfUbicacion, tfCalidad, crearHotel, cerrarSesion);
        
        crearHotel.addClickListener(e -> {    //Evento para cerrar la sesión y volver al login
            
            String[] params = {tfID.getValue(), tfNombre.getValue(), tfUbicacion.getValue(), tfCalidad.getValue()};
            
            cHotel.add(new Hotel(params[0], params[1], params[2], params[3]));
        });

        cerrarSesion.addClickListener(e -> {    //Evento para cerrar la sesión y volver al login
            sesion.invalidate();
            UI.getCurrent().getPage().setLocation("/");
        });
        
        
        Table tablaHoteles = new Table("Hoteles");     //Tabla en la que se mostrarán los hoteles
        tablaHoteles.setPageLength(tablaHoteles.size());      //El tamaño de la tabla se ajustará al número de elementos
        tablaHoteles.setImmediate(true);
        tablaHoteles.addContainerProperty("ID", String.class, null);
        tablaHoteles.addContainerProperty("Nombre", String.class, null);
        tablaHoteles.addContainerProperty("Ubicación", String.class, null);
        tablaHoteles.addContainerProperty("Calidad", String.class, null);
        layout.addComponent(tablaHoteles);
        
        //Mostramos en la tabla los hoteles ya creados
        Iterator it = cHotel.listar().iterator();
        while (it.hasNext()) {  //Iteramos dobre la lista de hoteles
            Hotel h = (Hotel) it.next();

            //Construimos el elemento de la fila
            Object item[] = {h.getId(), h.getNombre(), h.getUbicacion(), h.getCalidad()};
            tablaHoteles.addItem(item, h.getId());     //Añadimos la nueva fila

        }

        setContent(layout);
    }

    @WebServlet(value = {"/hoteles/*"}, name = "GestionHoteles", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionHoteles.class)
    public static class GestionHotelesServlet extends VaadinServlet {
    }
}
