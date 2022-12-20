package fr.eni.encheres.bo;

import java.time.LocalDate;

public class Enchere {
	
	private LocalDate dateEnchere;
	private int montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu article;
	
	public Enchere() {}

	public Enchere(Utilisateur acheteur, ArticleVendu articleVendu, LocalDate dateEnchere, int montantEnchere) {
		this();
		this.utilisateur = acheteur;
		this.article = articleVendu;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public LocalDate getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}

}
