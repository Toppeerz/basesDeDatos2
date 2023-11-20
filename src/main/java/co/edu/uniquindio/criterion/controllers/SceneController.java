package co.edu.uniquindio.criterion.controllers;

import java.io.IOException;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.criterion.CriterionApplication;
import co.edu.uniquindio.criterion.model.Agencia;
import co.edu.uniquindio.criterion.model.Ciudad;
import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.model.Pais;
import co.edu.uniquindio.criterion.model.Persona;
import co.edu.uniquindio.criterion.repositories.AgenciaRepo;
import co.edu.uniquindio.criterion.repositories.CiudadRepo;
import co.edu.uniquindio.criterion.repositories.ClienteRepo;
import co.edu.uniquindio.criterion.repositories.PaisRepo;
import co.edu.uniquindio.criterion.repositories.PersonaRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class SceneController {

    private final PersonaRepo personaRepo;

    private final ClienteRepo clienteRepo;

    private final PaisRepo paisRepo;

    private final CiudadRepo ciudadRepo;

    private final AgenciaRepo agenciaRepo;

    private Persona admin;

    private Cliente clienteLogueado;

    private static final Logger LOGGER = LoggerFactory.getLogger(SceneController.class);
    @Autowired
    public SceneController(PersonaRepo personaRepo, ClienteRepo clienteRepo, PaisRepo paisRepo, CiudadRepo ciudadRepo, AgenciaRepo agenciaRepo) {
        this.personaRepo = personaRepo;
        this.clienteRepo = clienteRepo;
        this.paisRepo = paisRepo;
        this.ciudadRepo = ciudadRepo;
        this.agenciaRepo = agenciaRepo;
        admin = clienteRepo.findById("1").orElse(null);
        if (admin == null) {
            admin = new Cliente("1", "1", "Juan", "1@gmail.com", "1", LocalDate.now());
            clienteRepo.save((Cliente) admin);

            Pais pais = new Pais("Colombia", "Espanol", "Peso Colombiano");
            pais = paisRepo.save(pais);
            Ciudad ciudad = new Ciudad(1L, "Armenia", 630_003L, pais, null, null, null, null, null);
            ciudad = ciudadRepo.save(ciudad);
            Agencia agencia = new Agencia(1L, "Agencia1", ciudad, "Descripcion1", "telefono1");
            agencia = agenciaRepo.save(agencia);

            LOGGER.info("SE HA INICIALIZADO LA BASE DE DATOS");
        }
    }


    public void cambiarALogin(ActionEvent event) {
        try {
            clienteLogueado = null;
            FXMLLoader loader = new FXMLLoader(CriterionApplication.class.getResource("/views/Login.fxml"));
            loader.setControllerFactory(CriterionApplication.context::getBean);
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void cambiarAMenuCliente(ActionEvent event, Cliente cliente) {
        try {
            clienteLogueado = cliente;
            FXMLLoader loader = new FXMLLoader(CriterionApplication.class.getResource("/views/MenuCliente.fxml"));
            loader.setControllerFactory(CriterionApplication.context::getBean);
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void cambiarAVistaReservasHoteles(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(CriterionApplication.class.getResource("/views/ReservaCliente.fxml"));
            loader.setControllerFactory(CriterionApplication.context::getBean);
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void cambiarAVistaPaquetes(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(CriterionApplication.class.getResource("/views/PaquetesCliente.fxml"));
            loader.setControllerFactory(CriterionApplication.context::getBean);
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void cambiarAVistaReservasAutomoviles(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(CriterionApplication.class.getResource("/views/ReservaAutomoviles.fxml"));
            loader.setControllerFactory(CriterionApplication.context::getBean);
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public void cambiarAVistaArticulos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(CriterionApplication.class.getResource("/views/Articulos.fxml"));
            loader.setControllerFactory(CriterionApplication.context::getBean);
            Parent root = (Parent) loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
