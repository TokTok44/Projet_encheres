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
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;

public class ArticleDAOJdbcImpl implements ArticleDAO {
	
	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?,?,?,?);";
	
	
	private static String selectArticleCategorie = "SELECT UTILISATEURS.no_utilisateur,no_article, nom_article, date_fin_encheres, prix_vente, pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";
	
	// Les différents morceaux pour la requete de selection des articles 
	//en fonction des filtres sélctionnes
	
	private static final String COMMUN_ACHATS_VENTES = "SELECT UTILISATEURS.no_utilisateur,ENCHERES.no_article,nom_article,date_fin_encheres,prix_vente,pseudo FROM ARTICLES_VENDUS INNER JOIN UTILISATEURS ON ARTICLES_VENDUS.no_utilisateur = UTILISATEURS.no_utilisateur";
	
	// Les ventes de l'utilisateur
	
	private static final String VENTES_UTILISATEUR = " WHERE (UTILISATEURS.no_utilisateur = ?)";
	private static final String VENTES_UTILISATEUR_ENCOURS = " AND (DATEDIFF(day,date_debut_encheres,getDate()) > 0) AND (DATEDIFF(day,getDate(),date_fin_encheres) > 0)";
	private static final String VENTES_A_VENIR = " AND (DATEDIFF(day,getDate(),date_debut_encheres) > 0)";
	private static final String VENTES_TERMINEES = " AND (DATEDIFF(day,date_fin_encheres,getDate()) > 0)";
	private static final String VENTES_ENCOURS_ET_A_VENIR = " AND (DATEDIFF(day,date_fin_encheres,getDate()) < 0)";
	private static final String VENTES_ENCOURS_ET_TERMINEES = " AND (DATEDIFF(day,getDate(),date_debut_encheres) < 0)";
	private static final String VENTES_UTILISATEUR_PAS_ENCOURS = " AND ((DATEDIFF(day,date_debut_encheres,getDate()) < 0) OR (DATEDIFF(day,getDate(),date_fin_encheres) < 0))";
	
	// Les achats de l'utilisateur
	
	private static final String COMMUN_ACHATS = "INNER JOIN ENCHERES ON (ARTICLES_VENDUS.no_article = ENCHERES.no_article AND ARTICLES_VENDUS.prix_vente = ENCHERES.montant_enchere)";
	
	private static final String MES_ENCHERES_EN_COURS = "WHERE (ENCHERES.no_utilisateur = ? AND (DATEDIFF(day,date_debut_encheres,getDate()) > 0) AND  (DATEDIFF(day,getDate(),date_fin_encheres) > 0))";
	private static final String MES_ENCHERES_FINIES = "WHERE (ENCHERES.no_utilisateur = ? AND (DATEDIFF(day,date_fin_encheres,getDate()) > 0)";
	private static final String ENCHERES_OUVERTES_ET_MES_ENCHERES_REMPORTEES = "WHERE (((ENCHERES.no_utilisateur = ? AND (DATEDIFF(day,date_fin_encheres,getDate()) > 0)) OR (DATEDIFF(day,date_fin_encheres,getDate()) < 0)))";
	private static final String MES_ENCHERES_OUVERTES_ET_MES_ENCHERES_REMPORTEES = "WHERE ((ENCHERES.no_utilisateur = ?) AND ((DATEDIFF(day,date_fin_encheres,getDate()) > 0) OR (DATEDIFF(day,date_fin_encheres,getDate()) < 0))";
	
	
	private static final String SELECT_ARTICLE_EN_VENTE = "SELECT nom_article, description, libelle, prix_vente, ENCHERES.pseudo, prix_initial, date_fin_encheres, rue, code_postal, ville, ARTICLES_VENDUS.pseudo FROM ARTICLES_VENDUS INNER JOIN ENCHERES ON (ARTICLES_VENDUS.prix_vente = ENCHERES.montant_enchere AND ARTICLES_VENDUS.no_article = ENCHERES.no_article) INNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ENCHERES.no_utilisateur INNER JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article WHERE ARTICLES_VENDUS.no_article = ?;";
	private static final String SELECT_ARTICLE_SANS_ENCHERES = "SELECT nom_article, description, libelle, prix_vente, prix_initial, date_fin_encheres, rue, code_postal, ville, pseudo FROM ARTICLES_VENDUS INNER JOIN RETRAITS ON ARTICLES_VENDUS.no_article = RETRAITS.no_article WHERE ARTICLES_VENDUS.no_article = ?";
	
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, no_categorie = ? WHERE no_article = ?;";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_article = ?;";
	
	private static final String SELECT_PRIX_VENTE = "SELECT no_article, MAX(montant_enchere) as prix_vente_actuel INTO #TEMP_1 FROM ENCHERES GROUP BY no_article;";
	
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
	public List<ArticleVendu> selectAllArticle(String condition, int noCategorie, String recherche){
		
		List<ArticleVendu> listeArticle = new ArrayList<>();
		
		selectArticleCategorie += condition;
		
		ResultSet rs = null;
		
		recherche = "%" + recherche + "%";
		
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
			String nomArticle;
			LocalDate dateFin;
			int prixVente;
			
			while(rs.next()) {
				vendeur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"));
				noArticle = rs.getInt("no_article");

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
	public List<ArticleVendu> selectArticlesFiltre(String condition, int noCategorie, int noUtilisateur, String recherche,boolean ventes, boolean ventesEnCours,
			boolean ventesAVenir, boolean ventesTerminees, boolean encheresOuvertes, boolean mesEncheresOuvertes,
			boolean mesEncheresTerminees) {
		List<ArticleVendu> listeArticle = new ArrayList<>();
		
		String requete = "";
		
		if(ventes) {
			requete += COMMUN_ACHATS_VENTES + VENTES_UTILISATEUR;
		}else {
			requete += COMMUN_ACHATS_VENTES + COMMUN_ACHATS;
		}
		
		// Mes Ventes
		if(ventes && (ventesAVenir && ventesEnCours && !ventesTerminees)) {
			requete += VENTES_ENCOURS_ET_A_VENIR;
		}
		if(ventes && (ventesAVenir && !ventesEnCours && ventesTerminees)) {
			requete += VENTES_UTILISATEUR_PAS_ENCOURS;
		}
		if(ventes && (!ventesAVenir && ventesEnCours && ventesTerminees)) {
			requete += VENTES_ENCOURS_ET_TERMINEES;
		}
		if(ventes && (ventesAVenir && !ventesEnCours && !ventesTerminees)) {
			requete += VENTES_A_VENIR;
		}
		if(ventes && (!ventesAVenir && !ventesEnCours && ventesTerminees)) {
			requete += VENTES_TERMINEES;
		}
		if(ventes && (!ventesAVenir && ventesEnCours && !ventesTerminees)) {
			requete += VENTES_UTILISATEUR_ENCOURS;
		}
		
		// Mes Achats
		if(!ventes && (encheresOuvertes && !mesEncheresOuvertes && mesEncheresTerminees)) {
			requete += ENCHERES_OUVERTES_ET_MES_ENCHERES_REMPORTEES;
		}
		if(!ventes && (!encheresOuvertes && mesEncheresOuvertes && mesEncheresTerminees)) {
			requete += MES_ENCHERES_OUVERTES_ET_MES_ENCHERES_REMPORTEES;
		}
		if(!ventes && (!encheresOuvertes && !mesEncheresOuvertes && mesEncheresTerminees)) {
			requete += MES_ENCHERES_FINIES;
		}
		if(!ventes && (!encheresOuvertes && mesEncheresOuvertes && !mesEncheresTerminees)) {
			requete += MES_ENCHERES_EN_COURS;
		}
		
		requete += condition;
	
		requete += ";";
		
		recherche = "%" + recherche + "%";
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			ResultSet rs = null;
			
			if(noCategorie != 0 && !(recherche.isBlank())) {
				if((!ventes && (encheresOuvertes && mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (!encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees))) {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setInt(1, noCategorie);
					pstmt.setString(2, recherche);
					rs = pstmt.executeQuery();
				}else {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setInt(1, noUtilisateur);
					pstmt.setInt(2, noCategorie);
					pstmt.setString(3, recherche);
					rs = pstmt.executeQuery();
				}
			}else if(noCategorie != 0) {
				if((!ventes && (encheresOuvertes && mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (!encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees))) {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setInt(1, noCategorie);
					rs = pstmt.executeQuery();
				}else {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setInt(1, noUtilisateur);
					pstmt.setInt(2, noCategorie);
					rs = pstmt.executeQuery();
				}
			}else if(!(condition.isBlank())) {
				if((!ventes && (encheresOuvertes && mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees) || (!ventes && (!encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees)))) {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setString(1, recherche);
					rs = pstmt.executeQuery();
				}else {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setInt(1, noUtilisateur);
					pstmt.setString(2, recherche);
					rs = pstmt.executeQuery();
				}
			}else {
				if((!ventes && (encheresOuvertes && mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees)) || (!ventes && (!encheresOuvertes && !mesEncheresOuvertes && !mesEncheresTerminees))) {
					Statement stmt = cnx.createStatement();
					rs = stmt.executeQuery(requete);
				}else {
					PreparedStatement pstmt = cnx.prepareStatement(requete);
					pstmt.setInt(1, noUtilisateur);
					rs = pstmt.executeQuery();
				}
			}
			
			while(rs.next()) {
				
				Utilisateur utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),rs.getString("pseudo"));
				ArticleVendu article = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"),rs.getInt("prix_vente"),rs.getDate("date_fin_enchere").toLocalDate(),utilisateur);
				listeArticle.add(article);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return listeArticle;
	}

	@Override
	public ArticleVendu selectArticle(int noArticle, boolean debute) {
		ArticleVendu article = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = null;
			if(debute) {
				pstmt = cnx.prepareStatement(SELECT_ARTICLE_EN_VENTE);
			}else {
				pstmt = cnx.prepareStatement(SELECT_ARTICLE_SANS_ENCHERES);
			}
			pstmt.setInt(1, noArticle);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				Retrait pointRetrait = new Retrait(rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"));
				Categorie categorie = new Categorie(rs.getString("libelle"));
				LocalDate dateFin = rs.getDate("date_fin_encheres").toLocalDate();
				article = new ArticleVendu(rs.getString("nom_article"),rs.getString("description"),rs.getInt("prix_vente"),rs.getInt("prix_initial"),dateFin);
				Utilisateur vendeur = null;
				Utilisateur acheteur = null;
				if(debute) {
					acheteur = new Utilisateur(rs.getString(5));
					vendeur = new Utilisateur(rs.getString(11));
					article.setVendeur(vendeur);
					article.setAcheteur(acheteur);
				}else {
					vendeur = new Utilisateur(rs.getString(10));
					article.setVendeur(vendeur);
				}
				
				article.setPointRetrait(pointRetrait);
				article.setCategorie(categorie);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return article;
	}

	public ArticleVendu updateArticle(ArticleVendu article) {
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmtArticle = cnx.prepareStatement(UPDATE_ARTICLE);
				PreparedStatement pstmtRetrait = cnx.prepareStatement(UPDATE_RETRAIT);
				
				pstmtArticle.setString(1, );
				pstmtArticle.setString(2, );
				pstmtArticle.setDate(3, );
				pstmtArticle.setDate(4, );
				pstmtArticle.setString(5, );
				pstmtArticle.setString(6, );
				pstmtArticle.setString(7, );
				
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
