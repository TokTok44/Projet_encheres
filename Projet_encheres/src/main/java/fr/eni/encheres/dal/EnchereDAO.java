package fr.eni.encheres.dal;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.exception.BusinessException;

public interface EnchereDAO {
	
	public Enchere insertEnchere(Enchere enchere) throws BusinessException;
}
