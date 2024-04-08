package fr.app;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class connexionController {

    public static int UtilisateurConnecter;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField erreurId;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private void buttonConnexion() throws SQLException, IOException {

        String email = emailInput.getText();
        String password = passwordInput.getText();

        // String sql = "SELECT u.id, p.posteName, fk_poste FROM utilisateur u INNER
        // JOIN poste p ON u.fk_poste = p.id"+
        // email+"' AND u.password = '"+password+"'";
        String sql = "SELECT u.id, fk_poste FROM utilisateur u WHERE u.email='" + email + "' AND u.password = SHA2('" + password + "', 512)";

        // je me connect a la Db
        Connect connect = new Connect();
        Connection con = DriverManager.getConnection(connect.dbURL, connect.nomUtilisateur, connect.mdp);

        // Je déclare puis je demande d'ajouté le resultat de la requêt sql
        Statement sqlRec = con.createStatement();
        ResultSet sqlreq = sqlRec.executeQuery(sql);

        // Je regarde dans un tableau si ce qui sera marqué dans le email et password et
        // bon
        while (sqlreq.next()) {

            UtilisateurConnecter = sqlreq.getInt("id");

            if (sqlreq.getInt("id") != 0) {

                String sql1 = "SELECT p.posteName FROM utilisateur u INNER JOIN poste p ON u.fk_poste = p.id WHERE u.id = '"
                        + sqlreq.getString("id") + "'";

                Statement sqlrec = con.createStatement();
                ResultSet sqlreq1 = sqlrec.executeQuery(sql1);

                while (sqlreq1.next()) {
                    if (sqlreq1.getString("posteName").equals("visiteur")) {

                        App.setRoot("application_visiteur");

                    } else if (sqlreq1.getString("posteName").equals("comptable")) {
                        // Page Comptable
                        App.setRoot("application_comptable");
                    }
                }

            }
        }
        erreurId.setStyle("-fx-text-fill: rgb(168, 26, 26);");
        erreurId.setText("Cette utilisateur n'existe pas ! . Réessayer avec de bon identifiant.");

        if (email.isEmpty() && password.isEmpty()) {
            erreurId.setStyle("-fx-text-fill: rgb(168, 26, 26);");
            erreurId.setText("Aucun Champ n'a a été remplis !");
        }
    }

    @FXML
    public static int getUser() {
        System.out.println(UtilisateurConnecter);
        return UtilisateurConnecter;
    }
}

// "SELECT email, password, p.posteName, fk_poste FROM utilisateur u INNER JOIN
// poste p ON u.fk_poste = p.id;";

// SELECT email, password FROM utilisateur WHERE id = ?;

// if (emailInput.getText().equals(sqlreq.getString("email"))
// && passwordInput.getText().equals(sqlreq.getString("password"))) {
// App.setRoot("app");

// } else {
// // si c'est pas le cas cette erreur s'affichera
// erreurId.setStyle("-fx-text-fill: rgb(168, 26, 26);");
// erreurId.setText("Cette utilisateur n'existe pas ! . Réessayer avec de bon
// identifiant.");

// if (emailInput.getText().isEmpty() && passwordInput.getText().isEmpty()) {
// erreurId.setStyle("-fx-text-fill: rgb(168, 26, 26);");
// erreurId.setText("Aucun Champ n'a a été remplis !");
// }
// }