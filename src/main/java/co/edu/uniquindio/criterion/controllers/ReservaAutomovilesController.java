package co.edu.uniquindio.criterion.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import co.edu.uniquindio.criterion.model.AlquilerVehiculo;
import co.edu.uniquindio.criterion.model.Ciudad;
import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.model.CompraReservaAutomovil;
import co.edu.uniquindio.criterion.model.MetodoPago;
import co.edu.uniquindio.criterion.model.Pais;
import co.edu.uniquindio.criterion.model.ServiciosAdicionales;
import co.edu.uniquindio.criterion.model.Vehiculo;
import co.edu.uniquindio.criterion.repositories.AlquilerVehiculoRepo;
import co.edu.uniquindio.criterion.repositories.CiudadRepo;
import co.edu.uniquindio.criterion.repositories.CompraReservaAutomovilRepo;
import co.edu.uniquindio.criterion.repositories.MetodoPagoRepo;
import co.edu.uniquindio.criterion.repositories.PaisRepo;
import co.edu.uniquindio.criterion.repositories.ServiciosAdicionalesRepo;
import co.edu.uniquindio.criterion.repositories.VehiculoRepo;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class ReservaAutomovilesController implements Initializable {

    @Autowired
    private SceneController sceneController;
    private ObservableList<Vehiculo> listaVehiculosObservable = FXCollections.observableArrayList();
    private ObservableList<ServiciosAdicionales> listaServiciosObservable = FXCollections.observableArrayList();
    private ObservableList<CompraReservaAutomovil> listaReservasObservable = FXCollections.observableArrayList();
    private Cliente clienteLogueado;
    private Vehiculo vehiculoSeleccionado;
    private CompraReservaAutomovil reservaSeleccionada;
    private static final String VALIDACION_DATOS = "Validacion de datos";
    @Autowired
    private PaisRepo paisRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private MetodoPagoRepo metodoPagoRepo;
    @Autowired
    private VehiculoRepo vehiculoRepo;
    @Autowired
    private ServiciosAdicionalesRepo serviciosAdicionalesRepo;
    @Autowired
    private AlquilerVehiculoRepo alquilerVehiculoRepo;
    @Autowired
    private CompraReservaAutomovilRepo compraReservaAutomovilRepo;

    @FXML
    private TableColumn<CompraReservaAutomovil, LocalDate> ColumnaFechaEntrega;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnReservar;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField buscadorAuto;

    @FXML
    private TextField buscadorReserva;

    @FXML
    private TableColumn<Vehiculo, Long> columnaCapacidad;

    @FXML
    private TableColumn<CompraReservaAutomovil, String> columnaDescripcion;

    @FXML
    private TableColumn<ServiciosAdicionales, String> columnaDescripcionServicio;

    @FXML
    private TableColumn<Vehiculo, String> columnaDisponible;

    @FXML
    private TableColumn<Vehiculo, String> columnaEstadoAuto;

    @FXML
    private TableColumn<CompraReservaAutomovil, LocalDate> columnaFechaReserva;

    @FXML
    private TableColumn<CompraReservaAutomovil, Long> columnaId;

    @FXML
    private TableColumn<Vehiculo, String> columnaMarca;

    @FXML
    private TableColumn<ServiciosAdicionales, String> columnaNombre;

    @FXML
    private TableColumn<Vehiculo, Double> columnaPrecioDia;

    @FXML
    private TableColumn<ServiciosAdicionales, Double> columnaPrecioServicio;

    @FXML
    private TableColumn<Vehiculo, String> columnaTipo;

    @FXML
    private TableColumn<CompraReservaAutomovil, Double> columnaTotal;

    @FXML
    private ComboBox<String> comboCiudadOrigen;

    @FXML
    private ComboBox<String> comboCiudades;

    @FXML
    private ComboBox<String> comboMetodo;

    @FXML
    private ComboBox<String> comboPaises;

    @FXML
    private DatePicker fechaEntrega;

    @FXML
    private TableView<Vehiculo> tablaAutos;

    @FXML
    private TableView<CompraReservaAutomovil> tablaReservas;

    @FXML
    private TableView<ServiciosAdicionales> tablaServicios;

    @FXML
    void reservar(ActionEvent event) {
        reservar();
    }

    private void reservar() {

        String ciudadOrigen = this.comboCiudades.getValue() != null ? this.comboCiudadOrigen.getValue() : "";
        String ciudadDestino = this.comboCiudadOrigen.getValue() != null ? this.comboCiudades.getValue() : "";
        String fechaEntregaString = this.fechaEntrega.getValue() != null ? this.fechaEntrega.getValue().toString() : "";
        String metodoPago = this.comboMetodo.getValue() != null ? this.comboMetodo.getValue() : "";

        String informacionVerificada = verificarInformacionReserva(ciudadOrigen, ciudadDestino, fechaEntregaString,
                metodoPago);

        if (!informacionVerificada.equalsIgnoreCase("Ok")) {
            mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS, informacionVerificada, AlertType.WARNING);
            return;
        }
        try {
            Double total = 0.0;
            double dias = LocalDate.now().until(fechaEntrega.getValue(), ChronoUnit.DAYS);
            List<ServiciosAdicionales> serviciosAdicionalesList = new ArrayList<>();
            if (!tablaServicios.getSelectionModel().getSelectedItems().isEmpty()) {
                for (ServiciosAdicionales serviciosAdicionales : tablaServicios.getSelectionModel()
                        .getSelectedItems()) {
                    total += serviciosAdicionales.getPrecioAdicional();
                    serviciosAdicionalesList.add(serviciosAdicionales);
                }
            }
            total += vehiculoSeleccionado.getPrecioDia() * dias;
            AlquilerVehiculo alquilerVehiculo = new AlquilerVehiculo(ciudadRepo.findByNombre(ciudadDestino),
                    ciudadRepo.findByNombre(ciudadOrigen), LocalDate.now(), fechaEntrega.getValue(), total);
            alquilerVehiculoRepo.save(alquilerVehiculo);
            CompraReservaAutomovil reserva = new CompraReservaAutomovil(clienteLogueado, LocalDate.now(),
                    metodoPagoRepo.findByNombre(metodoPago),
                    "El cliente con cedula: " + clienteLogueado.getCedula() + " reservo un auto con id: "
                            + vehiculoSeleccionado.getId() + " por un valor de: " + total,
                    alquilerVehiculo, vehiculoSeleccionado);
            if(!serviciosAdicionalesList.isEmpty()){
                reserva.setServiciosAdicionales(serviciosAdicionalesList);
            }
            compraReservaAutomovilRepo.save(reserva);
            vehiculoSeleccionado.setDisponible("No");
            vehiculoRepo.save(vehiculoSeleccionado);
            refrescarTablaReservas();
            limpiarInterfazReserva();
            mostrarMensaje("Gestion de reservas", "Informacion", "Reserva registrada con exito",
                    AlertType.INFORMATION);
        } catch (TransactionSystemException e) {
            Throwable t = e.getCause();
            while ((t != null) && !(t instanceof ConstraintViolationException)) {
                t = t.getCause();
            }
            if (t instanceof ConstraintViolationException) {
                ConstraintViolationException cve = (ConstraintViolationException) t;
                if (!cve.getConstraintViolations().isEmpty()) {
                    String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
                    mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS, errorMessage,
                            AlertType.WARNING);
                }
            }
        }
    }

    private void mostrarMensaje(String titulo, String header, String contenido, AlertType information) {
        Alert alert = new Alert(information);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private String verificarInformacionReserva(String ciudadOrigen, String ciudadDestino, String fechaEntregaString,
            String metodoPago) {
        String mensaje = "";

        if (ciudadOrigen.equalsIgnoreCase("")) {
            mensaje += "Por favor seleccione la ciudad de origen de la reserva\n";
        }
        if (ciudadDestino.equalsIgnoreCase("")) {
            mensaje += "Por favor seleccione la ciudad de destino de la reserva\n";
        }
        if (fechaEntregaString.equalsIgnoreCase("")) {
            mensaje += "Por favor ingrese la fecha en la que se entregara el automovil\n";
        }
        if (metodoPago.equalsIgnoreCase("")) {
            mensaje += "Por favor seleccione el metodo de pago de la reserva\n";
        }
        if (vehiculoSeleccionado == null) {
            mensaje += "Por favor seleccione el automovil que desea reservar\n";
        }
        if (comboPaises.getSelectionModel().getSelectedItem() == null) {
            mensaje += "Por favor seleccione el pais de la reserva\n";
        }
        if (fechaEntrega.getValue().isEqual(LocalDate.now())) {
            mensaje += "La fecha de entrega no puede ser igual a la fecha actual\n";
        }
        if (fechaEntrega.getValue().isBefore(LocalDate.now())) {
            mensaje += "La fecha de entrega no puede ser menor a la fecha actual\n";
        }
        if(vehiculoSeleccionado != null && vehiculoSeleccionado.getDisponible().equalsIgnoreCase("No")){
            mensaje += "El vehiculo seleccionado no esta disponible\n";
        }
        if (mensaje.equalsIgnoreCase("")) {
            mensaje = "OK";
        }
        return mensaje;
    }

    @FXML
    void volver(ActionEvent event) {
        sceneController.cambiarAMenuCliente(event, clienteLogueado);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clienteLogueado = sceneController.getClienteLogueado();
        tablaServicios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inicializarComboPais();
        inicializarComboCiudad();
        inicializarVehiculos();
        inicializarComboCiudadDestino();
        inicializarServicios();
        inicializarComboMetodo();
        inicializarReservas();
    }

    private void inicializarReservas() {
        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.ColumnaFechaEntrega.setCellValueFactory(cellData -> {
            CompraReservaAutomovil compraReservaAutomovil = cellData.getValue();
            AlquilerVehiculo alquilerVehiculo = compraReservaAutomovil.getAlquilerVehiculo();
            return new SimpleObjectProperty<>(alquilerVehiculo.getFechaEntrega());
        });
        this.columnaFechaReserva.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        this.columnaTotal.setCellValueFactory(cellData -> {
            CompraReservaAutomovil compraReservaVehiculo = cellData.getValue();
            AlquilerVehiculo alquilerVehiculo = compraReservaVehiculo.getAlquilerVehiculo();
            return new SimpleObjectProperty<>(alquilerVehiculo.getPrecio());
        });
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        refrescarTablaReservas();

        inicializarBuscadorReservas();

        tablaReservas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            reservaSeleccionada = newSelection;
            mostarInformacionReserva(reservaSeleccionada);
            if (reservaSeleccionada == null) {
                limpiarInterfazReserva();
            }
        });
    }

    private void inicializarBuscadorReservas() {
        // Crea el metodo
        FilteredList<CompraReservaAutomovil> listaReservasFiltradas = new FilteredList<>(listaReservasObservable,
                b -> true);
        // Crea el buscador
        buscadorReserva.textProperty()
                .addListener((obs, oldValue, newValue) -> listaReservasFiltradas.setPredicate(reserva -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (reserva.getId().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getFecha().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getAlquilerVehiculo().getFechaEntrega().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getDescripcion().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return reserva.getAlquilerVehiculo().getPrecio().toString().contains(lowerCaseFilter);
                    }
                }));

        SortedList<CompraReservaAutomovil> listaReservasSorteadas = new SortedList<>(listaReservasFiltradas);

        listaReservasSorteadas.comparatorProperty().bind(tablaReservas.comparatorProperty());

        tablaReservas.setItems(listaReservasSorteadas);
    }

    private void limpiarInterfazReserva() {
        comboPaises.getSelectionModel().clearSelection();
        comboCiudades.getSelectionModel().clearSelection();
        tablaAutos.getSelectionModel().clearSelection();
        comboCiudadOrigen.getSelectionModel().clearSelection();
        tablaServicios.getSelectionModel().clearSelection();
        fechaEntrega.setValue(null);
        comboMetodo.getSelectionModel().clearSelection();
    }

    private void mostarInformacionReserva(CompraReservaAutomovil reservaSeleccionada) {
        if (reservaSeleccionada != null) {

            comboPaises.getSelectionModel()
                    .select(reservaSeleccionada.getAlquilerVehiculo().getCiudadOrigen().getPais().getNombre());

            comboCiudades.getSelectionModel()
                    .select(reservaSeleccionada.getAlquilerVehiculo().getCiudadOrigen().getNombre());

            tablaAutos.getSelectionModel().select(reservaSeleccionada.getVehiculo());

            comboCiudadOrigen.getSelectionModel()
                    .select(reservaSeleccionada.getAlquilerVehiculo().getCiudadRecepcion().getNombre());

            for (ServiciosAdicionales servicio : serviciosAdicionalesRepo.findAllByCompraReservaAutomoviles_Id(reservaSeleccionada.getId())) {
                tablaServicios.getSelectionModel().select(servicio);
            }
        
            fechaEntrega.setValue(reservaSeleccionada.getAlquilerVehiculo().getFechaEntrega());
            comboMetodo.getSelectionModel().select(reservaSeleccionada.getMetodoPago().getNombre());

        }
    }

    private void refrescarTablaReservas() {
        listaReservasObservable.clear();
        listaReservasObservable.addAll(compraReservaAutomovilRepo.findAllByCliente_Cedula(clienteLogueado.getCedula()));
    }

    private void inicializarComboMetodo() {
        List<String> valoresMetodo = new ArrayList<>();
        for (MetodoPago metodoPago : metodoPagoRepo.findAll()) {
            if (metodoPago.getEstado().equalsIgnoreCase("Activo")) {
                valoresMetodo.add(metodoPago.getNombre());
            }
        }
        ObservableList<String> listaMetodos = FXCollections.observableArrayList(valoresMetodo);
        comboMetodo.getItems().clear();
        comboMetodo.setItems(listaMetodos);
    }

    private void inicializarServicios() {
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaDescripcionServicio.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        this.columnaPrecioServicio.setCellValueFactory(new PropertyValueFactory<>("precioAdicional"));
        refrescarTablaServicios();
        tablaServicios.getItems().clear();
        tablaServicios.setItems(listaServiciosObservable);

    }

    private void refrescarTablaServicios() {
        listaServiciosObservable.clear();
        listaServiciosObservable.addAll(serviciosAdicionalesRepo.findAll());
    }

    private void inicializarComboCiudadDestino() {
        comboPaises.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String nombre = newSelection;
                List<String> valoresCiudades = new ArrayList<>();
                List<Ciudad> ciudades = ciudadRepo.findByPais_Nombre(nombre);
                for (Ciudad ciudad : ciudades) {
                    valoresCiudades.add(ciudad.getNombre());
                }
                ObservableList<String> listaCiudades = FXCollections.observableArrayList(valoresCiudades);
                comboCiudadOrigen.getItems().clear();
                comboCiudadOrigen.setItems(listaCiudades);
            } else {
                comboCiudades.getItems().clear();
            }
        });
    }

    private void inicializarVehiculos() {
        this.columnaCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidadPersonas"));
        this.columnaEstadoAuto.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.columnaDisponible.setCellValueFactory(new PropertyValueFactory<>("disponible"));
        this.columnaMarca.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getNombreMarca().getNombre()));
        this.columnaPrecioDia.setCellValueFactory(new PropertyValueFactory<>("precioDia"));
        this.columnaTipo.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getTipoVehiculo().getTipo()));

        refrescarTablaVehiculos();

        FilteredList<Vehiculo> listaVehiculosFiltrados = new FilteredList<>(listaVehiculosObservable, b -> true);
        buscadorAuto.textProperty()
                .addListener((obs, oldValue, newValue) -> listaVehiculosFiltrados.setPredicate(vehiculo -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (vehiculo.getCapacidadPersonas().toString().contains(lowerCaseFilter)) {
                        return true;
                    } else if (vehiculo.getNombreMarca().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (vehiculo.getTipoVehiculo().getTipo().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (vehiculo.getEstado().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (vehiculo.getDisponible().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return vehiculo.getPrecioDia().toString().contains(lowerCaseFilter);
                    }
                }));

        SortedList<Vehiculo> listaVehiculosSorteados = new SortedList<>(listaVehiculosFiltrados);

        listaVehiculosSorteados.comparatorProperty().bind(tablaAutos.comparatorProperty());

        tablaAutos.setItems(listaVehiculosSorteados);

        tablaAutos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                vehiculoSeleccionado = newSelection;
            }
        });
    }

    private void refrescarTablaVehiculos() {
        listaVehiculosObservable.clear();
        comboCiudades.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                List<Vehiculo> vehiculos = vehiculoRepo.findAll();
                listaVehiculosObservable.addAll(vehiculos);
            } else {
                listaVehiculosObservable.clear();
            }
        });
    }

    private void inicializarComboCiudad() {
        comboPaises.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String nombre = newSelection;
                List<String> valoresCiudades = new ArrayList<>();
                List<Ciudad> ciudades = ciudadRepo.findByPais_Nombre(nombre);
                for (Ciudad ciudad : ciudades) {
                    valoresCiudades.add(ciudad.getNombre());
                }
                ObservableList<String> listaCiudades = FXCollections.observableArrayList(valoresCiudades);
                comboCiudades.getItems().clear();
                comboCiudades.setItems(listaCiudades);
            } else {
                comboCiudades.getItems().clear();
            }
        });
    }

    private void inicializarComboPais() {
        List<String> valoresPaises = new ArrayList<>();
        for (Pais pais : paisRepo.findAll()) {
            valoresPaises.add(pais.getNombre());
        }
        ObservableList<String> listaPaises = FXCollections.observableArrayList(valoresPaises);
        comboPaises.getItems().clear();
        comboPaises.setItems(listaPaises);
    }

}
