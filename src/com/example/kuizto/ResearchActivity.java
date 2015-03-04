package com.example.kuizto;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

public class ResearchActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_research);


		final RecetteOperations leRecetteOperations = new RecetteOperations(this);

		// Création des objets : Recette, Ingredient

		/**
		 *Toast au chèvre
		 */

		Recette toast = new Recette("Toast au chevre", "plat principal", 20);

		Ingredient pain = new Ingredient ("pain", 4, 0.2, "tranches de");
		Ingredient pomme = new Ingredient ("pomme", 1, 0.35, "");
		Ingredient chevre = new Ingredient ("chèvre", 4, 0.2, "tranches de" );
		toast.setIngredient(pain);
		toast.setIngredient(pomme);
		toast.setIngredient(chevre);

		Four fourToast = new Four();
		Plaque plaqueToast = new Plaque();
		toast.setAppareil(fourToast);
		toast.setAppareil(plaqueToast);
		int[] tripToastP = new int[3];
		tripToastP[0] = 3; 
		tripToastP[1] = 0 ; 
		tripToastP[2] = 5;
		plaqueToast.ajoutDurPuiNiv(tripToastP);
		int[] tripToastF = new int[3];
		tripToastF [0] = 10; tripToastF[1] = 0 ; tripToastF[2] = 180;
		fourToast.ajoutDurPuiTemp(tripToastF);

		String contenuToast1 = "Prechauffer le four à " + fourToast.getInformations().get(0)[2] +"°C.";
		String contenuToast2 = "Couper la pomme en douze quartiers et faites-les cuire à la poële au niveau " + plaqueToast.getInformations().get(0)[2] + " pendant " + plaqueToast.getInformations().get(0)[0] + " minutes.";
		String contenuToast3 = "Disposer trois quartiers de pomme par tranche de pain puis ajouter une tranche de chèvre. " + "\n" +
				"Mettre au four pendant "+ fourToast.getInformations().get(0)[0]+ " minutes.";

		/**
		 * Mousse au chocolat
		 */

		Recette mousseChocolat = new Recette("Mousse au chocolat", "dessert", 180);

		Ingredient tabletteChocolat = new Ingredient ("chocolat", 1, 1.2, "tablette de");
		Ingredient oeuf = new Ingredient ("oeufs", 6, 0.3, "");
		mousseChocolat.setIngredient(tabletteChocolat);
		mousseChocolat.setIngredient(oeuf);


		int[] tripMousseFri = new int[3];
		tripMousseFri[0]=160; 
		Vector<int[]> vecMousseFri = new Vector<int[]>();
		vecMousseFri.add(tripMousseFri);
		Frigidaire frigoMousse = new Frigidaire(vecMousseFri);
		int[]tripMousseM = new int[3];
		tripMousseM[0]=10;
		Vector<int[]> vecMousseM= new Vector<int[]>();
		vecMousseM.add(tripMousseM);
		Mixeur mixeurMousse = new Mixeur(vecMousseM);
		Plaque plaqueMousse = new Plaque();
		int[] tripMousse = new int[3];
		tripMousse[0] = 3; 
		tripMousse[1] = 0 ; 
		tripMousse[2] = 5;
		plaqueMousse.ajoutDurPuiNiv( tripMousse);
		mousseChocolat.setAppareil(plaqueMousse);
		mousseChocolat.setAppareil(frigoMousse);
		mousseChocolat.setAppareil(mixeurMousse);

		String contenuMousse1 = "Séparer les blancs des jaunes et battre les blancs pendant " + mixeurMousse.getInformations().get(0)[0] +" minutes.";
		String contenuMousse2 = "Faire fondre le chocolat avec un peu d'eau à la casserole au niveau " + plaqueMousse.getInformations().get(0)[2] +" pendant " +
				plaqueMousse.getInformations().get(0)[0] + " minutes, puis le mélanger avec le chocolat";
		String contenuMousse3 = "Incorporer 1/3 des blancs au chocolat en mélangeant rapidement, puis ajouter progressivement le reste des blancs en mélangeant délicatement. ";
		String contenuMousse4 = "Réserver au frigidaire pendant "+frigoMousse.getInformations().get(0)[0]+" minutes";


		/**
		 * Oeufs Mimosa
		 */

		Recette oeufsMimosa = new Recette("Oeufs mimosa", "entrée", 90);

		Ingredient mayonnaise = new Ingredient ("mayonnaise", 3, 0.05, "cuillère à café de");
		Ingredient thon = new Ingredient ("thon", 1, 1, "boîte de");
		Ingredient oeuf2 = new Ingredient ("oeufs", 2, 0.2, "");
		oeufsMimosa.setIngredient(mayonnaise);
		oeufsMimosa.setIngredient(oeuf2);
		oeufsMimosa.setIngredient(thon);

		int[] tripOeufsF = new int[3];
		tripOeufsF[0]=60; 
		Vector<int[]> vecOeufs = new Vector<int[]>();
		vecOeufs.add(tripOeufsF);
		Frigidaire frigoOeufs = new Frigidaire(vecOeufs);
		Plaque plaqueOeufs = new Plaque();
		oeufsMimosa.setAppareil(plaqueOeufs);
		oeufsMimosa.setAppareil(frigoOeufs);
		int[] tripOeufsP1 = new int[3];
		tripOeufsP1[0] = 3; tripOeufsP1[1] = 0 ; tripOeufsP1[2] = 5;
		plaqueOeufs.ajoutDurPuiNiv(tripOeufsP1);
		int[] tripOeufsP2 = new int[3];
		tripOeufsP2[0] = 10; tripOeufsP2[1] = 0 ; tripOeufsP2[2] = 4;
		plaqueOeufs.ajoutDurPuiNiv(tripOeufsP2);
		oeufsMimosa.setAppareil(frigoOeufs);
		oeufsMimosa.setAppareil(plaqueOeufs);

		String contenuOeufs1 = "Faire chauffer l'eau dans une casserole au niveau " + plaqueOeufs.getInformations().get(0)[2] + " pendant " + plaqueOeufs.getInformations().get(0)[0] + " minutes.";
		String contenuOeufs2 = "Mettre les deux oeufs dans la casserole au niveau " + plaqueOeufs.getInformations().get(1)[2] + " pendant " + plaqueOeufs.getInformations().get(1)[0] + " minutes.";
		String contenuOeufs3 = "Retirer les oeufs de la casserole et attendre qu'ils refroidissent." + 
				"\n"+"Retirer la coque des oeufs. Les couper en deux, retirer le jaune." 
				+ "\n" + "Mélanger le thon, la mayonnaise et le jaune, et replacer le mélange dans le creux laissé par le jaune d'oeuf.";
		String contenuOeufs4 = "Réserver au frigidaire pendant "+frigoOeufs.getInformations().get(0)[0]+" minutes";
		/**
		 * Taboulé
		 */

		Recette taboule = new Recette("Taboulé", "plat principal", 30);

		Ingredient semoule = new Ingredient ("semoule",50 , 0.002, "grammes de");
		Ingredient tomate1 = new Ingredient ("tomate", 3, 0.3, "");
		Ingredient poivron = new Ingredient ("poivron", 0.5, 0.75, "" );
		Ingredient citron = new Ingredient ("citron", 0.5,0.2, "");
		Ingredient dinde = new Ingredient ("dinde", 1, 1.91, "");
		taboule.setIngredient(semoule);
		taboule.setIngredient(tomate1);
		taboule.setIngredient(poivron);
		taboule.setIngredient(citron);
		taboule.setIngredient(dinde);

		int[] tripTabF = new int[3];
		tripTabF[0]=60; 
		Vector<int[]> vecTab = new Vector<int[]>();
		vecTab.add(tripTabF);
		Frigidaire frigoTab = new Frigidaire(vecTab);
		Plaque plaqueTab = new Plaque();
		taboule.setAppareil(frigoTab);
		taboule.setAppareil(plaqueTab);
		int[] tripTabP1 = new int[3];
		tripTabP1[0] = 3; tripTabP1[1] = 0 ; tripTabP1[2] = 6;
		int[] tripTabP2 = new int[3];
		tripTabP2[0] = 5; tripTabP2[1] = 0 ; tripTabP2[2] = 4;
		plaqueTab.ajoutDurPuiNiv(tripTabP1);
		plaqueTab.ajoutDurPuiNiv(tripTabP2);

		String contenutaboule1 = "Mettre la semoule dans un saladier. Chauffer l'eau à la casserole au niveau " + plaqueTab.getInformations().get(0)[2] +" pendant " + plaqueTab.getInformations().get(0)[0] + " minutes.";
		String contenutaboule2 = "Lorsque l'eau bout, verser l'eau sur la semoule et mélanger. La semoule gonfle.";
		String contenutaboule3 = "Couper la tomate, le poivron, la dinde en petit morceaux." + 
				"\n" + "Faire cuire les morceaux de dinde à la poele, avec un peu d'huile, au niveau "+ plaqueTab.getInformations().get(1)[2] + " pendant " + plaqueTab.getInformations().get(1)[0] + " minutes.";
		String contenutaboule4 = "Mélanger la semoule, la tomate, le poivron, les morceaux de dinde. Rajouter un peu de menthe et des raisins secs si vous en avez, et y verser le jus d'un demi citron."+
				"\n" + "Placez votre saladier au réfrigérateur pendant " + frigoTab.getInformations().get(0)[0]+ " minutes." ;

		leRecetteOperations.open();
		leRecetteOperations.dbHelper.onUpgrade(leRecetteOperations.database,2,1);
		leRecetteOperations.addRecette(toast);
		leRecetteOperations.addEntree(toast);
		leRecetteOperations.addRecette(mousseChocolat);
		leRecetteOperations.addEntree(mousseChocolat);
		leRecetteOperations.addRecette(oeufsMimosa);
		leRecetteOperations.addEntree(oeufsMimosa);
		leRecetteOperations.addRecette(taboule);
		leRecetteOperations.addEntree(taboule);
	

		Toast.makeText(getApplicationContext(), "Tables bien créées", Toast.LENGTH_LONG).show();

		// On effectue la requête en stockant les résultats dans le vecteur lesRecettes, qu'on convertit en tableau
		Vector<Recette> lesRecettes;
		lesRecettes = leRecetteOperations.getRecettewithIngredientAndType("","plat principal");
		leRecetteOperations.close();
		
		if (lesRecettes != null) {
			for (int p = 0; p < lesRecettes.size(); p++) {
				Toast.makeText(getApplicationContext(),lesRecettes.get(p).getTitre(), Toast.LENGTH_LONG).show();

			}
		}


		// On récupère par son identifiant le TextField où l'on rentre un nom d'ingrédient 
		final EditText champ_ingredient = (EditText) findViewById(R.id.ingredient_cherche);


		// On récupère le bouton "GO !" par son identifiant et on définit ce qu'il se produit lors d'un click
		final Button bouton_GO = (Button) findViewById(R.id.button_GO);
		bouton_GO.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				Intent activiteRecherche = new Intent(ResearchActivity.this, CritereEtChoix.class );
				startActivity(activiteRecherche);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.research, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

