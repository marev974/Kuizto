package com.example.kuizto;


import java.util.*;

public class Recette {
	public String titre;
	public String type;
	public int tpsPreparation;
	private Vector<Ingredient> lesIngredients;
	public String contenu;

	// Constructeur de la classe Recette
	public Recette(String unTitre, String unType, int unTpsPreparation) {
		titre=unTitre;
		type=unType;
		tpsPreparation=unTpsPreparation;
		lesIngredients = new Vector<Ingredient>();
	}

	// M�thode permettant d'affilier un ingr�dient � une recette 
	public void setIngredient(Ingredient ing) {
		lesIngredients.add(ing);
	}
	
	// M�thode permettant d'ajouter le contenu d'une recette
	public void setContenu(String unContenu) {
		contenu = unContenu;
	}

	// M�thode pour r�cup�rer l'ensemble des ingr�dients de la recette
	public Vector<Ingredient> getLesIngredients() {
		return lesIngredients;
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

    public String getTpsStringPreparation() { return Integer.toString(tpsPreparation);}

	public String getContenu() {
return contenu;
	}

}
