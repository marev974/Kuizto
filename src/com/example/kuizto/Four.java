package com.example.kuizto;

import java.util.Vector;

public class Four extends Appareil {

	//Constructeur de la classe Four
	public Four() {
		super(new Vector<int[]>());
		typeAppareil = "Four";
	}

	//Méthode qui permet d'ajouter un nouveau triplet au vecteur Informations
	//On prend en argument un triplet d'entiers : durée, grandeur nulle, température
	//On calcule la seconde composante du triplet (qui représente la puissance) grâce à la température
	public void ajoutDurPuiTemp(int[] durPuiTemp) {
		durPuiTemp[1] = (int) (1.7*5.670373*0.00000001*Math.pow(durPuiTemp[2]+273,4.0));
		super.ajoutInformation(durPuiTemp);
	}

	//Méthode qui calcule l'énergie, à partir de la méthode getEnergie de la classe Appareil
	public int getEnergie() {
		return super.getEnergie();
	}

}
