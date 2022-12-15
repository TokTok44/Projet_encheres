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
	
	public Utilisateur insertUser(Utilisateur utilisateur, String confirmation) throws BusinessException {
		
		BusinessException be = new BusinessException();
		if(!(utilisateur.getMotDePasse().equals(confirmation))) {
			be.ajouterErreur(CodesResultatBLL.ECHEC_CONFIRMATION);
		}
		
		verificationDonneesUtilisateur(utilisateur,be);
		
		if(be.hasErreurs()) {
			throw be;
		}
		
		return DAOFactory.getUtilisateurDAO().insertUser(utilisateur);
	}
	
	public void updateUser(Utilisateur utilisateur, String newMdp, String confirmationNewMdp) throws BusinessException {
		
		BusinessException be = new BusinessException();
		
		verificationDonneesUtilisateur(utilisateur,be);
		
		if(!(newMdp.equals(confirmationNewMdp))) {
			be.ajouterErreur(CodesResultatBLL.ECHEC_CONFIRMATION);
		}else if(!newMdp.isBlank()) {
			utilisateur.setMotDePasse(newMdp);
		}
		
		if(be.getListeCodesErreur().size() > 0) {
			throw be;
		}
		
		DAOFactory.getUtilisateurDAO().updateUser(utilisateur);
	}
	
	public void deleteUser(Utilisateur utilisateur, String motDePasse) throws BusinessException {
		
		if(utilisateur.getMotDePasse().equals(motDePasse)) {
		
			DAOFactory.getUtilisateurDAO().deleteUser(utilisateur.getNoUtilisateur());
		
		}else {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.ECHEC_SUPPRESSION);
			throw be;
		}
	}
	
	public Utilisateur selectUser(String pseudo) throws BusinessException {
		//TODO : gestion
		return DAOFactory.getUtilisateurDAO().selectUser(pseudo);
	}
	
	public Utilisateur selectConnexion(String identifiant, String mdp) throws BusinessException {
		//TODO : gestion
		return DAOFactory.getUtilisateurDAO().selectConnexion(identifiant, mdp);
	}
	
	private void verificationDonneesUtilisateur(Utilisateur utilisateur, BusinessException be) {
		
		if (utilisateur.getCodePostal() == null || utilisateur.getEmail() == null || utilisateur.getMotDePasse() == null
				|| utilisateur.getNom() == null || utilisateur.getPrenom() == null || utilisateur.getPseudo() == null
				|| utilisateur.getRue() == null || utilisateur.getVille() == null) {
			be.ajouterErreur(CodesResultatBLL.CHAMP_OBLIGATOIRE);
		}
		
		if(utilisateur.getPseudo().length() > 30 /*|| !(utilisateur.getPseudo().matches(""))*/) {
			be.ajouterErreur(CodesResultatBLL.PSEUDO_INVALIDE);
		}
		if(utilisateur.getNom().length() > 30 || utilisateur.getPrenom().length() > 30) {
			be.ajouterErreur(CodesResultatBLL.NOM_PRENOM_INVALIDE);
		}
		if(utilisateur.getEmail().length() > 40 || !(utilisateur.getEmail().matches("\\@"))) {
			be.ajouterErreur(CodesResultatBLL.EMAIL_INVALIDE);
		}
		if(utilisateur.getRue().length() > 30) {
			be.ajouterErreur(CodesResultatBLL.RUE_INVALIDE);
		}
		if(utilisateur.getCodePostal().length() > 10 || utilisateur.getCodePostal().length() < 5 /*|| utilisateur.getCodePostal().matches("")*/) {
			be.ajouterErreur(CodesResultatBLL.RUE_INVALIDE);
		}
		if (utilisateur.getVille().length() > 30) {
			be.ajouterErreur(CodesResultatBLL.VILLE_INVALIDE);
		}
		if (utilisateur.getMotDePasse().length() > 30 || utilisateur.getMotDePasse().length() < 6 /*|| utilisateur.getMotDePasse().matches("")*/) {
			be.ajouterErreur(CodesResultatBLL.MOT_DE_PASSE_INVALIDE);
		}
		
	}

}
