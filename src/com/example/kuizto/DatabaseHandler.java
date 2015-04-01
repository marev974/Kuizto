package com.example.kuizto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper{

	private static DatabaseHandler sInstance;

	// Version de la base de donn�es
	private static final int VERSION_BASE_DE_DONNEES = 1;

	// Nom du fichier contenant la base de donn�es
	private static final String NOM_BASE_DE_DONNEES = "baserecettes.db";

	// Nom des tables qui seront cr��es dans la base de donn�es
	private static final String TABLE_RECETTES = "RECETTES";
	private static final String TABLE_REC_ING = "RELATION_REC_ING";

	// Les champs de la table "RECETTES"
	private static final String ID = "ID";
	private static final String TITRE = "TITRE";
	private static final String TYPE = "TYPE";
	private static final String TpsPreparation = "TpsPreparation";
	private static final String CONTENU = "CONTENU";

	// Les champs de la table "RELATION_REC_ING"
	private static final String TITRE_ING = "TITRE";
	private static final String INGREDIENT = "INGREDIENT";
	private static final String QUANTITE = "QUANTITE";
    private static final String UNITE = "UNITE";


	// Requ�te SQL de cr�ation de la table "RECETTES" dans la base de donn�es 
	private static final String REQUETE_CREATION_TABLE_REC = 
			"CREATE TABLE " + TABLE_RECETTES + 
			"(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			TITRE + " TEXT NOT NULL, " +  
			TYPE + " TEXT NOT NULL, " +  
			TpsPreparation + " INTEGER, " +
			CONTENU + " TEXT NOT NULL) ;";

	// Requ�te SQL de cr�ation de la table "RELATION_REC_ING" dans la base de donn�es 
	private static final String REQUETE_CREATION_TABLE_ING = 
			"CREATE TABLE " + TABLE_REC_ING + 
			"(" + TITRE_ING + " TEXT, " +
			INGREDIENT + " TEXT NOT NULL, " +
                    UNITE + " TEXT, " +
                    QUANTITE + " REAL) ;";

	public static DatabaseHandler getInstance(Context context) {

		// Use the application context, which will ensure that you 
		// don't accidentally leak an Activity's context.
		// See this article for more information: http://bit.ly/6LRzfx
		if (sInstance == null) {
			sInstance = new DatabaseHandler(context.getApplicationContext());
		}
		return sInstance;
	}

	// Constructeur de la classse DatabaseHandler	
	public DatabaseHandler(Context context) {
		super(context, NOM_BASE_DE_DONNEES, null, VERSION_BASE_DE_DONNEES);
	}


	// Cr�ation des tables "RECETTES" et "RELATION_REC_ING" dans la base de donn�es
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(REQUETE_CREATION_TABLE_REC);
		db.execSQL(REQUETE_CREATION_TABLE_ING);
	}


	// Mise � jour de la base de donn�es
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("Content provider database",  
				"Upgrading database from version " + oldVersion + " to "  
						+ newVersion + ", which will destroy all old data");  
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECETTES + ";");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REC_ING + ";");
		onCreate(db);
	}

	// Getters pour la table RECETTES

	public static String getId() {
		return ID;
	}

	public static String getTitre_Rec() {
		return TITRE;
	}

	public static String getType() {
		return TYPE;
	}

	public static String getTpspreparation() {
		return TpsPreparation;
	}
	
	public static String getContenu() {
		return CONTENU;
	}

	public static String getTableRecettes() {
		return TABLE_RECETTES;
	}

	// Getters pour la table RELATION_REC_ING

	public static String getTitre_Ing() {
		return TITRE_ING;
	}

	public static String getIngredient() {
		return INGREDIENT;
	}

	public static String getQuantite() {
		return QUANTITE;
	}

    public static String getUnite() {
        return UNITE;
    }

	public static String getTableRecIng() {
		return TABLE_REC_ING;
	}
}
