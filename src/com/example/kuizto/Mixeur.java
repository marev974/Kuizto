package com.example.kuizto;


import java.util.*;

public class Mixeur extends Appareil {

	//Constructeur de la classe Mixeur, classe dérivée de la classe Appareil
	public Mixeur(Vector<int[]> durPui) {
		super(durPui);
		for (int i=0 ; i<getInformations().size() ;i++ ) {
			getInformations().get(i)[1] = 350;
		}
		typeAppareil = "Mixeur";
	}

	//Méthode qui calcule l'énergie, à partir de la méthode getEnergie de la classe Appareil
	public int getEnergieMixeur() {
		return super.getEnergie();
	}

}

