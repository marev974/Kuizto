package com.example.kuizto;

public class Ingredient {
	private String nom;
	private double quantite;
	private double prixUnitaire;
	private String unite;

	//Constructeur de la classe Ingredient
	public Ingredient(String ing, double qte, double prixU, String unit) {
		nom = ing;
		quantite = qte;
		prixUnitaire = prixU;
		unite = unit;
	}




	public String getNom() {
		return nom;
	}


	public double getQuantite() {
		return quantite;
	}


	public double getPrixUnitaire() {
		return prixUnitaire;
	}


	public String getUnite() {
		return unite;
	}
}
