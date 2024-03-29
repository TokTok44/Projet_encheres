package fr.eni.encheres.bo;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
	
	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private int credit;
	private boolean administrateur;
	private List<ArticleVendu> achete;
	private List<ArticleVendu> vend;
	private List<Enchere> encherit;
	
	public Utilisateur() {
		this.achete = new ArrayList<>();
		this.vend = new ArrayList<>();
		this.encherit = new ArrayList<>();
	}
	
	public Utilisateur(String pseudo) {
		this();
		this.pseudo = pseudo;

	}
	
	public Utilisateur(int noUtilisateur, String pseudo) {
		this();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
	}
	
	public Utilisateur(String pseudo, String email, String motDePasse) {
		this();
		this.pseudo = pseudo;
		this.email = email;
		this.motDePasse = motDePasse;

	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville) {
		this();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp) {
		this();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = mdp;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp, int credit) {
		this();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = mdp;
		this.credit = credit;
	}
	
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp, int credit, boolean administrateur) {
		this();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = mdp;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public Utilisateur(int noUtilisateur) {
		this();
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	public List<ArticleVendu> getAchete() {
		return achete;
	}

	public void setAchete(List<ArticleVendu> achete) {
		this.achete = achete;
	}

	public List<ArticleVendu> getVend() {
		return vend;
	}

	public void setVend(List<ArticleVendu> vend) {
		this.vend = vend;
	}

	public List<Enchere> getEncherit() {
		return encherit;
	}

	public void setEncherit(List<Enchere> encherit) {
		this.encherit = encherit;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [noUtilisateur=").append(noUtilisateur).append(", pseudo=").append(pseudo)
				.append(", nom=").append(nom).append(", prenom=").append(prenom).append(", email=").append(email)
				.append(", telephone=").append(telephone).append(", rue=").append(rue).append(", codePostal=")
				.append(codePostal).append(", ville=").append(ville).append(", motDePasse=").append(motDePasse)
				.append(", credit=").append(credit).append(", administrateur=").append(administrateur)
				.append(", achete=").append(achete).append(", vend=").append(vend).append(", encherit=")
				.append(encherit).append("]");
		return builder.toString();
	}
	
	

}
