package com.example.kuizto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.Vector;

public class RecetteOperations {
	public DatabaseHandler dbHelper;
	public SQLiteDatabase database;

	// Construction de la classe RecetteOperations
	public RecetteOperations(Context context) {
		dbHelper = new DatabaseHandler(context);
	}

	// Cr�ation de la base de donn�es avec un acc�s en lecture et �criture
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	// Fermeture de la connexion � la base de donn�es
	public void close() {
		dbHelper.close();
	}

	// M�thode pour ajouter une recette � la table de donn�es "RECETTES"
	public long addRecette(Recette rec) {

		// L'objet valeurs contient les donn�es � ajouter � la table
		ContentValues valeurs = new ContentValues();

		// Initialisation de la variable valeurs avec les couples de valeurs
		// suivants :
		// - (TITRE, titre)
		// - (TYPE, type)
		// - (TpsPreparation, tpsPreparation)
		// - (CONTENU, contenu)
		valeurs.put(dbHelper.getTitre_Rec(), rec.getTitre());
		valeurs.put(dbHelper.getType(), rec.getType());
		valeurs.put(dbHelper.getTpspreparation(), rec.getTpsPreparation());
		valeurs.put(dbHelper.getContenu(), rec.getContenu());

		// Insertion de l'enregistrement dans la table "RECETTES"
		long recetteId = database.insert(dbHelper.getTableRecettes(), null,
				valeurs);
		return recetteId;
	}

	// M�thode pour ajouter un enregistrement � la table de donn�e
	// "RELATION_REC_ING"
	public void addEntree(Recette rec) {

		// Initialisation de la variable valeurs avec les couples de valeurs
		// suivants :
		// - (TITRE, titre)
		// - (INGREDIENT, ingredient)
		// - (QUANTITE, quantite)
        // - (UNITE, unite)
		for (int i = 0; i < rec.getLesIngredients().size(); i++) {
			ContentValues valeurs = new ContentValues();
			valeurs.put(dbHelper.getTitre_Ing(), rec.getTitre());
			valeurs.put(dbHelper.getIngredient(), rec.getLesIngredients()
					.get(i).getNom());
			valeurs.put(dbHelper.getQuantite(), rec.getLesIngredients().get(i)
					.getQuantite());
            valeurs.put(dbHelper.getUnite(), rec.getLesIngredients().get(i)
                    .getUnite());

            // Insertion de l'enregistrement dans la table "RELATION_REC_ING"
			long entree = database.insert(dbHelper.getTableRecIng(), null,
					valeurs);
		}
	}

	// Pour r�cup�rer une recette gr�ce � son ingr�dient
	public Vector<Recette> getRecettewithIngredient(String nom_ingredient) {

		// On cr�e un vecteur de Recette qui contiendra toutes les recettes qui
		// r�sultent de la recherche
		Vector<Recette> lesRecettes = new Vector<Recette>();

		// On ex�cute la requ�te dont le r�sultat sera stock� dans la variable
		// cursor
		Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
				"SELECT RECETTES.TITRE, RECETTES.TYPE, RECETTES.TpsPreparation "
						+ "FROM  RELATION_REC_ING,RECETTES "
						+ "WHERE RELATION_REC_ING.INGREDIENT = '"
						+ nom_ingredient
						+ "' and RELATION_REC_ING.TITRE = RECETTES.TITRE;",

				new String[] {});
		// On r�cup�re les valeurs des colonnes des enregistrements stock�s dans
		// l'objet cursor.
		// On construit un vecteur de Recette
		int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper
				.getTitre_Rec());
		int numeroColonneType = cursor
				.getColumnIndexOrThrow(dbHelper.getType());
		int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper
				.getTpspreparation());

		if (cursor.moveToFirst() == true) {
			do {
				// On cr�e une recette on lui affecte toutes les informations
				// contenues dans le cursor
				String titre = cursor.getString(numeroColonneTitre);
				String type = cursor.getString(numeroColonneType);
				int tpsPreparation = cursor.getInt(numeroColonneTps);
				Recette rec = new Recette(titre, type, tpsPreparation);
				lesRecettes.add(rec);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return lesRecettes;
	}

	// Pour r�cup�rer une recette gr�ce � son type (entr�e, plat ou dessert)
	public Vector<Recette> getRecettewithType(String unType) {

		// On cr�e un vecteur de Recette qui contiendra toutes les recettes qui
		// r�sultent de la recherche
		Vector<Recette> lesRecettes = new Vector<Recette>();

		// La requ�te r�cup�re toutes les informations sur le recette-r�sultat
		// de la recherche
		String tabColonne[] = new String[4];
		tabColonne[0] = dbHelper.getId();
		tabColonne[1] = dbHelper.getTitre_Rec();
		tabColonne[2] = dbHelper.getType();
		tabColonne[3] = dbHelper.getTpspreparation();

		// On ex�cute la requ�te dont le r�sultat sera stock� dans la variable
		// cursor
		Cursor cursor = database.query(dbHelper.getTableRecettes(), tabColonne,
				dbHelper.getType() + " LIKE \"" + unType + "\"", null, null,
				null, null);

		// On r�cup�re les valeurs des colonnes des enregistrements stock�s dans
		// l'objet cursor
		int numeroColonneId = cursor.getColumnIndexOrThrow(dbHelper.getId());
		int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper
				.getTitre_Rec());
		int numeroColonneType = cursor
				.getColumnIndexOrThrow(dbHelper.getType());
		int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper
				.getTpspreparation());

		if (cursor.moveToFirst() == true) {
			do {
				// On cr�e une recette on lui affecte toutes les informations
				// contenues dans le cursor
				String titre = cursor.getString(numeroColonneTitre);
				String type = cursor.getString(numeroColonneType);
				int tps = cursor.getInt(numeroColonneTps);
				Recette rec = new Recette(titre, type, tps);
				lesRecettes.add(rec);
			} while (cursor.moveToNext());
		}
		cursor.close();
		return lesRecettes;
	}

	// Pour r�cup�rer une recette gr�ce � un ingr�dient et un type
	public Vector<Recette> getRecettewithIngredientAndType(
			String nom_ingredient, String unType) {

		// On cr�e un vecteur de Recette qui contiendra toutes les recettes qui
		// r�sultent de la recherche
		Vector<Recette> lesRecettes = new Vector<Recette>();
        unType = unType.toLowerCase();
		// S'il n'y a que l'ingr�dient qui est renseign�, on ex�cute la m�thode
		// getRecettewithIngredient()
		if (unType.equals("") ) {
			lesRecettes = getRecettewithIngredient(nom_ingredient);

			// S'il n'y a que le type qui est renseign�, on ex�cute la m�thode

			// getRecettewithType()
		} else if (nom_ingredient.equals("") ) {
			lesRecettes = getRecettewithType(unType);

			// Si on a un nom d'ingr�dient et un nom de type :
		} else {
			// On ex�cute la requ�te dont le r�sultat sera stock� dans la
			// variable cursor
			Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
					"SELECT RECETTES.TITRE, RECETTES.TYPE "
							+ "FROM  RELATION_REC_ING,RECETTES "
							+ "WHERE RELATION_REC_ING.INGREDIENT = '"
							+ nom_ingredient + "' and RECETTES.TYPE ='"
							+ unType
							+ "' and RELATION_REC_ING.TITRE = RECETTES.TITRE;",

					new String[] {});
			// On r�cup�re les valeurs des colonnes des enregistrements stock�s
			// dans l'objet cursor.
			// On construit un vecteur de Recette
			int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper
					.getTitre_Rec());
			int numeroColonneType = cursor.getColumnIndexOrThrow(dbHelper
					.getType());
			//int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper
					//.getTpspreparation());

			if (cursor.moveToFirst() == true) {
				do {
					// On cr�e une recette on lui affecte toutes les
					// informations contenues dans le cursor
					String titre = cursor.getString(numeroColonneTitre);
					String type = cursor.getString(numeroColonneType);
					//int tps = cursor.getInt(numeroColonneTps);
					Recette rec = new Recette(titre, type, 0);
					lesRecettes.add(rec);
				} while (cursor.moveToNext());
			}
			cursor.close();

		}
		return lesRecettes;
	}

	// Pour r�cup�rer une recette gr�ce � son titre
    public Recette getRecettewithTitre(String titre_recette) {

        // On cr�e un vecteur de Recette qui contiendra toutes les recettes qui
        // r�sultent de la recherche
        Recette laRecette=null;

        // On ex�cute la requ�te dont le r�sultat sera stock� dans la variable
        // cursor
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT TITRE, TYPE, TpsPreparation, CONTENU " + "FROM  RECETTES "
                        + "WHERE TITRE = '" + titre_recette + "';",

                new String[] {});
        // On r�cup�re les valeurs des colonnes des enregistrements stock�s dans
        // l'objet cursor.
        // On construit un vecteur de Recette
        int numeroColonneTitre = cursor.getColumnIndexOrThrow(dbHelper
                .getTitre_Rec());
        int numeroColonneType = cursor.getColumnIndexOrThrow(dbHelper
                .getType());
        int numeroColonneTps = cursor.getColumnIndexOrThrow(dbHelper
                .getTpspreparation());
        int numeroColonneCont = cursor.getColumnIndexOrThrow(dbHelper
                .getContenu());


        if (cursor.moveToFirst() == true) {
            do {
                // On cr�e une recette on lui affecte toutes les informations
                // contenues dans le cursor
                String titre = cursor.getString(numeroColonneTitre);
                String type = cursor.getString(numeroColonneType);
                int tps = cursor.getInt(numeroColonneTps);
                String contenu = cursor.getString(numeroColonneCont);
                laRecette = new Recette(titre, type, tps);
                laRecette.setContenu(contenu);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return laRecette;
    }

    // Pour r�cup�rer une recette gr�ce � son titre
    public Vector<Ingredient> getIngredientwithTitre(String titre_recette) {

        // On cr�e un vecteur de Recette qui contiendra toutes les recettes qui
        // r�sultent de la recherche
        Vector<Ingredient> lesIngredients = new Vector<Ingredient>();


        // La requ�te r�cup�re toutes les informations sur le recette-r�sultat
        // de la recherche
        String tabColonne[] = new String[4];
        tabColonne[0] = dbHelper.getTitre_Ing();
        tabColonne[1] = dbHelper.getIngredient();
        tabColonne[2] = dbHelper.getQuantite();
        tabColonne[3] = dbHelper.getUnite();

        // On ex�cute la requ�te dont le r�sultat sera stock� dans la variable
        // cursor
        Cursor cursor = database.query(dbHelper.getTableRecIng(), tabColonne,
                dbHelper.getTitre_Ing() + " LIKE \"" + titre_recette + "\"", null, null,
                null, null);
        /*// On ex�cute la requ�te dont le r�sultat sera stock� dans la variable
        // cursor
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(
                "SELECT INGREDIENT, QUANTITE, UNITE " + "FROM  RELATION_REC_ING "
                        + "WHERE TITRE_ING = '" + titre_recette + "';",
                new String[] {});*/

        // On r�cup�re les valeurs des colonnes des enregistrements stock�s dans
        // l'objet cursor.
        // On construit un vecteur de Recette
        int numeroColonneIngredient = cursor.getColumnIndexOrThrow(dbHelper
                .getIngredient());
        int numeroColonneQuantite = cursor.getColumnIndexOrThrow(dbHelper
                .getQuantite());
        int numeroColonneUnite = cursor.getColumnIndexOrThrow(dbHelper
                .getUnite());


        if (cursor.moveToFirst() == true) do {
            // On cr�e une recette on lui affecte toutes les informations
            // contenues dans le cursor
            String ingredient = cursor.getString(numeroColonneIngredient);
            int quantite = cursor.getInt(numeroColonneQuantite);
            String unite = cursor.getString(numeroColonneUnite);

          Ingredient  ing = new Ingredient(ingredient, quantite, 0, unite);
            lesIngredients.add(ing);

        } while (cursor.moveToNext());
        cursor.close();
        return lesIngredients;
    }
}
