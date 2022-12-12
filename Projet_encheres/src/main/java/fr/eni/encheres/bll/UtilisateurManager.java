package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;

public class UtilisateurManager {
	
	private static UtilisateurManager manager;
	
	private UtilisateurManager() {}
	
	public static UtilisateurManager getManager() {
		if(manager == null) {
			manager = new UtilisateurManager();
		}
		return manager;
	}
	
	public void insertUser(Utilisateur utilisateur) {
		
	}

}
