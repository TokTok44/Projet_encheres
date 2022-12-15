package fr.eni.encheres.bll;

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
		
		if(article.getMiseAPrix() < 0) {
			throw new BusinessException();
		}
		
		return DAOFactory.getArticleDAO().insertArticle(article);
	}
	
	public List<ArticleVendu> selectArticle(int noCategorie, String recherche){
		String condition = null;
		
		if(noCategorie != 0 && !recherche.isBlank()) {
			
			condition = " WHERE no_categorie = ? AND nom_article LIKE %?%;";
			
		}else if(noCategorie != 0){
			
			condition = " WHERE no_categorie = ?;";
			
		}else if(!recherche.isBlank()) {
			
			condition = " WHERE nom_article LIKE %?%;";
			
		}else {
			condition = ";";
		}
		
		return DAOFactory.getArticleDAO().selectArticle(condition, noCategorie, recherche);
	}

}