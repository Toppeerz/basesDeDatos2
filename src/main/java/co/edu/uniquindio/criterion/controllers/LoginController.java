package co.edu.uniquindio.criterion.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.uniquindio.criterion.model.Cliente;
import co.edu.uniquindio.criterion.repositories.ClienteRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

@Component
public class LoginController {

    @Autowired
    private ClienteRepo clienteRepo;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private TextField txtCedula;

    @FXML
    private Label txtError;

    @Autowired
    private SceneController sceneController;

    @FXML
    void iniciarSesion(ActionEvent event) {
        String cedula = txtCedula.getText();
       
            
		Cliente cliente = clienteRepo.findById(cedula).orElse(null);
        if(cliente != null){
		abrirMenuCliente(event, cliente);
        }else{
        txtError.setText("No hay existe un usuario con esa cedula");
    }
    }

    private void abrirMenuCliente(ActionEvent event, Cliente cliente) {
        sceneController.cambiarAMenuCliente(event, cliente);
    }


}
