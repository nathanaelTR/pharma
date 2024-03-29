package fr.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class comptableController {

    @FXML
    void deconnexion(ActionEvent event) throws IOException {

        
        App.setRoot("connexion");
        connexionController.UtilisateurConnecter = 0;

    }
}
