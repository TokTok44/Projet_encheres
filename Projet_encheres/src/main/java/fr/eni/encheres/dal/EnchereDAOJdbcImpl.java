package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements EnchereDAO{

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,?,?);";
	private static final String SELECT_ALL = "SELECT * FROM ENCHERES;";
	private static final String SELECT_BY_ID = "SELECT * FROM ENCHERES WHERE no_enchere = ?;";
	
	
	@Override
	public Enchere insertEnchere(Enchere enchere) {
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			try {
				
				PreparedStatement pstmtEnchere = cnx.prepareStatement(INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS);
				pstmtEnchere.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
				pstmtEnchere.setInt(2, enchere.getArticle().getNoArticle());
				pstmtEnchere.setDate(3, Date.valueOf(enchere.getDateEnchere()));
				pstmtEnchere.setInt(4, enchere.getMontantEnchere());
				pstmtEnchere.executeUpdate();
				
								
			} catch (Exception e) {
				cnx.rollback();
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return enchere;
	}

	public List<Enchere> selectAllEnchere(){
		
		List<Enchere> listeAllEnchere = new ArrayList<>();
		ResultSet rs = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			Statement stmt = cnx.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			
			Utilisateur acheteur = null;
			ArticleVendu articleVendu = null;
			LocalDate dateEnchere;
			int montantEnchere;
			
			while (rs.next()) {
				acheteur = new Utilisateur(rs.getInt("no_utilisateur"));
				articleVendu = new ArticleVendu(rs.getInt("no_article"));
				
				dateEnchere = rs.getDate("dateEnchere").toLocalDate();
				montantEnchere = rs.getInt("montantEnchere");
				
				listeAllEnchere.add(new Enchere(acheteur,articleVendu,dateEnchere,montantEnchere));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listeAllEnchere;
	}
	
	public Enchere selectByNoArticle(int noArticle) {
		
		Enchere enchere = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, enchere.getArticle().getNoArticle());
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				enchere = new Enchere();
				//pas encore fini
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return enchere;
	}
}
