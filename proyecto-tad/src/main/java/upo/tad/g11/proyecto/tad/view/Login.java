package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import upo.tad.g11.proyecto.tad.controller.ControladorPersonal;

@Theme("mytheme")
@Title("Login")
public class Login extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //Creamos el layout del formulario de login
        FormLayout loginForm = new FormLayout();
        loginForm.setSpacing(true);
        loginForm.setMargin(true);

        //Creamos la sesion
        WrappedSession sesion = getSession().getSession();

        if (sesion.getAttribute("usuario") != null) {     //En caso de existir una sesión activa, redirigimos al menú
            UI.getCurrent().getPage().setLocation("/hoteles");
        }
        String content = "<img src=\"https://i.ibb.co/5F2dsWB/Logo.png\"/>";

        Label label = new Label(content, ContentMode.HTML);
        loginForm.addComponent(label);
        //Formulario de inicio de sesion
        TextField tfEmail = new TextField("Email:");
        PasswordField tfPass = new PasswordField("Contraseña:");
        Button submit = new Button("Acceder");
        submit.addStyleName(ValoTheme.BUTTON_PRIMARY);

        //Guardamos el formulario en el layout
        loginForm.addComponents(tfEmail, tfPass, submit);
        
        // Creamos el panel de login
        Panel loginPanel = new Panel("Login");
        loginPanel.setContent(loginForm);
        loginPanel.setWidth(null);

        //Listener para guardar la sesion
        submit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //Recogemos los valores del formulario
                String email = tfEmail.getValue();
                String pass = tfPass.getValue();

                ControladorPersonal cp = new ControladorPersonal();
                if (cp.check(email, pass) || (email.equals("admin") && pass.equals("admin"))) {

                    //Guardamos en la sesion el email del usuario
                    sesion.setAttribute("usuario", email);
                    //Redireccionamos a la pagina principal
                    UI.getCurrent().getPage().setLocation("/menu");

                    System.out.println("\n\nSESION ACTIVADA\n\n");
                } else {
                    Notification.show("Error de autentificación", "Credenciales no válidas", Notification.Type.ERROR_MESSAGE);
                }
            }

        });
        //Comprobamos si el usuario tiene ya una sesion activa
        if (sesion.getAttribute("usuario") != null) {
            //UI.getCurrent().getPage().setLocation("./MantenimientoDeEntidad");
            System.out.println("\n\nSESION ACTIVA\n\n");
        }

        //Layout de la pagina
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.addComponent(loginPanel);
        layout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @WebServlet(value = {"/*", "/VAADIN/*"}, name = "Login", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = Login.class)
    public static class Servlet extends VaadinServlet {
    }
}
