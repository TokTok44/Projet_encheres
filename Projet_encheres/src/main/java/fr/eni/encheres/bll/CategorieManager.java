package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.DAOFactory;

public class CategorieManager {
	
	private static CategorieManager manager;
	
	private CategorieManager() {}
	
	public static CategorieManager getManager() {
		if(manager == null) {
			manager = new CategorieManager();
		}
		return manager;
	}
	
	public void insertCategorie(Categorie categorie) {
		//TODO : gestion insertion categorie
		DAOFactory.getCategorieDAO().insertCategorie(categorie);
	}
	
	public List<Categorie> selectAll(){
		// TODO : gestion select categorie
		return DAOFactory.getCategorieDAO().selectAll();
	}

}
