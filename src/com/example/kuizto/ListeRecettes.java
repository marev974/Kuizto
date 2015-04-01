package com.example.kuizto;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.util.Vector;


public class ListeRecettes extends ActionBarActivity {
    private ListView listeEntrees;
    private ListView listePlats;
    private ListView listeDesserts;
    private ArrayAdapter<String> listAdapter;
    private Button rechRec =null;

    //On crée le String correspondant à l'Extra qui sera mis ensuite

    final String RECETTE_SELECT = "re";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_recettes);
    //On récupère le bouton de recherche
        rechRec=(Button)  findViewById(R.id.recherche);
    //On associe une action à un clic sur ce bouton :
        rechRec.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Le premier paramètre est le nom de l'activité actuelle
                //Le second paramètre est le nom de l'activité de destination
                Intent secondeActivite = new Intent(ListeRecettes.this, CritereEtChoix.class );
                //On lance l'intent :
                startActivity(secondeActivite);
            }

        });


        //On crée un vecteur de recettes par type de plats
        Vector<Recette> lesRecettesEntrees;
        Vector<Recette> lesRecettesPlats;
        Vector<Recette> lesRecettesDesserts;

        //On crée un RecetteOperations pour effectuer les recherches
        final RecetteOperations leRecetteOperations = new RecetteOperations(this);
        //On l'ouvre
        leRecetteOperations.open();

        //On complète ensuite chaque vecteur de recette :
        lesRecettesEntrees = leRecetteOperations.getRecettewithType("entrée");
        lesRecettesPlats = leRecetteOperations.getRecettewithType("plat principal");
        lesRecettesDesserts = leRecetteOperations.getRecettewithType("dessert");
        //On le ferme
        leRecetteOperations.close();

        //On construit trois tableaux de recettes à partir de ces vecteurs :
        final String[] recettesEntrees = new String[lesRecettesEntrees.size()];
        final String[] recettesPlats = new String[lesRecettesPlats.size()];
        final String[] recettesDesserts = new String[lesRecettesDesserts.size()];

        //On convertit chaque vecteur en tableau :
            //remplissage du tableau des entrées :
        for (int p = 0; p < lesRecettesEntrees.size(); p++) {
            String s = lesRecettesEntrees.get(p).getTitre();
            recettesEntrees[p] = s;
        }

            //remplissage du tableau des plats principaux :
        for (int q = 0; q < lesRecettesPlats.size(); q++) {
            String ss = lesRecettesPlats.get(q).getTitre();
            recettesPlats[q] = ss;
        }

            //remplissage du tableau des desserts :
        for (int r = 0; r < lesRecettesDesserts.size(); r++) {
            String rr = lesRecettesDesserts.get(r).getTitre();
            recettesDesserts[r] = rr;
        }

        //On récupère chaque liste sur le fichier xml :
        listeEntrees = (ListView) findViewById(R.id.listeRecEnt);
        listePlats = (ListView) findViewById(R.id.listePlatsPrinc);
        listeDesserts = (ListView) findViewById(R.id.listeDesDesserts);

        //On créée alors un adapter pour créer chaque liste sur l'activité :

        //La liste des entrées :

        ArrayAdapter entreesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recettesEntrees);
        listeEntrees.setAdapter(entreesAdapter);

        //La liste des plats :
        ArrayAdapter platsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recettesPlats);
        listePlats.setAdapter(platsAdapter);

        //La liste des desserts :

        ArrayAdapter dessertsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recettesDesserts);
        listeDesserts.setAdapter(dessertsAdapter);


        //On s'occupe de ce qu'il se passera lorsque l'on clique sur un item de la liste :
        //Une nouvelle activité s'ouvre, qui affiche la recette sélectionnée
        //Pour la liste des Entrées
        listeEntrees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecettes.this, AffichageRecette.class);
                String titre = recettesEntrees[position];
                secondeActivite.putExtra(RECETTE_SELECT, titre);
                startActivity(secondeActivite);
            }
        });
        //Pour la liste des plats
        listePlats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecettes.this, AffichageRecette.class);
                String titre = recettesPlats[position];
                secondeActivite.putExtra(RECETTE_SELECT, titre);
                startActivity(secondeActivite);
            }
        });
        //Pour la liste des desserts
        listeDesserts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, final View view, int position, long id) {

                Intent secondeActivite = new Intent(ListeRecettes.this, AffichageRecette.class);
                String titre = recettesDesserts[position];
                secondeActivite.putExtra(RECETTE_SELECT, titre);
                startActivity(secondeActivite);
            }
        });
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

}