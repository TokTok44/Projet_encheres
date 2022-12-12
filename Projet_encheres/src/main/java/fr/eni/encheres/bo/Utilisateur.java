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
	
	
	

}
