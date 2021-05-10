package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("mytheme")
@Title("Menu")
public class Menu extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Obtenemos la sesion HTTP del usuario actual.
        WrappedSession session = getSession().getSession();

        //Creamos el layout
        VerticalLayout layout = new VerticalLayout();

        //Comprobamos si el usuario tiene ya una sesion activa
        /*if (session.getAttribute("usuario") != null) {     //En caso de no existir una sesión activa, redirigimos al login
            UI.getCurrent().getPage().setLocation("/");
        }*/
        String content = "<h1><strong>Menú</strong></h1><ul>";
        content += "<li><a href='" + vaadinRequest.getContextPath() + "/hoteles" + "'><h2>Gestión de Hoteles</h2></a></li>";
        content += "<li><a href='" + vaadinRequest.getContextPath() + "/habitaciones" + "'><h2>Gestión de Habitaciones</h2></a></li>";
        content += "<li><a href='" + vaadinRequest.getContextPath() + "/tiposHabitacion" + "'><h2>Gestión de Tipos de Habitación</h2></a></li>";
        content += "</ul>";

        Label label = new Label(content, ContentMode.HTML);

        // Boton para realizar la accion de logout
        Button cerrarSesionBtn = new Button("Cerrar Sesión");
        cerrarSesionBtn.addStyleName(ValoTheme.BUTTON_SMALL);

        // Al hacer click, se invalida la sesion actual y se redirecciona a la
        // pagina de login
        cerrarSesionBtn.addClickListener(e
                -> {
            session.invalidate();
            UI.getCurrent().getPage().setLocation("/");
        }
        );

        layout.addComponents(cerrarSesionBtn, label);
        layout.setSpacing(true);
        layout.setMargin(true);
        setContent(layout);
    }

    @WebServlet(value = {"/menu/*"}, name = "Menu", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Menu.class)
    public static class MenuServlet extends VaadinServlet {
    }
}
