package fr.eni.encheres.bo;

public class Categorie {
	
	private int noCategorie;
	private String libelle;
	
	public Categorie() {}
	
	public Categorie(String libelle) {
		this();
		this.libelle = libelle;
	}
	
	public Categorie(int noCategorie, String libelle) {
		this();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public Categorie(int choixCategorie) {
		this();
		this.noCategorie = choixCategorie;
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

}
