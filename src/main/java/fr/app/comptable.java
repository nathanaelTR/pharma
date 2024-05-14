package fr.app;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class comptable {

    @FXML
    private Button logout;

    @FXML
    private TextField month_number_search;

    @FXML
    private TextField user_search;

    @FXML
    void deconnexion(ActionEvent event) throws IOException {

        App.setRoot("connexion");
    }

    @FXML
    public void validiteFicheDeFrais() throws SQLException {

        // je récupére le nom, le prenom, lemail, le fk_poste, le postename puis je mais une conditions pour dire que je veux 
        // récupérer seulement 
        String sql = "SELECT prenom, nom, email, fk_poste, p.posteName FROM utilisateur INNER JOIN poste p ON fk_poste = 2";

        Connect connect = new Connect();
        Connection con = DriverManager.getConnection(connect.dbURL, connect.nomUtilisateur, connect.mdp);

        Statement sqlStat = con.createStatement();
        ResultSet sqlRec = sqlStat.executeQuery(sql);

        month_number_search.textProperty().addListener((obj, old, newVal) -> {
            if (!newVal.matches("\\d*")) {

                month_number_search.setText(old);
            }
        });
    }
}
