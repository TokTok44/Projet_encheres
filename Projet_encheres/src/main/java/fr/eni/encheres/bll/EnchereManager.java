package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exception.BusinessException;

public class EnchereManager {
	
	private static EnchereManager manager;
	
	private EnchereManager() {}
	
	public static EnchereManager getManager() {
		if (manager == null) {
			manager = new EnchereManager();
		}
		return manager;
	}
	
	public Enchere insertEnchere(Enchere enchere) throws BusinessException {
		BusinessException be = new BusinessException();
		verifEnchere(enchere,be);
		if (be.hasErreurs()) {
			throw be;
		}
		
		return DAOFactory.getEncheresDAO().insertEnchere(enchere);
	}
	
	private void verifEnchere (Enchere enchere, BusinessException be) {
		if (enchere.getMontantEnchere() < 0 ) {
			be.ajouterErreur(CodesResultatBLL.ENCHERE_POSITIVE);
		}
		if (enchere.getUtilisateur().getCredit() < enchere.getMontantEnchere()) {
			be.ajouterErreur(CodesResultatBLL.CREDIT_INSUFFISANT);
		}
		if (enchere.getMontantEnchere() < enchere.getArticle().getMiseAPrix()) {
			be.ajouterErreur(CodesResultatBLL.ENCHERE_INSUFFISANTE);
		}
		if (enchere.getMontantEnchere() < enchere.getArticle().getPrixVente()) {
			be.ajouterErreur(CodesResultatBLL.ENCHERE_INSUFFISANTE);
		}
		if (enchere.getUtilisateur().getNoUtilisateur() == enchere.getArticle().getVendeur().getNoUtilisateur()) {
			be.ajouterErreur(CodesResultatBLL.MEME_UTILISATEUR);
		}
		if (enchere.getDateEnchere().isBefore(enchere.getArticle().getDateDebutEncheres())) {
			be.ajouterErreur(CodesResultatBLL.ENCHERE_NON_OUVERTE);
		}
		if (enchere.getDateEnchere().isAfter(enchere.getArticle().getDateFinEncheres())) {
			be.ajouterErreur(CodesResultatBLL.ENCHERE_TERMINEE);
		}
		
	}

}
