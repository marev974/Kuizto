package com.example.kuizto;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;



public class ListeRecettes extends ActionBarActivity {
	private ListView listeDesRecettes;
	//private ArrayAdapter<String> listAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_recettes);

		//On récupère l'intent qui a lancé l'activité
		Intent i = getIntent();
		//On récupère l'ingrédient donné par l'autre activité, celle qui l'a ouvert, ou 0 s'il n'y a pas de valeurs.
		String ingredientRech = i.getStringExtra(CritereEtChoix.INGREDIENT);

		final RecetteOperations leRecetteOperations = new RecetteOperations(this);

		listeDesRecettes = (ListView) findViewById(R.id.recetteList);
		// On effectue la requ�te en stockant les r�sultats dans le vecteur lesRecettes, qu'on convertit en tableau
		Vector<Recette> lesRecettes;
		leRecetteOperations.open();
		lesRecettes = leRecetteOperations.getRecettewithType(ingredientRech);
		leRecetteOperations.close(); 
		
		String[] tableauRecettes = new String[lesRecettes.size() + 1];
		if (lesRecettes != null) {
			for (int p = 0; p < lesRecettes.size(); p++) {
				String s = lesRecettes.get(p).getTitre();
				tableauRecettes[p] = s;
			}
		}else { 
			tableauRecettes[0] = "Aucune recette correspondante";
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,tableauRecettes);
		listeDesRecettes.setAdapter(adapter);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.liste_recettes, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
	public void showPopUp(MenuItem it, String name){

	}
}
