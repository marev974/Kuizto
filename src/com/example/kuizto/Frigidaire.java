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

	//M�thode qui calcule l'�nergie, � partir de la m�thode getEnergie de la classe Appareil
	public int getEnergie() {
		return super.getEnergie();
	}
}



