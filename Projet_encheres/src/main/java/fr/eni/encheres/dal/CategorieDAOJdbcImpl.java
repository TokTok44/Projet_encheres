package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.exception.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String INSERT_CATEGORIE = "INSERT INTO CATEGORIES libelle VALUES (?);";
	private static final String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES;";

	@Override
	public void insertCategorie(Categorie categorie) {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_CATEGORIE);

				pstmt.setString(1, categorie.getLibelle());
				
				pstmt.executeUpdate();
				cnx.commit();
			}catch (Exception e){
				cnx.rollback();
				e.printStackTrace();
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.ECHEC_INSERTION_CATEGORIE);
				throw be;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Categorie> selectAll() {
		List<Categorie> listeCategorie = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			
			while(rs.next()) {
				
				int noCategorie = rs.getInt("no_categorie");
				String libelle = rs.getString("libelle");
				
				listeCategorie.add(new Categorie(noCategorie,libelle));
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listeCategorie;
	}
	
	

}
