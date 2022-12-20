package fr.eni.encheres.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	//TODO d�clarer etatVente
	private Utilisateur acheteur;
	private Utilisateur vendeur;
	private List<Enchere> listeEnchere;
	private Categorie categorie;
	private Retrait pointRetrait;
	
	public ArticleVendu() {
		this.listeEnchere = new ArrayList<>();
	}

	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, Categorie categorie, Retrait pointRetrait) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.pointRetrait = pointRetrait;
	}

	public ArticleVendu(Utilisateur vendeur,String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, Categorie categorie, Retrait pointRetrait) {
		this();
		this.vendeur = vendeur;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.pointRetrait = pointRetrait;
	}


	public ArticleVendu(int noArticle, String nomArticle, int prixVente, LocalDate dateFin, Utilisateur vendeur) {
		this();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.prixVente = prixVente;
		this.dateFinEncheres = dateFin;
		this.vendeur = vendeur;
	}

	public ArticleVendu(String nomArticle, String description, int prixVente, int miseAPrix, LocalDate dateFin, LocalDate dateDebut) {
		this.nomArticle = nomArticle;
		this.description = description;
		this.prixVente = prixVente;
		this.miseAPrix = miseAPrix;
		this.dateFinEncheres = dateFin;
		this.dateDebutEncheres = dateDebut;
	}

	public ArticleVendu(int noArticle) {
		this();
		this.noArticle = noArticle;
	}

	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

	public List<Enchere> getListeEnchere() {
		return listeEnchere;
	}

	public void setListeEnchere(List<Enchere> listeEnchere) {
		this.listeEnchere = listeEnchere;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Retrait getPointRetrait() {
		return pointRetrait;
	}

	public void setPointRetrait(Retrait pointRetrait) {
		this.pointRetrait = pointRetrait;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}

	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

}
