package com.example.kuizto;

public class Etape {

	private String contenu;
	private Recette laRecette;

	// Constructeur de la classe Etape
	public Etape(String c, Recette r) {
		contenu = c;
		laRecette = r;

	}

	// Méthode qui permet d'obtenir le contenu de l'étape
	public String getContenu() {
		return contenu;

	}
}
