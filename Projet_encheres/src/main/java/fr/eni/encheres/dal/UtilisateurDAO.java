package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

public interface UtilisateurDAO {

	List<Utilisateur> selectAll();
	void insertUser (Utilisateur utilisateur) throws BusinessException;
	void updateUser (Utilisateur utilisateur) throws BusinessException;
	void deleteUser (int noUtilisateur);
	Utilisateur selectUser (int noUtilisateur);
	Utilisateur selectConnexion (String identifiant, String motDePasse);
}
