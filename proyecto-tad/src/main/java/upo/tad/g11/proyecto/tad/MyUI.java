package upo.tad.g11.proyecto.tad;

import com.mongodb.MongoClient;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

@Theme("mytheme")
public class MyUI extends UI {
    
    private static Datastore datastore = null;

    public static Datastore getConnection() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            datastore = morphia.createDatastore(new MongoClient(), "TAD_Proyecto");
            datastore.ensureIndexes();
        }
        return datastore;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        layout.addComponents();
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
