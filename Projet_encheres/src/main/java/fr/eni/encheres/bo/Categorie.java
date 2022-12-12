package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> listeArticle;
	
	public Categorie() {
		this.listeArticle = new ArrayList<>();
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getListeArticle() {
		return listeArticle;
	}

	public void setListeArticle(List<ArticleVendu> listeArticle) {
		this.listeArticle = listeArticle;
	}

}
