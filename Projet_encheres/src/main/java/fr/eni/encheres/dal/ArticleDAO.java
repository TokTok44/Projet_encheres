package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;

public interface ArticleDAO {
	
	public ArticleVendu insertArticle(ArticleVendu article);
	
	public List<ArticleVendu> selectArticle(String condition, int noCategorie, String recherche);
	
	public List<ArticleVendu> selectArticlesConnecte(String condition, int noCategorie, int noUtilisateur, String recherche, boolean ventes, boolean ventesEnCours, boolean ventesAVenir, boolean ventesTerminees, boolean encheresOuvertes, boolean mesEncheresOuvertes, boolean mesEncheresTerminees);

}