package fr.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class comptableController {

    
    @FXML
    private ListView<String> list_fiche_de_frais;

    @FXML
    void deconnexion(ActionEvent event) throws IOException {

        App.setRoot("connexion");
        connexionController.UtilisateurConnecter = 0;

    }

    @FXML
    void ListeFicheDeFrais(ActionEvent event) throws IOException {

    }
    
}
