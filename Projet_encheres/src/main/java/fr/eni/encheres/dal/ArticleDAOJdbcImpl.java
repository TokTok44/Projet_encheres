package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.eni.encheres.bo.ArticleVendu;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie VALUES (?,?,?,?,?,?,?);";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS no_article, rue, code_postal, ville VALUES (?,?,?,?);";
	
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
            pstmtArticle.setInt(6, article.getAcheteur().getNoUtilisateur());
            pstmtArticle.setInt(7, article.getCategorie().getNoCategorie());
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

	
	
}
