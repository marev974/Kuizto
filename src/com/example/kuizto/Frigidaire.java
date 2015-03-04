package com.example.kuizto;

import java.util.*;

public class Frigidaire extends Appareil {

	//Constructeur de la classe Frigidaire
	public Frigidaire(Vector<int[]> durPui) {
		super(durPui);
		for (int i=0 ; i<getInformations().size() ;i++ ) {
			getInformations().get(i)[1] = 100;
		}
		typeAppareil = "Frigidaire";
	}

	//Méthode qui calcule l'énergie, à partir de la méthode getEnergie de la classe Appareil
	public int getEnergie() {
		return super.getEnergie();
	}
}



