package co.edu.uniquindio.criterion.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;

import co.edu.uniquindio.criterion.model.CancelacionReserva;
import co.edu.uniquindio.criterion.model.Ciudad;
import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.model.CompraReserva;
import co.edu.uniquindio.criterion.model.Habitacion;
import co.edu.uniquindio.criterion.model.Hotel;
import co.edu.uniquindio.criterion.model.Instalacion;
import co.edu.uniquindio.criterion.model.MetodoPago;
import co.edu.uniquindio.criterion.model.Pais;
import co.edu.uniquindio.criterion.model.PoliticaCancelacionHotel;
import co.edu.uniquindio.criterion.model.RegimenHospedaje;
import co.edu.uniquindio.criterion.model.ReservaHotel;
import co.edu.uniquindio.criterion.repositories.CancelacionReservaRepo;
import co.edu.uniquindio.criterion.repositories.CiudadRepo;
import co.edu.uniquindio.criterion.repositories.CompraReservaRepo;
import co.edu.uniquindio.criterion.repositories.HabitacionRepo;
import co.edu.uniquindio.criterion.repositories.HotelRepo;
import co.edu.uniquindio.criterion.repositories.InstalacionRepo;
import co.edu.uniquindio.criterion.repositories.MetodoPagoRepo;
import co.edu.uniquindio.criterion.repositories.PaisRepo;
import co.edu.uniquindio.criterion.repositories.PoliticaCancelacionHotelRepo;
import co.edu.uniquindio.criterion.repositories.RegimenHospedajeRepo;
import co.edu.uniquindio.criterion.repositories.RegimenHotelRepo;
import co.edu.uniquindio.criterion.repositories.ReservaHotelRepo;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class ReservaClienteController implements Initializable {

    @Autowired
    private SceneController sceneController;
    private ObservableList<Hotel> listaHotelObservable = FXCollections.observableArrayList();
    private ObservableList<Habitacion> listaHabitacionObservable = FXCollections.observableArrayList();
    private ObservableList<ReservaHotel> listaReservasObservable = FXCollections.observableArrayList();
    private Cliente clienteLogueado;
    private Hotel hotelSeleccionado;
    private ReservaHotel reservaSeleccionada;
    private static final String VALIDACION_DATOS = "Validacion de datos";
    @Autowired
    private PaisRepo paisRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private HotelRepo hotelRepo;
    @Autowired
    private HabitacionRepo habitacionRepo;
    @Autowired
    private ReservaHotelRepo reservaHotelRepo;
    @Autowired
    private InstalacionRepo instalacionRepo;
    @Autowired
    private MetodoPagoRepo metodoPagoRepo;
    @Autowired
    private RegimenHospedajeRepo regimenHospedajeRepo;
    @Autowired
    private RegimenHotelRepo regimenHotelRepo;
    @Autowired
    private CompraReservaRepo compraReservaRepo;
    @Autowired
    private PoliticaCancelacionHotelRepo politicaCancelacionHotelRepo;
    @Autowired
    private CancelacionReservaRepo cancelacionReservaRepo;

    @FXML
    private TableColumn<ReservaHotel, LocalDate> ColumnaFechaFin;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField buscadorHabitacion;

    @FXML
    private TextField buscadorHotel;

    @FXML
    private TextField buscadorReserva;

    @FXML
    private TableColumn<ReservaHotel, Double> columnaCosto;

    @FXML
    private TableColumn<ReservaHotel, String> columnaDescripcion;

    @FXML
    private TableColumn<Habitacion, String> columnaDescripcionHabitacion;

    @FXML
    private TableColumn<Hotel, String> columnaDireccion;

    @FXML
    private TableColumn<ReservaHotel, String> columnaEstado;

    @FXML
    private TableColumn<ReservaHotel, LocalDate> columnaFechaInicio;

    @FXML
    private TableColumn<ReservaHotel, LocalDate> columnaFechaReserva;

    @FXML
    private TableColumn<ReservaHotel, Long> columnaId;

    @FXML
    private TableColumn<Hotel, String> columnaInstalacion;

    @FXML
    private TableColumn<ReservaHotel, String> columnaMetodoPago;

    @FXML
    private TableColumn<Habitacion, String> columnaNivel;

    @FXML
    private TableColumn<Hotel, String> columnaNombre;

    @FXML
    private TableColumn<Habitacion, Double> columnaPrecioNoche;

    @FXML
    private TableColumn<Habitacion, String> columnaTipoHabitacion;

    @FXML
    private TableColumn<Hotel, String> columnaTipoHotel;

    @FXML
    private TableColumn<Hotel, Long> columnaValoracion;

    @FXML
    private ComboBox<String> comboCiudades;

    @FXML
    private ComboBox<String> comboMetodoPago;

    @FXML
    private ComboBox<String> comboPaises;

    @FXML
    private ComboBox<String> comboRegimen;

    @FXML
    private DatePicker fechaFin;

    @FXML
    private DatePicker fechaInicio;

    @FXML
    private TableView<Habitacion> tablaHabitaciones;

    @FXML
    private TableView<Hotel> tablaHoteles;

    @FXML
    private TableView<ReservaHotel> tablaReservas;

    @FXML
    void actualizar(ActionEvent event) {
        actualizarReserva();
    }

    public void actualizarReserva() {
        String fechaInicioString = this.fechaInicio.getValue() != null ? this.fechaInicio.getValue().toString() : "";
        String fechaFinString = this.fechaFin.getValue() != null ? this.fechaFin.getValue().toString() : "";
        String metodoPago = this.comboMetodoPago.getValue() != null ? this.comboMetodoPago.getValue() : "";
        String regimen = this.comboRegimen.getValue() != null ? this.comboRegimen.getValue() : "";

        if (reservaSeleccionada == null) {
            mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS, "No ha seleccionado una reserva para actualizar",
                    AlertType.WARNING);
            return;
        }

        String informacionVerificada = verificarInformacionReserva(fechaInicioString, fechaFinString, metodoPago,
                regimen);

        if (!informacionVerificada.equalsIgnoreCase("Ok")) {
            mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS, informacionVerificada, AlertType.WARNING);
            return;
        }
        try {
            Double total = 0.0;
            double dias = fechaInicio.getValue().until(fechaFin.getValue(), ChronoUnit.DAYS);
            for (Habitacion habitacion : tablaHabitaciones.getSelectionModel().getSelectedItems()) {
                total += habitacion.getPrecio() * dias;
            }

            reservaSeleccionada.setHotel(hotelSeleccionado);
            reservaSeleccionada.setCheckin(fechaInicio.getValue());
            reservaSeleccionada.setCheckout(fechaFin.getValue());
            reservaSeleccionada.setMetodoPago(metodoPagoRepo.findByNombre(metodoPago));
            reservaSeleccionada.setCostoTotal(total);
            reservaSeleccionada.setDescripcion(
                    "El cliente con la cedula: " + clienteLogueado.getCedula() + " registro una reserva en el hotel "
                            + hotelSeleccionado.getNombre() + " por un valor de " + total);
            reservaSeleccionada.setRegimenHotel(
                    regimenHotelRepo.findByHotel_IdAndRegimen_NombreTipo(hotelSeleccionado.getId(), regimen));
            reservaHotelRepo.save(reservaSeleccionada);

            for (Habitacion habitacion : habitacionRepo.findAllByCompraReservas_ReservaHotel_Id(reservaSeleccionada.getId())) {
                habitacion.setEstado("Disponible");
                habitacionRepo.save(habitacion);
            }
            compraReservaRepo.deleteAllByReservaHotel_Id(reservaSeleccionada.getId());
            for (Habitacion habitacion : tablaHabitaciones.getSelectionModel().getSelectedItems()) {
                CompraReserva compraReserva = new CompraReserva(habitacion, reservaSeleccionada, LocalDate.now());
                compraReservaRepo.save(compraReserva);
                habitacion.setEstado("No disponible");
                habitacionRepo.save(habitacion);
            }
            refrescarTablaReservas();
            limpiarInterfazReserva();
            mostrarMensaje("Gestion de reservas", "Informacion", "Reserva actualizada con exito",
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

    @FXML
    void cancelar(ActionEvent event) {
        cancelarReserva();
    }

    private void cancelarReserva() {
        if (reservaSeleccionada != null) {
            if (!reservaSeleccionada.getEstado().equals("Cancelada")) {
                Long diasRetrasados = reservaSeleccionada.getFechaReserva().until(fechaInicio.getValue(),
                        ChronoUnit.DAYS);
                List<PoliticaCancelacionHotel> politicas = politicaCancelacionHotelRepo
                        .findClosestToDayMax(diasRetrasados);
                PoliticaCancelacionHotel politicaCancelacionHotel = politicas.isEmpty() ? null : politicas.get(0);
                if (politicaCancelacionHotel == null) {
                    mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS,
                            "No se encontro una politica de cancelacion para la reserva seleccionada",
                            AlertType.WARNING);
                    return;
                }
                Double costo = Double.valueOf(politicaCancelacionHotel.getCostoDiaRetraso() * (double) diasRetrasados);
                String motivo ="";
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Input Dialog");
                dialog.setHeaderText("Ingrese el motivo de la cancelación");
                dialog.setContentText("Motivo:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    motivo = result.get();
                }
                CancelacionReserva cancelacionReserva = new CancelacionReserva(LocalDate.now(), costo, motivo,
                        reservaSeleccionada, politicaCancelacionHotel);
                cancelacionReservaRepo.save(cancelacionReserva);
                reservaSeleccionada.setEstado("Cancelada");
                reservaSeleccionada.setCostoMora(costo);
                reservaHotelRepo.save(reservaSeleccionada);
                refrescarTablaReservas();
                reservaSeleccionada = null;
                mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS,
                        "Reserva cancelada con exito \n" + politicaCancelacionHotel.getDescripcion() +"\n El costo de la cancelacion es de: "+costo,
                        AlertType.INFORMATION);
            } else {
                mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS,
                        "No se puede cancelar una reserva que ya fue cancelada",
                        AlertType.WARNING);
            }
        } else {
            mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS, "No ha seleccionado una reserva para cancelar",
                    AlertType.WARNING);
        }
    }

    @FXML
    void crearReserva(ActionEvent event) {
        crearReserva();
    }

    private void crearReserva() {
        String fechaInicioString = this.fechaInicio.getValue() != null ? this.fechaInicio.getValue().toString() : "";
        String fechaFinString = this.fechaFin.getValue() != null ? this.fechaFin.getValue().toString() : "";
        String metodoPago = this.comboMetodoPago.getValue() != null ? this.comboMetodoPago.getValue() : "";
        String regimen = this.comboRegimen.getValue() != null ? this.comboRegimen.getValue() : "";

        String informacionVerificada = verificarInformacionReserva(fechaInicioString, fechaFinString, metodoPago,
                regimen);

        if (!informacionVerificada.equalsIgnoreCase("Ok")) {
            mostrarMensaje(VALIDACION_DATOS, VALIDACION_DATOS, informacionVerificada, AlertType.WARNING);
            return;
        }
        try {
            Double total = 0.0;
            double dias = fechaInicio.getValue().until(fechaFin.getValue(), ChronoUnit.DAYS);
            for (Habitacion habitacion : tablaHabitaciones.getSelectionModel().getSelectedItems()) {
                total += habitacion.getPrecio() * dias;
            }
            ReservaHotel reserva = new ReservaHotel(clienteLogueado, hotelSeleccionado, fechaInicio.getValue(), 0.0,
                    LocalDate.now(), fechaFin.getValue(), "Activa", total, metodoPagoRepo.findByNombre(metodoPago),
                    "El cliente con la cedula: " + clienteLogueado.getCedula() + " registro una reserva en el hotel "
                            + hotelSeleccionado.getNombre() + " por un valor de " + total,
                    regimenHotelRepo.findByHotel_IdAndRegimen_NombreTipo(hotelSeleccionado.getId(), regimen));
            reservaHotelRepo.save(reserva);

            for (Habitacion habitacion : tablaHabitaciones.getSelectionModel().getSelectedItems()) {
                CompraReserva compraReserva = new CompraReserva(habitacion, reserva, LocalDate.now());
                compraReservaRepo.save(compraReserva);
                habitacion.setEstado("No disponible");
                habitacionRepo.save(habitacion);
            }
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

    private String verificarInformacionReserva(String fechaInicioString, String fechaFinString, String metodoPago,
            String regimen) {
        String mensaje = "";

        if (fechaInicioString.equalsIgnoreCase("")) {
            mensaje += "Por favor ingrese la fecha en la que iniciara la reserva\n";
        }
        if (fechaFinString.equalsIgnoreCase("")) {
            mensaje += "Por favor ingrese la fecha en la que finalizara la reserva\n";
        }
        if (metodoPago.equalsIgnoreCase("")) {
            mensaje += "Por favor seleccione el metodo de pago de la reserva\n";
        }
        if (regimen.equalsIgnoreCase("")) {
            mensaje += "Por favor seleccione el regimen de hospedaje de la reserva\n";
        }
        if (hotelSeleccionado == null) {
            mensaje += "Por favor seleccione el hotel de la reserva\n";
        }
        if (tablaHabitaciones.getSelectionModel().getSelectedItems().isEmpty()) {
            mensaje += "Por favor seleccione al menos una habitacion para reservar\n";
        }
        if (comboCiudades.getSelectionModel().getSelectedItem() == null) {
            mensaje += "Por favor seleccione la ciudad de la reserva\n";
        }
        if (comboPaises.getSelectionModel().getSelectedItem() == null) {
            mensaje += "Por favor seleccione el pais de la reserva\n";
        }
        if (fechaInicio.getValue().until(fechaFin.getValue(), ChronoUnit.DAYS) == 0) {
            mensaje += "La fecha de inicio y la fecha de fin no pueden ser iguales\n";
        }
        if (fechaInicio.getValue().until(fechaFin.getValue(), ChronoUnit.DAYS) < 0) {
            mensaje += "La fecha de inicio no puede ser mayor a la fecha de fin\n";
        }
        if (Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), fechaInicio.getValue())) > 470) {
            mensaje += "La fecha de inicio de la reserva no puede ser mayor a 470 dias de la fecha actual\n";
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
        tablaHabitaciones.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        inicializarComboPais();
        inicializarComboCiudad();
        inicializarHoteles();
        inicializarHabitaciones();
        inicializarComboMetodo();
        inicializarComboRegimen();
        inicializarReservas();
    }

    private void inicializarReservas() {
        this.columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        this.columnaFechaInicio.setCellValueFactory(new PropertyValueFactory<>("checkin"));
        this.ColumnaFechaFin.setCellValueFactory(new PropertyValueFactory<>("checkout"));
        this.columnaFechaReserva.setCellValueFactory(new PropertyValueFactory<>("fechaReserva"));
        this.columnaCosto.setCellValueFactory(new PropertyValueFactory<>("costoTotal"));
        this.columnaMetodoPago
                .setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getMetodoPago().getNombre()));
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
        FilteredList<ReservaHotel> listaReservasFiltradas = new FilteredList<>(listaReservasObservable, b -> true);
        buscadorReserva.textProperty()
                .addListener((obs, oldValue, newValue) -> listaReservasFiltradas.setPredicate(reserva -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (String.valueOf(reserva.getId()).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getEstado().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getMetodoPago().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getCostoTotal().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getCheckin().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (reserva.getCheckout().toString().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return (reserva.getFechaReserva().toString().toLowerCase().contains(lowerCaseFilter));
                    }
                }));

        SortedList<ReservaHotel> listaReservasSorteadas = new SortedList<>(listaReservasFiltradas);

        listaReservasSorteadas.comparatorProperty().bind(tablaReservas.comparatorProperty());

        tablaReservas.setItems(listaReservasSorteadas);
    }

    private void limpiarInterfazReserva() {
        fechaInicio.setValue(null);
        fechaFin.setValue(null);
        comboMetodoPago.setValue(null);
        comboRegimen.setValue(null);
        tablaHabitaciones.getSelectionModel().clearSelection();
        tablaHoteles.getSelectionModel().clearSelection();
        comboCiudades.getSelectionModel().clearSelection();
        comboPaises.getSelectionModel().clearSelection();
    }

    private void mostarInformacionReserva(ReservaHotel reservaSeleccionada) {
        if (reservaSeleccionada != null) {

            comboPaises.getSelectionModel().select(reservaSeleccionada.getHotel().getCiudad().getPais().getNombre());

            comboCiudades.getSelectionModel().select(reservaSeleccionada.getHotel().getCiudad().getNombre());

            tablaHoteles.getSelectionModel().select(reservaSeleccionada.getHotel());

            List<Habitacion> habitacionesSeleccionadas = habitacionRepo
                    .findAllByCompraReservas_ReservaHotel_Id(reservaSeleccionada.getId());
            for (Habitacion habitacion : habitacionesSeleccionadas) {
                tablaHabitaciones.getSelectionModel().select(habitacion);
            }
            comboRegimen.setValue(reservaSeleccionada.getRegimenHotel().getRegimen().getNombreTipo());
            fechaInicio.setValue(reservaSeleccionada.getCheckin());
            fechaFin.setValue(reservaSeleccionada.getCheckout());
            comboMetodoPago.setValue(reservaSeleccionada.getMetodoPago().getNombre());

        }
    }

    private void refrescarTablaReservas() {
        listaReservasObservable.clear();
        listaReservasObservable.addAll(reservaHotelRepo.findAllByCliente_Cedula(clienteLogueado.getCedula()));
    }

    private void inicializarComboRegimen() {
        tablaHoteles.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    hotelSeleccionado = newSelection;
                    if (hotelSeleccionado != null) {
                        List<String> valoresRegimen = new ArrayList<>();
                        for (RegimenHospedaje regimenHospedaje : regimenHospedajeRepo
                                .findAllByRegimenHoteles_Hotel_Id(hotelSeleccionado.getId())) {
                            valoresRegimen.add(regimenHospedaje.getNombreTipo());
                        }
                        ObservableList<String> listaRegimenes = FXCollections.observableArrayList(valoresRegimen);
                        comboRegimen.getItems().clear();
                        comboRegimen.setItems(listaRegimenes);
                    } else {
                        comboRegimen.getItems().clear();
                    }
                });
    }

    private void inicializarComboMetodo() {
        List<String> valoresMetodo = new ArrayList<>();
        for (MetodoPago metodoPago : metodoPagoRepo.findAll()) {
            if (metodoPago.getEstado().equalsIgnoreCase("Activo")) {
                valoresMetodo.add(metodoPago.getNombre());
            }
        }
        ObservableList<String> listaMetodos = FXCollections.observableArrayList(valoresMetodo);
        comboMetodoPago.getItems().clear();
        comboMetodoPago.setItems(listaMetodos);
    }

    private void inicializarHabitaciones() {
        this.columnaNivel.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getNivel().getNombre()));
        this.columnaTipoHabitacion
                .setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getTipoHabitacion().getTipo()));
        this.columnaPrecioNoche.setCellValueFactory(new PropertyValueFactory<>("precio"));
        this.columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        refrescarTablaHabitaciones();

        FilteredList<Habitacion> listaHabitacionesFiltradas = new FilteredList<>(listaHabitacionObservable, b -> true);
        buscadorHabitacion.textProperty()
                .addListener((obs, oldValue, newValue) -> listaHabitacionesFiltradas.setPredicate(habitacion -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (habitacion.getDescripcion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (habitacion.getNivel().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (habitacion.getTipoHabitacion().getTipo().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return habitacion.getPrecio().toString().contains(lowerCaseFilter);
                    }
                }));

        SortedList<Habitacion> listaHabitacionesSorteadas = new SortedList<>(listaHabitacionesFiltradas);

        listaHabitacionesSorteadas.comparatorProperty().bind(tablaHabitaciones.comparatorProperty());

        tablaHabitaciones.setItems(listaHabitacionesSorteadas);

    }

    private void refrescarTablaHabitaciones() {
        listaHabitacionObservable.clear();
        tablaHoteles.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSelection, newSelection) -> {
                    hotelSeleccionado = newSelection;
                    if (hotelSeleccionado != null) {
                        listaHabitacionObservable.clear();
                        List<Habitacion> habitaciones = habitacionRepo.findAllByHotel_Id(hotelSeleccionado.getId());
                        for (Habitacion habitacion : habitaciones) {
                                listaHabitacionObservable.add(habitacion);
                        }
                    } else {
                        listaHabitacionObservable.clear();
                    }
                });
    }

    private void inicializarHoteles() {
        this.columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        this.columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.columnaValoracion.setCellValueFactory(new PropertyValueFactory<>("valoracion"));
        this.columnaTipoHotel.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getTipoHotel().getTipo()));
        this.columnaInstalacion.setCellValueFactory(a -> {
            List<Instalacion> instalaciones = instalacionRepo.findAllByHoteles_Id(a.getValue().getId());
            String instalacionesNombres = instalaciones.stream()
                    .map(Instalacion::getNombre)
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(instalacionesNombres);
        });

        refrescarTablaHoteles();

        FilteredList<Hotel> listaHotelesFiltrados = new FilteredList<>(listaHotelObservable, b -> true);
        buscadorHotel.textProperty()
                .addListener((obs, oldValue, newValue) -> listaHotelesFiltrados.setPredicate(hotel -> {

                    if (newValue == null || newValue.isEmpty() || newValue.isBlank()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (hotel.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (hotel.getDireccion().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (hotel.getTipoHotel().getTipo().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (instalacionRepo.findAllByHoteles_Id(hotel.getId()).stream()
                            .map(Instalacion::getNombre)
                            .collect(Collectors.joining(", ")).toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else {
                        return hotel.getValoracion().toString().contains(lowerCaseFilter);
                    }
                }));

        SortedList<Hotel> listaHotelesSorteados = new SortedList<>(listaHotelesFiltrados);

        listaHotelesSorteados.comparatorProperty().bind(tablaHoteles.comparatorProperty());

        tablaHoteles.setItems(listaHotelesSorteados);

    }

    private void refrescarTablaHoteles() {
        listaHotelObservable.clear();
        comboCiudades.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                String nombre = newSelection;
                List<Hotel> hoteles = hotelRepo.findByCiudad_Nombre(nombre);
                listaHotelObservable.addAll(hoteles);
            } else {
                listaHotelObservable.clear();
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
