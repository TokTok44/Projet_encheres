package fr.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.exception.BusinessException;

public class ArticleManager {
	
	private static ArticleManager manager;
	
	private ArticleManager() {}
	
	public static ArticleManager getManager() {
		if(manager == null) {
			manager = new ArticleManager();
		}
		return manager;
	}
	
	public ArticleVendu insertArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		
		verifArticle(article, be);
		if(be.hasErreurs()) {
			throw be;
		}
		
		return DAOFactory.getArticleDAO().insertArticle(article);
	}
	
	public List<ArticleVendu> selectAllArticle(int noCategorie, String recherche){
		String condition = null;
		
		if(noCategorie != 0 && !recherche.isBlank()) {
			condition = " WHERE no_categorie = ? AND nom_article LIKE ?;";
		}else if(noCategorie != 0){
			condition = " WHERE no_categorie = ?;";
		}else if(!recherche.isBlank()) {
			condition = " WHERE nom_article LIKE ?;";
		}else {
			condition = ";";
		}
		
		return DAOFactory.getArticleDAO().selectAllArticle(condition, noCategorie, recherche);
	}

	public List<ArticleVendu> selectArticleFiltre(int noCategorie, int noUtilisateur, String recherche,boolean ventes, boolean ventesEnCours,
			boolean ventesAVenir, boolean ventesTerminees, boolean encheresOuvertes, boolean mesEncheresOuvertes,
			boolean mesEncheresTerminees){
		String condition = "";
		if(!ventes && !mesEncheresOuvertes && !mesEncheresTerminees) {
			if(noCategorie != 0 && !recherche.isBlank()) {
				condition = " WHERE (ARTICLES_VENDUS.no_categorie = ?) AND (ARTICLES_VENDUS.nom_article LIKE ?)";
			}else if(noCategorie != 0){
				condition = " WHERE (ARTICLES_VENDUS.no_categorie = ?)";
			}else if(!recherche.isBlank()) {
				condition = " WHERE (ARTICLES_VENDUS.nom_article LIKE ?)";
			}
		}else {
			if(noCategorie != 0 && !recherche.isBlank()) {
				condition = " AND (ARTICLES_VENDUS.no_categorie = ?) AND (ARTICLES_VENDUS.nom_article LIKE ?)";
			}else if(noCategorie != 0){
				condition = " AND (ARTICLES_VENDUS.no_categorie = ?)";
			}else if(!recherche.isBlank()) {
				condition = " AND (ARTICLES_VENDUS.nom_article LIKE ?)";
			}
		}
		
		return DAOFactory.getArticleDAO().selectArticlesFiltre(condition, noCategorie, noUtilisateur, recherche, ventes, ventesEnCours, ventesAVenir, ventesTerminees, encheresOuvertes, mesEncheresOuvertes, mesEncheresTerminees);
		
	}

	public ArticleVendu selectArticle(int noArticle) {
		return DAOFactory.getArticleDAO().selectArticle(noArticle);
	}

	public void updateArticle(ArticleVendu article) throws BusinessException {
		BusinessException be = new BusinessException();
		
		verifArticle(article,be);
		if(be.hasErreurs()) {
			throw be;
		}
		
		DAOFactory.getArticleDAO().updateArticle(article);
	}
	
	public void deleteArticle(int noArticle) {
		DAOFactory.getArticleDAO().deleteArticle(noArticle);
	}
	
	private void verifArticle(ArticleVendu article, BusinessException be) {
		if(article.getMiseAPrix() < 0) {
			be.ajouterErreur(CodesResultatBLL.PRIX_POSITIF);
		}
		if(article.getDateDebutEncheres().isAfter(article.getDateFinEncheres())) {
			be.ajouterErreur(CodesResultatBLL.INCOHERENCE_DATES);
		}
		if(article.getNomArticle().length() > 30) {
			be.ajouterErreur(CodesResultatBLL.NOM_TROP_LONG);
		}
		if(article.getDescription().length() > 300) {
			be.ajouterErreur(CodesResultatBLL.DESCRIPTION_TROP_LONGUE);
		}
		
		
	}
	
}