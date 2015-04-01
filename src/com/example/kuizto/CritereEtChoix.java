package com.example.kuizto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;


public class CritereEtChoix extends ActionBarActivity {

    // On cr�e les diff�rents attributs

    private RelativeLayout grillePrincipale = null;

    private TextView titre = null;

    private Button hideShowIng = null;
    private LinearLayout toHideIng = null;
    public EditText ingredientRecherche = null;
    private Button hideShowType = null;
    private LinearLayout toHideType = null;
    private RadioGroup typeChooser = null;
    private Button rechercheG = null;
    private ListView listeResultat =null;
    private TextView titreListe=null;

    final String RECETTE_RECH = "re";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critere_et_choix);

        //On r�cup�re le menu
        grillePrincipale = (RelativeLayout) findViewById(R.id.grilleGenerale);

        //On r�cup�re l'�diteur de texte o� l'on rentre ses ingr�dients
        ingredientRecherche = (EditText) findViewById(R.id.ingredientRentre);

        //On r�cup�re les deux boutons de recherche
        rechercheG = (Button) findViewById(R.id.rechercherType);
        ingredientRecherche = (EditText) findViewById(R.id.ingredientRentre);

        //On r�cup�re le Radiogroup de s�lection de type de plat
        typeChooser = (RadioGroup) findViewById(R.id.groupeType);

        //On r�cup�re La liste qui deviendra visible lorsque la recherche
        //sera effectu�e et qu'il y a un r�sultat
        listeResultat = (ListView) findViewById(R.id.recetteList);

        titreListe=(TextView) findViewById(R.id.titreListeRech);

//Lorsque l'on clique sur le bouton "Rechercher":

        rechercheG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //On r�cup�re l'ingr�dient recherch�, rentr� dans l'EditText par l'utilisateur
                String ingredientRech = ingredientRecherche.getText().toString();

                //On r�cup�re aussi le type de plat s�lectionn�
                //On commence par r�cup�rer le num�ro du bouton s�lectionn�
                int button_selected = typeChooser.getCheckedRadioButtonId();
                //On r�cup�re alors le bouton correspondant � ce num�ro
                RadioButton radioType = (RadioButton) findViewById(button_selected);
                //On d�finit alors le String coreespondant au plat recherch�
                String typePlatRech;
                if (button_selected < 0) {
                    typePlatRech = "";
                } else {
                    typePlatRech = radioType.getText().toString();
                }


                //On cr�e un vecteur de recettes
                Vector<Recette> lesRecettes;
                // On cr�e les objets de type RecetteOperations qui permettra de faire des
                // op�rations sur les tables de la base de donn�es.
                final RecetteOperations leRecetteOperations = new RecetteOperations(getApplicationContext());

                //On ouvre la base de donn�es
                leRecetteOperations.open();

                lesRecettes = leRecetteOperations.getRecettewithIngredientAndType(ingredientRech, typePlatRech);
                //On ferme la base de donn�es
                leRecetteOperations.close();

                //On cr�e un tableau pour la recherche par type de plat
                final String[] typeRec = new String[lesRecettes.size() + 1];

                //On transf�re les donn�es du vecteur de recettes vers un tableau de String avec les noms des recettes
                if (lesRecettes != null) {

                    for (int p = 0; p < lesRecettes.size(); p++) {
                        String s = lesRecettes.get(p).getTitre();


                        typeRec[p] = s;

                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Aucune recette correspondante", Toast.LENGTH_LONG).show();
                }
                //On cr�e  un adapter pour cr�er la liste de r�sultat
                ArrayAdapter<String> adapterType = new ArrayAdapter<String>(getApplicationContext(), R.layout.simplerow, typeRec );

                //On cr�e la liste
                listeResultat.setAdapter(adapterType);
                //On rend la liste et le titre visibles
                listeResultat.setVisibility(View.VISIBLE);
                titreListe.setVisibility(View.VISIBLE);


                //On vient de cr�er une liste. On associe alors une action aux items de cette liste.
                //Il faut lancer l'activit� affichageRecette
                listeResultat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, final View view, int position, long id) {

                        Intent secondeActivite = new Intent(CritereEtChoix.this, AffichageRecette.class);
                        String rec = typeRec[position];

                        secondeActivite.putExtra(RECETTE_RECH, rec);
                        startActivity(secondeActivite);
                    }
                });




            }
        });
    }





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
