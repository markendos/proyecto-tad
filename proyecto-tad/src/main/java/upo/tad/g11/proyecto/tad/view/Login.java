package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import upo.tad.g11.proyecto.tad.controller.ControladorPersonal;

@Theme("mytheme")
@Title("Login")
public class Login extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Creamos el layout
        final VerticalLayout layout = new VerticalLayout();
        //Layout de la pagina
        layout.setMargin(true);

        //Creamos la sesion
        WrappedSession sesion = getSession().getSession();

        if (sesion.getAttribute("usuario") != null) {     //En caso de existir una sesión activa, redirigimos al menú
            UI.getCurrent().getPage().setLocation("/hoteles");
        }
        
        //Formulario de inicio de sesion
        TextField tfEmail = new TextField("Introduzca su correo para iniciar sesion:");
        TextField tfPass = new TextField("Introduzca su contraseña para iniciar sesion:");
        Button submit = new Button("Acceder");

        //Guardamos el formulario en el layout
        layout.addComponent(tfEmail);
        layout.addComponent(tfPass);
        layout.addComponent(submit);

        //Listener para guardar la sesion
        submit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Recogemos los valores del formulario
                String email = tfEmail.getValue();
                String pass = tfPass.getValue();

                ControladorPersonal cp = new ControladorPersonal();
                if (cp.Check(email, pass)) {

                    //Guardamos en la sesion el email del usuario
                    sesion.setAttribute("usuario", email);
                    //Redireccionamos a la pagina principal
                    UI.getCurrent().getPage().setLocation("/hoteles");

                    System.out.println("\n\nSESION ACTIVADA\n\n");
                }else{
                    Notification.show("Error de autentificación", "Credenciales no válidos", Notification.Type.ERROR_MESSAGE); 
                }
            }

        });
        //Comprobamos si el usuario tiene ya una sesion activa
        if (sesion.getAttribute("usuario") != null) {
            //UI.getCurrent().getPage().setLocation("./MantenimientoDeEntidad");
            System.out.println("\n\nSESION ACTIVA\n\n");
        }

        setContent(layout);
    }

    @WebServlet(value = {"/*", "/VAADIN/*"}, name = "Login", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Login.class)
    public static class Servlet extends VaadinServlet {
    }
}
