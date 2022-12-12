package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {

	List<Utilisateur> selectAll();
	void insert_user (Utilisateur utilisateur);
	void update_user (Utilisateur utilisateur);
	void delete_user (Utilisateur utilisateur);
}
