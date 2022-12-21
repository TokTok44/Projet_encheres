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
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()){
			try {
				cnx.setAutoCommit(false);
	//Récupère l'utilisateur ayant fait l'enchère la plus élevée
				String sql = "SELECT U.no_utilisateur, U.credit FROM UTILISATEURS U INNER JOIN ENCHERES E ON E.no_utilisateur = U.no_utilisateur WHERE E.no_article = ? AND E.montant_enchere = (SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = ?);";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setInt(1, enchere.getArticle().getNoArticle());
				pstmt.setInt(2, enchere.getArticle().getNoArticle());
				rs = pstmt.executeQuery();
				
				int noAcheteur = -1;
				int credit = 0;
				if (rs.next()) {
					noAcheteur = rs.getInt("no_utilisateur");
					credit = rs.getInt("credit");
				}
	
	//Insère une nouvelle enchère dans la table ENCHERES
				
				pstmt = cnx.prepareStatement(INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, enchere.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(2, enchere.getArticle().getNoArticle());
				pstmt.setDate(3, Date.valueOf(enchere.getDateEnchere()));
				pstmt.setInt(4, enchere.getMontantEnchere());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				
	//Met à jour le prix de vente de l'article dans la table ARTICLES_VENDUS
				
				sql = "UPDATE ARTICLES_VENDUS SET prix_vente = ? WHERE no_article = ?;";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setInt(1, enchere.getMontantEnchere());
				pstmt.setInt(2, enchere.getArticle().getNoArticle());
				pstmt.executeUpdate();
				
	//Met à jour les crédits de l'utilisateur ayant fait l'enchère la plus élevée
				
				sql = "UPDATE UTILISATEURS SET credit = credit - ? WHERE no_utilisateur = ?;";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setInt(1, enchere.getMontantEnchere());
				pstmt.setInt(2, enchere.getUtilisateur().getNoUtilisateur());
				
	//Recréditer l'ancien enchérisseur de sa précédente enchère
				
				sql = "UPDATE UTILISATEURS SET credit = credit + ? WHERE no_utilisateur = ?;";
				pstmt = cnx.prepareStatement(sql);
				pstmt.setInt(1, credit);
				pstmt.setInt(2, noAcheteur);
				
				cnx.commit();				
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
