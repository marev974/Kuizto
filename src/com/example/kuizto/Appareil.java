package com.example.kuizto;

import java.util.Vector;

public abstract class Appareil {

	private Vector<int []> informations;
	protected String typeAppareil;

	// Constructeur de la classe Appareil, qui contient un vecteur de triplets d'entiers  : 
	// durée d'utilisation, puissance, grandeur supplémentaire (nécessaire pour le four et la plaque)
	public Appareil(Vector<int[]> durPui) { 
		informations = durPui;
		typeAppareil = null;
	}

	// Méthode permettant d'ajouter un triplet d'entiers au vecteur informations
	public void ajoutInformation(int [] info) {
		informations.add(info);
	}

	// Méthode permettant d'obtenir le vecteur informations
	public Vector<int[]> getInformations() {
		return informations;
	}

	// Méthode permettant de récupérer le type d'appareil 
	protected String getType() {
		return typeAppareil;
	}
	
	//Méthode permettant de calculer l'énergie totale, en utilisant le vecteur d'informations :
		//On utilise la formule E = Puissance * Temps * 60, pour savoir l'énergie en Joule
		//On divise le résultat trouvé par 1000 pour l'afficher en kJ
	public int getEnergie() {
		int energie = 0;

		for (int i=0 ; i<informations.size(); i++) {
			energie += informations.get(i)[0]*informations.get(i)[1]*60;
		}
		return (int) (energie/1000);
	}
	

}
