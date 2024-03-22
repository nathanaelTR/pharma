package fr.app;

import java.util.Scanner;

public class FicheDeFrais {

    private int quantite;
    private double total;
    private int quantiteRepas;
    private double totalRepas;
    private int quantitekilometres;
    private double totalkilometres;
    private int typeVehicule;
    private double coutTotal;
    final double TARIF_4CV_DIESEL = 0.52;
    final double TARIF_5_6CV_DIESEL = 0.58;
    final double TARIF_4CV_ESSENCE = 0.62;
    final double TARIF_5_6CV_ESSENCE = 0.67;

    public FicheDeFrais(int quantite, int quantiteRepas, int quantitekilometres, int typeVehicule) {
        this.quantite = quantite;
        this.total = quantite * 80;
        this.quantiteRepas = quantiteRepas;
        this.totalRepas = quantiteRepas * 29;
        this.quantitekilometres = quantitekilometres;
        this.typeVehicule = typeVehicule;
        calculerCoutKilometres();
    }

    private void calculerCoutKilometres() {
        switch (typeVehicule) {
            case 1:
                coutTotal = quantitekilometres * TARIF_4CV_DIESEL;
                break;
            case 2:
                coutTotal = quantitekilometres * TARIF_5_6CV_DIESEL;
                break;
            case 3:
                coutTotal = quantitekilometres * TARIF_4CV_ESSENCE;
                break;
            case 4:
                coutTotal = quantitekilometres * TARIF_5_6CV_ESSENCE;
                break;
            default:
                System.out.println("Type de véhicule non reconnu.");
                coutTotal = 0;
                break;
        }
        totalkilometres = coutTotal;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getTotal() {
        return total;
    }

    public int getQuantiteRepas() {
        return quantiteRepas;
    }

    public double getTotalRepas() {
        return totalRepas;
    }

    public int getQuantitekilometres() {
        return quantitekilometres;
    }

    public double getTotalkilometres() {
        return totalkilometres;
    }

    public String toString() {
        return "Nombre de nuitées : " + quantite + "\n" +
                "Montant total des nuitées : " + total + " €" + "\n" +
                "Nombre de repas midi : " + quantiteRepas + "\n" +
                "Montant total des repas midi : " + totalRepas + " €" + "\n" +
                "Nombre de kilomètres : " + quantitekilometres + " €" + "\n" +
                "Montant total de kilomètres : " + totalkilometres + " €";

    }

    public void modifierFrais() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez-vous modifier les frais ? (Oui/Non) : ");
        String reponse = sc.nextLine();
    }

}
