package fr.eni.encheres.bll;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	
	private static ArticleManager manager;
	
	private ArticleManager() {}
	
	public static ArticleManager getManager() {
		if(manager == null) {
			manager = new ArticleManager();
		}
		return manager;
	}
	
	public ArticleVendu insertArticle(ArticleVendu article) {
		// TODO : gestion
		return DAOFactory.getArticleDAO().insertArticle(article);
	}

}