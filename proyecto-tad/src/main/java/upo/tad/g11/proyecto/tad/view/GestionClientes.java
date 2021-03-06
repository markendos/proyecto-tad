package upo.tad.g11.proyecto.tad.view;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import upo.tad.g11.proyecto.tad.controller.Controlador;
import upo.tad.g11.proyecto.tad.controller.ControladorCliente;
import upo.tad.g11.proyecto.tad.controller.ControladorReserva;
import upo.tad.g11.proyecto.tad.model.entity.Cliente;
import upo.tad.g11.proyecto.tad.model.entity.Hotel;
import upo.tad.g11.proyecto.tad.model.entity.Reserva;
import upo.tad.g11.proyecto.tad.model.entity.TipoHabitacion;
import upo.tad.g11.proyecto.tad.view.form.ClienteForm;
import upo.tad.g11.proyecto.tad.view.form.ReservaForm;

/**
 *
 * @author Grupo XI
 */
@Theme("mytheme")
@Title("Clientes")
public class GestionClientes extends UI {

    Controlador controladorR = new ControladorReserva();
    Controlador controladorC = new ControladorCliente();
    Cliente clienteActual = null;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Obtenemos la sesion HTTP del usuario actual.
        WrappedSession session = getSession().getSession();

        if (session.getAttribute("usuario") == null) {
            //En caso de no existir una sesi??n activa, redirigimos al login
            UI.getCurrent().getPage().setLocation("/");
        }

        // Creamos el layout principal de la UI.
        HorizontalLayout layout = new HorizontalLayout();

        /*------------------------FORMULARIO (create)-------------------------*/
        // Layout que servira de contenedor para los componentes del formulario.
        VerticalLayout vLayoutForm = new VerticalLayout();

        /* Creamos un objeto que servira para envolver los valores de los campos
        ** del formulario de nuestra entidad CRUD. Asignamos tambien los valores
        ** por defecto de los campos y el tipo de dato que se espera recibir.
         */
        PropertysetItem itemCliente = new PropertysetItem();
        itemCliente.addItemProperty("dni", new ObjectProperty("", String.class));
        itemCliente.addItemProperty("nombre", new ObjectProperty("", String.class));
        itemCliente.addItemProperty("email", new ObjectProperty("", String.class));
        itemCliente.addItemProperty("telefono", new ObjectProperty("", String.class));

        PropertysetItem itemReserva = new PropertysetItem();
        itemReserva.addItemProperty("fechaLlegada", new ObjectProperty(null, Date.class));
        itemReserva.addItemProperty("fechaSalida", new ObjectProperty(null, Date.class));
        itemReserva.addItemProperty("hotel", new ObjectProperty(null, Hotel.class));
        itemReserva.addItemProperty("tipo", new ObjectProperty(null, TipoHabitacion.class));

        // Instanciamos un nuevo componente que contendra los campos del formulario
        ClienteForm formCliente = new ClienteForm();
        ReservaForm formReserva = new ReservaForm();

        // Realizamos el binding del formulario entre la UI y el modelo asociado.
        FieldGroup binderCliente = new FieldGroup(itemCliente);
        binderCliente.bindMemberFields(formCliente);
        // Realizamos el binding del formulario entre la UI y el modelo asociado.
        FieldGroup binderReserva = new FieldGroup(itemReserva);
        binderReserva.bindMemberFields(formReserva);

        // Creamos un boton para enviar el formulario y lo anyadimos a este
        Button crearClienteBtn = new Button("Crear Cliente");
        crearClienteBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        formCliente.addComponent(crearClienteBtn);

        Button crearReservaBtn = new Button("Crear Reserva");
        crearReservaBtn.addStyleName(ValoTheme.BUTTON_PRIMARY);
        formReserva.addComponent(crearReservaBtn);

        // Boton para volver al menu
        Button menuBtn = new Button("Men??");
        menuBtn.addStyleName(ValoTheme.BUTTON_LINK);

        // Al hacer click, se vuelve al menu principal
        menuBtn.addClickListener(e
                -> {
            UI.getCurrent().getPage().setLocation("/menu");
        }
        );

        vLayoutForm.addComponents(menuBtn, formCliente);
        vLayoutForm.addComponents(formReserva);

        /*----------------------(END)FORMULARIO (create)-----------------------*/

 /*--------------------TABLA (read, update, delete)--------------------*/
        // Layout que servira de contenedor para la tabla de la entidad.
        VerticalLayout vLayoutTable = new VerticalLayout();

        //layoutBotones.setMargin(true);
        // Contenedor para almacenar los beans de la entidad CRUD.
        BeanItemContainer<Cliente> beansClientes
                = new BeanItemContainer<>(Cliente.class);

        // Contenedor para almacenar los beans de la entidad CRUD.
        BeanItemContainer<Reserva> beansReservas
                = new BeanItemContainer<>(Reserva.class);

        beansReservas.addNestedContainerProperty("hotel.nombre");
        beansReservas.addNestedContainerProperty("habitacion.numero");

        //beans.addNestedContainerProperty("tipo.nombre");
        // Creamos la tabla y le asociamos el contenedor creado enteriormente.
        Grid gridCliente = new Grid("Clientes", beansClientes);
        Grid gridReserva = new Grid("Reservas", beansReservas);

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        gridCliente.setSelectionMode(Grid.SelectionMode.SINGLE);
        gridCliente.setEditorEnabled(true);
        gridCliente.setImmediate(true);
        gridCliente.setColumnReorderingAllowed(true);
        gridCliente.setWidth("100%");
        gridCliente.setHeightMode(HeightMode.ROW);
        gridCliente.setHeightByRows(5);

        // Create a header row to hold column filters
        HeaderRow filterRowClientes = gridCliente.appendHeaderRow();

// Set up a filter for all columns
        for (Object pid : gridCliente.getContainerDataSource()
                .getContainerPropertyIds()) {
            if (!pid.equals("reservas")) {
                HeaderCell cell = filterRowClientes.getCell(pid);

                // Have an input field to use for filter
                TextField filterField = new TextField();
                filterField.setColumns(8);

                // Update filter When the filter input is changed
                filterField.addTextChangeListener(change -> {
                    // Can't modify filters so need to replace
                    beansClientes.removeContainerFilters(pid);

                    // (Re)create the filter if necessary
                    if (!change.getText().isEmpty()) {
                        beansClientes.addContainerFilter(
                                new SimpleStringFilter(pid,
                                        change.getText(), true, false));
                    }
                });
                cell.setComponent(filterField);
            }
        }

        // Seleccionamos las columnas a visualizar y renombramos las que sean necesarias
        Object[] VISIBLE_COLUMN_IDS_CLIENTES = new String[]{"dni", "nombre", "email", "telefono"};
        gridCliente.setColumns(VISIBLE_COLUMN_IDS_CLIENTES);

        // Establecemos las propiedades de la tabla para obtener el
        // comportamiento deseado.
        gridReserva.setSelectionMode(Grid.SelectionMode.MULTI);
        gridReserva.setEditorEnabled(true);
        gridReserva.setImmediate(true);
        gridReserva.setColumnReorderingAllowed(true);
        gridReserva.setWidth("100%");
        gridReserva.setHeightMode(HeightMode.ROW);
        gridReserva.setHeightByRows(5);

        // Create a header row to hold column filters
        HeaderRow filterRowReservas = gridReserva.appendHeaderRow();

        // Set up a filter for all columns
        for (Object pid : gridReserva.getContainerDataSource()
                .getContainerPropertyIds()) {
            HeaderCell cell = filterRowReservas.getCell(pid);

            // Have an input field to use for filter
            TextField filterField = new TextField();
            filterField.setColumns(8);

            // Update filter When the filter input is changed
            filterField.addTextChangeListener(change -> {
                // Can't modify filters so need to replace
                beansReservas.removeContainerFilters(pid);

                // (Re)create the filter if necessary
                if (!change.getText().isEmpty()) {
                    beansReservas.addContainerFilter(
                            new SimpleStringFilter(pid,
                                    change.getText(), true, false));
                }
            });
            cell.setComponent(filterField);
        }

        // Seleccionamos las columnas a visualizar y renombramos las que sean necesarias
        Object[] VISIBLE_COLUMN_IDS = new String[]{"fechaLlegada", "fechaSalida", "hotel.nombre", "habitacion.numero"};
        gridReserva.setColumns(VISIBLE_COLUMN_IDS);

        Grid.Column llegadaColumn = gridReserva.getColumn("fechaLlegada");
        llegadaColumn.setHeaderCaption("Fecha de llegada");

        Grid.Column salidaColumn = gridReserva.getColumn("fechaSalida");
        salidaColumn.setHeaderCaption("Fecha de salida");

        Grid.Column hotelColumn = gridReserva.getColumn("hotel.nombre");
        hotelColumn.setHeaderCaption("Hotel");

        Grid.Column habitacionColumn = gridReserva.getColumn("habitacion.numero");
        habitacionColumn.setHeaderCaption("Habitacion");

        // Anyadimos los componentes de control para realizar las acciones de
        // y eliminar sobre los elementos de la tabla.
        Button btnEliminar = new Button("Eliminar");
        btnEliminar.addStyleName(ValoTheme.BUTTON_DANGER);
        btnEliminar.setEnabled(false);

        //Lsteners para los elementos interactivos de la tabla:
        // Habilita el boton de eliminar al seleccionar una fila de la tabla.
        gridReserva.addSelectionListener(selectionEvent -> {
            if (gridReserva.getSelectedRows().size() > 0) {
                btnEliminar.setEnabled(true);
            } else {
                btnEliminar.setEnabled(false);
            }
        });

        // Elimina el/los elemento/s de la/s fila/s seleccionada/s al pulsa el boton de eliminar.
        btnEliminar.addClickListener(
                (Button.ClickEvent event) -> {
                    for (Object itemId : gridReserva.getSelectedRows()) {
                        //Borramos la reserva del cliente que la hizo
                        ControladorCliente cc = (ControladorCliente) controladorC;
                        cc.borrarReservaCliente((Reserva) itemId);
                        //Borramos la reserva de la base de datos
                        controladorR.delete(itemId);
                        //Borramos la reserva de la tabla
                        beansReservas.removeItem(itemId);

                        Cliente c = ((Reserva) itemId).getCliente();
                        c.getReservas().remove((Reserva) itemId);

                    }
                    btnEliminar.setEnabled(false);
                });

        gridCliente.addSelectionListener(selectionEvent -> {
            if (gridCliente.getSelectedRows().size() == 1) {
                clienteActual = (Cliente) gridCliente.getSelectedRow();
                if (clienteActual != null) {
                    beansReservas.removeAllItems();
                    beansReservas.addAll(clienteActual.getReservas());
                } else {
                    beansReservas.addAll(controladorR.listar());
                }
            }
        });

        gridCliente.getEditorFieldGroup().addCommitHandler(new FieldGroup.CommitHandler() {
            @Override
            public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
            }

            @Override
            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
                Object itemId = gridCliente.getEditedItemId();
                controladorC.update(itemId);
            }
        });

        // Mapea los valores de los campos del formulario a una nueva instancia de
        // la entidad CRUD y la anyade al contenedor de beans.
        crearClienteBtn.addClickListener(e -> {
            boolean valid = true;
            for (Field field : binderCliente.getFields()) {
                valid &= field.isValid();
            }
            if (valid) {
                String errores = comprobarFormularioClientes(binderCliente.getField("dni"), binderCliente.getField("nombre"), binderCliente.getField("email"), binderCliente.getField("telefono"));

                if (errores.length() == 0) {
                    String dni = (String) binderCliente.getField("dni").getValue();
                    String nombre = (String) binderCliente.getField("nombre").getValue();
                    String email = (String) binderCliente.getField("email").getValue();
                    String telefono = (String) binderCliente.getField("telefono").getValue();

                    Cliente c = new Cliente(dni, nombre, email, telefono);
                    controladorC.add(c);
                    beansClientes.addBean(c);
                } else {
                    Notification.show("Se ha producido un error", errores, Notification.Type.ERROR_MESSAGE);
                }
            } else {
                Notification.show("Los datos no son v??lidos", "Revise los campos del fomulario", Notification.Type.ERROR_MESSAGE);
            }
        });
        crearReservaBtn.addClickListener(e -> {
            String errores = comprobarFormularioReservas(binderReserva.getField("fechaLlegada"), binderReserva.getField("fechaSalida"), binderReserva.getField("tipo"), binderReserva.getField("hotel"));
            if (errores.length() == 0) {
                String pattern = "dd/MM/yyyy";

                // Create an instance of SimpleDateFormat used for formatting 
                // the string representation of date according to the chosen pattern
                DateFormat df = new SimpleDateFormat(pattern);

                String fechaLlegada = df.format(binderReserva.getField("fechaLlegada").getValue());
                String fechaSalida = df.format(binderReserva.getField("fechaSalida").getValue());
                TipoHabitacion tipo = (TipoHabitacion) binderReserva.getField("tipo").getValue();
                Hotel hotel = (Hotel) binderReserva.getField("hotel").getValue();

                Reserva r = new Reserva(fechaLlegada, fechaSalida);
                r.setHotel(hotel);
                ControladorReserva cr = (ControladorReserva) controladorR;
                ControladorCliente cc = (ControladorCliente) controladorC;
                r = cr.prepararReserva(r, tipo);
                if (r.getHabitacion() == null) {
                    errores += "No se ha encontrado una habitaci??n disponible de esas caracter??sticas\n";
                }
                if (clienteActual == null) {
                    errores += "Debe seleccionar a un cliente\n";
                }
                if (errores.length() == 0) {
                    r.setCliente(clienteActual);

                    cr.add(r);
                    beansReservas.addBean(r);

                    clienteActual.getReservas().add(r);

                    cc.update(clienteActual);
                    clienteActual = null;
                } else {
                    Notification.show("Se ha producido un error", errores, Notification.Type.ERROR_MESSAGE);
                }
            } else {
                Notification.show("Se ha producido un error", errores, Notification.Type.ERROR_MESSAGE);
            }
            //beansReservas.addBean(r);
            //beansClientes.addBean(c);
        });
        // Boton para realizar la accion de logout
        Button cerrarSesionBtn = new Button("Cerrar Sesi??n");
        cerrarSesionBtn.addStyleName(ValoTheme.BUTTON_SMALL);

        // Al hacer click, se invalida la sesion actual y se redirecciona a la
        // pagina de login
        cerrarSesionBtn.addClickListener(e
                -> {
            session.invalidate();
            UI.getCurrent().getPage().setLocation("/");
        }
        );

        // Anyadimos los componetes al layout de la tabla y se aplican los estilos.
        vLayoutTable.addComponents(cerrarSesionBtn, gridCliente, gridReserva, btnEliminar);
        vLayoutTable.setSpacing(true);

        //Agregamos a la tabla la informacion de la BD
        beansClientes.addAll(controladorC.listar());
        beansReservas.addAll(controladorR.listar());

        /*------------------(END)TABLA (read, update, delete)------------------*/
        // Anyadimos los componentes que contienen los layouts del formulario y
        // la tabla al layout principal de la UI. Se aplican tambian los estilos.
        layout.addComponents(vLayoutForm, vLayoutTable);
        layout.setMargin(true);
        layout.setSpacing(true);
        layout.setWidth("100%");

        setContent(layout);
    }

    public String comprobarFormularioClientes(Field f1, Field f2, Field f3, Field f4) {
        String errores = "";
        if (f1 == null || f2 == null || f2 == null || f4 == null) {
            errores += "No debe haber campos vac??os";
        } else {
            String v1 = (String) f1.getValue();
            String v2 = (String) f2.getValue();
            String v3 = (String) f3.getValue();
            String v4 = (String) f4.getValue();
            if (v1.trim() == "" || v2.trim() == "" || v3.trim() == "" || v4.trim() == "") {
                errores += "No debe haber campos sin rellenar\n";
            }

            if (v1.length() != 9) {
                errores += "Introduzca un DNI valido\n";
            }
            if (v4.length() != 9) {
                errores += "Introduzca un Telefono valido\n";
            }
        }
        return errores;
    }

    public String comprobarFormularioReservas(Field f1, Field f2, Field f3, Field f4) {
        String errores = "";
        if (f1 == null || f2 == null || f2 == null || f4 == null) {
            errores += "No debe haber campos vac??os";
        } else {
            Date v1 = (Date) f1.getValue();
            Date v2 = (Date) f2.getValue();
            TipoHabitacion v3 = (TipoHabitacion) f3.getValue();
            Hotel v4 = (Hotel) f4.getValue();
            if (v1 == null || v2 == null || v3 == null || v4 == null) {
                errores += "No debe haber campos vac??os";

            } else if (v2.before(v1)) {
                errores += "Asegurese de que la fecha de llegada es anterior a la fecha de Salida.";
            }
        }
        return errores;
    }

    @WebServlet(value = {"/clientes/*"}, name = "GestionClientes", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = GestionClientes.class)
    public static class GestionHotelesServlet extends VaadinServlet {
    }
}
