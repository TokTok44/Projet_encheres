package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	List<Utilisateur> selectAll();
	void insertUser (Utilisateur utilisateur);
	void updateUser (Utilisateur utilisateur);
	void deleteUser (int noUtilisateur);
	Utilisateur selectUser (int noUtilisateur);
	Utilisateur selectConnexion (String identifiant, String motDePasse);
}
