package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

public interface UtilisateurDAO {

	List<Utilisateur> selectAll();
	Utilisateur insertUser (Utilisateur utilisateur) throws BusinessException;
	void updateUser (Utilisateur utilisateur) throws BusinessException;
	void deleteUser (int noUtilisateur) throws BusinessException;
	Utilisateur selectConnexion (String identifiant, String motDePasse) throws BusinessException;
	Utilisateur selectUser(int noUtilisateur) throws BusinessException;
}
