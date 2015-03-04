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

	public String getNomTable() {
		return "INGREDIENTS";
	}

	// M�thode permettant de r�cup�rer les noms et types des attributs de Recette sous forme d'un tableau de String
	protected String[] getChampsTypes() {
		String[] res = 
			{"ID","INTEGER PRIMARY KEY","NOM","TEXT","PrixUnitaire","REAL","UNITE","TEXT"};
		return res;
	}

	// M�thode permettant de r�cup�rer les noms des attributs de Recette sous forme d'un tableau de String (les diff�rents champs de la table)
	protected String[] getChamps() {
		String[] res = {"NOM","PrixUnitaire","UNITE"};
		return res;
	}


	// M�thode permettant de r�cup�rer les valeurs d'une entr�e de la table RECETTES

	protected String getValeurs() {
		String res;
		res = "'" + nom + "'," + prixUnitaire + ",'" + unite + "'";
		return res;		
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
