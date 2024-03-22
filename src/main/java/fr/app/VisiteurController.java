package fr.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VisiteurController {

    @FXML
    private TextField Dateinput;
    @FXML
    private Button Envoyer;

    @FXML
    private Label KilométrageTotal;

    @FXML
    private TextField Kilometrageinput;

    @FXML
    private TextField Libelléinput;

    @FXML
    private TextField Matriculeinput;

    @FXML
    private TextField Moisinput;

    @FXML
    private TextField Montantinput;

    @FXML
    private TextField Nuitinput;

    @FXML
    private TextField Repasmidiinput;

    @FXML
    private Label NuitTotal;

    @FXML
    private Label RepasTotal;

    @FXML
    private Button logout;

    @FXML
    private TextField user_poste;

    @FXML
    private TextField user_session;

    @FXML
    void EnvoyerFiche(ActionEvent event) {
        System.out.println("bouton OK clic");
        String quantitéNuités = Nuitinput.getText();
        int resultat = Integer.parseInt(quantitéNuités);
        int resultat_total = resultat * 80;
        String resultatotalString = String.valueOf(resultat_total);
        NuitTotal.setText(resultatotalString);
        System.out.println(resultat);

        System.out.println("bouton OK clic");
        String quantitérepas = Repasmidiinput.getText();
        int resultatRepasTotal = Integer.parseInt(quantitérepas);
        int RepasTotals = resultatRepasTotal * 29;
        String RepasTotalotalString = String.valueOf(RepasTotals);
        RepasTotal.setText(RepasTotalotalString);
        System.out.println(resultat);

        System.out.println("bouton OK clic");
        String quantitékilometrage = Kilometrageinput.getText();
        int resultatKilometrageTotal = Integer.parseInt(quantitékilometrage);
        int KilometrageTotals = resultatKilometrageTotal * 10;
        String KilometrageTotalString = String.valueOf(KilometrageTotals);
        KilométrageTotal.setText(KilometrageTotalString);
        System.out.println(resultat);

        System.out.println("bouton OK clic");
        String Date_input = Dateinput.getText();
        String Libelle_input = Libelléinput.getText();
        String Montant_input = Montantinput.getText();
        System.out.println("Date : " + Date_input +
                "\nlibelle: " + Libelle_input + "\nMontant: " + Montant_input);

    }

    @FXML
    void deconnexion(ActionEvent event) {

    }

    @FXML
    void userSession(ActionEvent event) {

    }

}
