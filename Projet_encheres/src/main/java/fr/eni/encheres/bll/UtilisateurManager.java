package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exception.BusinessException;

public class UtilisateurManager {
	
	private static UtilisateurManager manager;
	
	private UtilisateurManager() {}
	
	public static UtilisateurManager getManager() {
		if(manager == null) {
			manager = new UtilisateurManager();
		}
		return manager;
	}
	
	public void insertUser(Utilisateur utilisateur) throws BusinessException {
		
		
		
		DAOFactory.getUtilisateurDAO().insertUser(utilisateur);
	}
	
	public void updateUser(Utilisateur utilisateur) throws BusinessException {
		//TODO : Gestion
		DAOFactory.getUtilisateurDAO().updateUser(utilisateur);
	}
	
	public void deleteUser(int noUtilisateur) {
		//TODO : gestion
		DAOFactory.getUtilisateurDAO().deleteUser(noUtilisateur);
	}
	
	public Utilisateur selectUser(int noUtilisateur) {
		//TODO : gestion
		return DAOFactory.getUtilisateurDAO().selectUser(noUtilisateur);
	}
	
	public Utilisateur selectConnexion(String identifiant, String mdp) {
		//TODO : gestion
		return DAOFactory.getUtilisateurDAO().selectConnexion(identifiant, mdp);
	}
	
	private void verificationUtilisateur(Utilisateur utilisateur) {
		
		
		
	}

}
