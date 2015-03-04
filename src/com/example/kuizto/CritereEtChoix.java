package com.example.kuizto;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



public class CritereEtChoix extends ActionBarActivity {

	private RelativeLayout grillePrincipale = null;

	private TextView titre = null;

	private Button hideShowIng = null;
	//private Slider sliderIng = null;
	private LinearLayout toHideIng = null;
	public EditText ingredientRecherche = null;
	private Button rechercheIng = null;

	private Button hideShowType = null;
	//private Slider sliderType = null;
	private LinearLayout toHideType = null;
	private RadioGroup typeChooser = null;
	private Button rechercheType = null;

	public final static String INGREDIENT = null ;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_critere_et_choix);

		//On récupère le bouton Ingrédient permettant de cacher et ouvrir le menu
		hideShowIng=(Button) findViewById(R.id.hideShowIng);
		//On récupère le slider
		/*sliderIng=new Slider(false, toHideIng);
        //On rajoute un Listener sur le click du bouton
        hideShowIng.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View vue){
                // pour afficher ou cacher le menu
                if(sliderIng.toggle()){
                    // Si le slide est ouvert, changer le texte en
                    hideShowIng.setHintTextColor(002200);
                }else{
                    //Sinon on met le texte en noir
                    hideShowIng.setHintTextColor(000000);
                }
            }
        });*/

		//On récupère le bouton Type permettant de cacher et ouvrir le menu
		//hideShowType=(Button) findViewById(R.id.hideShowType);
		//On rajoute un Listener sur le click du bouton
		/* hideShowType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View vue){
                // pour afficher ou cacher le menu
                if(sliderType.toggle()){
                    // Si le slide est ouvert, changer le texte en
                    hideShowType.setHintTextColor(002200);
                }else{
                    //Sinon on met le texte en noir
                    hideShowType.setHintTextColor(000000);
                }
            }
        });*/
		//On récupère le menu
		grillePrincipale=(RelativeLayout) findViewById(R.id.grilleGenerale);
		// On récupère le premier LinearLayout des ingrédients
		toHideIng=(LinearLayout)  findViewById(R.id.grilleIng);
		//On récupère le second LinearLayout des types de plats
		//   toHideType=(LinearLayout)  findViewById(R.id.grilleType);

		//On récupère l'éditeur de texte où l'on rentre ses ingrédients
		ingredientRecherche=(EditText)  findViewById(R.id.ingredientRentre);

		//On récupère le Radiogroup de sélection de type de plat
		// typeChooser=(RadioGroup)  findViewById(R.id.groupeType);
		/*typeChooser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
			en fonction de l'identifiant du Radiobouton sélectionné
			switch(checkedId){
				//On retourne l'item sélectionné
				// RadioButton group1_checked = typeChooser.getCheckedRadioButtonId();//(RadioButton) typeChooser.getCheckedRadioButtonId();
				//String val1 = group1_checked.getText().toString();

				// return val1;

				}
			}
		});*/
		//On récupère les deux boutons
		rechercheIng=(Button)  findViewById(R.id.RechercherIng);
		// rechercheType=(Button)  findViewById(R.id.RechercherType);
		ingredientRecherche=(EditText)  findViewById(R.id.ingredientRentre);

		rechercheIng.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v){
				//Le premier paramètre est le nom de l'activité actuelle
				//Le second paramètre est le nom de l'activité de destination
				Intent secondeActivite = new Intent(CritereEtChoix.this, ListeRecettes.class );
				//On ajoute un extra, ie une valeur que l'on transmettra au second intent
				secondeActivite.putExtra(INGREDIENT, ingredientRecherche.getText().toString());
				//On lance l'intent :
				startActivity(secondeActivite);
				ingredientRecherche.setText(null);
			}

		});
	}
	/*public String ingredientRech(){
    return ingredientRecherche.getContent();*/


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.critere_et_choix, menu);
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
}
