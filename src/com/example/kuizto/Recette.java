package com.example.kuizto;


import java.util.*;

public class Recette {
	public String titre;
	public String type;
	public int tpsPreparation;
	private Vector<Ingredient> lesIngredients;
	private Vector<Appareil> lesAppareils;
	private Vector<Etape> lesEtapes;

	// Constructeur de la classe Recette
	public Recette(String unTitre, String unType, int unTpsPreparation) {
		titre=unTitre;
		type=unType;
		tpsPreparation=unTpsPreparation;
		lesIngredients = new Vector<Ingredient>();
		lesEtapes = new Vector<Etape>();
		lesAppareils = new Vector<Appareil>();
	}

	// Méthode permettant d'affilier un ingrédient à une recette 
	public void setIngredient(Ingredient ing) {
		lesIngredients.add(ing);
	}

	// Méthode pour récupérer l'ensemble des ingrédients de la recette
	public Vector<Ingredient> getLesIngredients() {
		return lesIngredients;
	}

	// Méthode permettant d'affilier un appareil à une recette 
	public void setAppareil(Appareil app) {
		lesAppareils.add(app);
	}

	// Méthode pour récupérer l'ensemble des appareils nécessaires à la recette
	public Vector<Appareil> getLesAppareils() {
		return lesAppareils;
	}



	// Getters de la classe Recette
	public String getTitre() {
		return titre;
	}


	public String getType() {
		return type;
	}


	public int getTpsPreparation() {
		return tpsPreparation;
	}

	public String getContenu() {

		String str = "";
		
		for (int i  = 0; i<lesEtapes.size(); i++){
			str = str + "\n" + lesEtapes.get(i).getContenu();
		}
		
		return str;
	}


	/**
	 * Création et remplissage de la table RELATION_REC_APP : table réunissant toutes les recettes, avec les appareils utilisés
	 */

	// Méthode servant à nommer la table de données

	public String getNomTable_RecApp() {
		return "RELATION_REC_APP";
	}


	// Méthode permettant de récupérer les champs de la table RELATION_REC_APP et leurs types 

	protected String[] getChampsTypes_RecApp() {
		String[] res = 
			{"TITRE","TEXT","TypeAppareil","TEXT","DureeUtilisation","INTEGER","PUISSANCE","INTEGER","TEMPERATUREouNIVEAU","INTEGER"};
		return res;
	}


	// Méthode permettant de récupérer les noms des champs de la table RELATION_REC_APP

	protected String[] getChamps_RecApp() {
		String[] res = {"TITRE", "TypeAppareil", "DureeUtilisation","PUISSANCE","TEMPERATUREouNIVEAU"};
		return res;
	}


	// Méthode pour créer la table RELATION_REC_APP

	public String sqlCreateTable_RecApp() {
		String res = "CREATE TABLE " + getNomTable_RecApp() + "(";
		boolean first = true ;
		int nb=0;
		for ( String s : getChampsTypes_RecApp()) {
			if (first)
				first = false;
			else if (nb % 2 == 0)
				res += ", ";
			else
				res += " ";
			res += s ;
			++nb ;
		}
		res += ");" ;
		System.out.println(res);
		return res ;
	}


	// Méthode pour insérer une entrée dans la table RELATION_REC_APP

	public String sqlInsert_RecApp() {
		String res = "";

		for (int i=0 ; i<getLesAppareils().size() ; i++) {
			Appareil app = getLesAppareils().get(i);
			for (int j=0 ; j<app.getInformations().size() ; j++) {
				res += "INSERT INTO " + getNomTable_RecApp() + "(";
				boolean b = true;
				for (String val : getChamps_RecApp()) {
					if (b)
						b = false;
					else
						res += ",";
					res += val ;
				}
				res += ") VALUES (";
				res += "'" + getTitre() + "','" + app.getType() + "'," + app.getInformations().get(j)[0] + "," + app.getInformations().get(j)[1] + "," + app.getInformations().get(j)[2] ;	
				res += ");"  ;
			}
		}
		return res ;
	}
}
