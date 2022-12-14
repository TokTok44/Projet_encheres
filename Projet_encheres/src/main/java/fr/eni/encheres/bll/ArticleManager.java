package fr.eni.encheres.bll;

public class ArticleManager {
	
	private static ArticleManager manager;
	
	private ArticleManager() {}
	
	public static ArticleManager getManager() {
		if(manager == null) {
			manager = new ArticleManager();
		}
		return manager;
	}
	
	

}