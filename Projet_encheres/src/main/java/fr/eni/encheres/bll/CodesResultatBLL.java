package fr.eni.encheres.bll;

public interface CodesResultatBLL {
	
	int CHAMP_OBLIGATOIRE = 20_000;
	int ECHEC_CONFIRMATION = 20_001;
	int PSEUDO_INVALIDE = 20_002;
	int NOM_PRENOM_INVALIDE = 20_003;
	int EMAIL_INVALIDE = 20_004;
	int RUE_INVALIDE = 20_005;
	int CODE_POSTAL_INVALIDE = 20_006;
	int VILLE_INVALIDE = 20_007;
	int MOT_DE_PASSE_INVALIDE = 20_008;
	int CREDIT_INVALIDE = 20_009;
	int ECHEC_SUPPRESSION = 20_010;
	int PSEUDO_UNIQUE = 20_011;
	int EMAIL_UNIQUE = 20_012;
	int PRIX_POSITIF = 20_013;
	int INCOHERENCE_DATES = 20_014;
	int NOM_TROP_LONG = 20_015;
	int DESCRIPTION_TROP_LONGUE = 20_016;
	int ENCHERE_POSITIVE = 20_017;
	int CREDIT_INSUFFISANT = 20_018;
	int ENCHERE_INSUFFISANTE = 20_019;
	int MEME_UTILISATEUR = 20_020;
	int ENCHERE_NON_OUVERTE = 20_021;
	int ENCHERE_TERMINEE = 20_022;
	int ERREUR_MDP = 20_023;
	int UTILISATEUR_INEXISTANT = 20_024;
	int ECHEC_CONNEXION = 20_025;
	int DONNEES_INVALIDES = 20_026;
	int ARTICLE_INEXISTANT = 20_027;
}
