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
import fr.eni.encheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie VALUES (?,?,?,?,?,?,?,?);";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS no_article, rue, code_postal, ville VALUES (?,?,?,?);";
	private static String selectArticleCategorie = "SELECT no_article, nom_article, date_fin_encheres, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";
	private static String selectArticleConnecte = "SELECT no_article, nom_article, prix_vente, pseudo, ";
	
	@Override
	public ArticleVendu insertArticle(ArticleVendu article) {
	
		try(Connection cnx = ConnectionProvider.getConnection()){
	            
            PreparedStatement pstmtArticle = cnx.prepareStatement(INSERT_ARTICLE, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmtRetrait = cnx.prepareStatement(INSERT_RETRAIT);
            pstmtArticle.setString(1, article.getNomArticle());
            pstmtArticle.setString(2, article.getDescription());
            pstmtArticle.setDate(3, Date.valueOf(article.getDateDebutEncheres()));
            pstmtArticle.setDate(4, Date.valueOf(article.getDateFinEncheres()));
            pstmtArticle.setInt(5, article.getMiseAPrix());
            pstmtArticle.setInt(6, article.getMiseAPrix());
            pstmtArticle.setInt(7, article.getVendeur().getNoUtilisateur());
            pstmtArticle.setInt(8, article.getCategorie().getNoCategorie());
            pstmtArticle.executeUpdate();
            ResultSet rs = pstmtArticle.getGeneratedKeys();
            if(rs.next()) {
                pstmtRetrait.setInt(1, rs.getInt(1));
                pstmtRetrait.setString(2, article.getPointRetrait().getRue());
                pstmtRetrait.setString(3, article.getPointRetrait().getCodePostal());
                pstmtRetrait.setString(4, article.getPointRetrait().getVille());
                
                article.setNoArticle(rs.getInt(1));
            }

	    }catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		return article;
		
	}
	
	@Override
	public List<ArticleVendu> selectArticle(String condition, int noCategorie, String recherche){
		
		List<ArticleVendu> listeArticle = new ArrayList<>();
		
		selectArticleCategorie += condition;
		
		ResultSet rs = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			if(condition.equals(";")) {
				Statement stmt = cnx.createStatement();
				rs = stmt.executeQuery(selectArticleCategorie);
			}else if(condition.contains("no_categorie") && condition.contains("nom_article")){
				PreparedStatement pstmt = cnx.prepareStatement(selectArticleCategorie);
				pstmt.setInt(1, noCategorie);
				pstmt.setString(2, recherche);
				rs = pstmt.executeQuery();
			}else if(condition.contains("no_categorie")) {
				PreparedStatement pstmt = cnx.prepareStatement(selectArticleCategorie);
				pstmt.setInt(1, noCategorie);
				rs = pstmt.executeQuery();
			}else if(condition.contains("nom_article")){
				PreparedStatement pstmt = cnx.prepareStatement(selectArticleCategorie);
				pstmt.setString(1, recherche);
				rs = pstmt.executeQuery();
			}
			
			Utilisateur vendeur = null;
			int noArticle;
			String pseudo;
			String nomArticle;
			LocalDate dateFin;
			int prixVente;
			
			while(rs.next()) {
				vendeur = new Utilisateur(rs.getString("pseudo"));
				noArticle = rs.getInt("no_article");
				pseudo = rs.getString("pseudo");
				nomArticle = rs.getString("nom_article");
				dateFin = rs.getDate("date_fin_encheres").toLocalDate();
				prixVente = rs.getInt("prix_vente");
				
				listeArticle.add(new ArticleVendu(noArticle,nomArticle,prixVente,dateFin,vendeur));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listeArticle;
		
	}

	
	@Override
	public List<ArticleVendu> selectArticlesConnecte(String condition, int noCategorie, String recherche) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
