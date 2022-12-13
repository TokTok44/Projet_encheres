package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ? WHERE no_utilisateur = ?;";
	private static final String SELECT_USER = "SELECT pseudo, nom, prenom, email, telephone, rue, code_postal, ville FROM UTILISATEURS WHERE no_utilisateur = ?;";
	private static final String SELECT_CONNEXION = "SELECT pseudo, email, mot_de_passe,no_utilisateur FROM UTILISATEURS WHERE ((pseudo = ? OR email = ?) AND mot_de_passe = ?);";
	private static final String DELETE_USER = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?;";

	@Override
	public List<Utilisateur> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(Utilisateur utilisateur) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, 200);
				pstmt.setByte(11, (byte) 0);

				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if(rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}

				cnx.commit();
				
			} catch (Exception e) {
				cnx.rollback();
				e.printStackTrace();
				BusinessException be = new BusinessException();
				
				if(e.getMessage().contains("pseudo_UNIQUE")) {
					be.ajouterErreur(CodesResultatDAL.PSEUDO_UNIQUE);
				}
				if(e.getMessage().contains("email_UNIQUE")) {
					be.ajouterErreur(CodesResultatDAL.EMAIL_UNIQUE);
				}
				be.ajouterErreur(CodesResultatDAL.ECHEC_INSERTION);
				
				throw be;

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void updateUser(Utilisateur utilisateur) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);
				pstmt.setString(1, utilisateur.getPseudo());
				pstmt.setString(2, utilisateur.getNom());
				pstmt.setString(3, utilisateur.getPrenom());
				pstmt.setString(4, utilisateur.getEmail());
				pstmt.setString(5, utilisateur.getTelephone());
				pstmt.setString(6, utilisateur.getRue());
				pstmt.setString(7, utilisateur.getCodePostal());
				pstmt.setString(8, utilisateur.getVille());
				pstmt.setString(9, utilisateur.getMotDePasse());
				pstmt.setInt(10, utilisateur.getNoUtilisateur());

				int nbUpdate = pstmt.executeUpdate();

				if (nbUpdate <= 1) {
					cnx.commit();
				} else {// TODO exception
					cnx.rollback();
				}

			} catch (Exception e) {
				cnx.rollback();
				e.printStackTrace();
				BusinessException be = new BusinessException();
				
				if(e.getMessage().contains("pseudo_UNIQUE")) {
					be.ajouterErreur(CodesResultatDAL.PSEUDO_UNIQUE);
				}
				if(e.getMessage().contains("email_UNIQUE")) {
					be.ajouterErreur(CodesResultatDAL.EMAIL_UNIQUE);
				}
				
				be.ajouterErreur(CodesResultatDAL.ECHEC_UPDATE);
				throw be;
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(int noUtilisateur) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
				pstmt.setInt(1, noUtilisateur);
				int nbDelete = pstmt.executeUpdate();
				
				if (nbDelete <= 1) {
					cnx.commit();
				} else {
					cnx.rollback();
					
				}
			} catch (Exception e) {
				cnx.rollback();
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Utilisateur selectUser(int noUtilisateur) {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER);
			pstmt.setInt(1, noUtilisateur);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");

				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return utilisateur;
	}

	@Override
	public Utilisateur selectConnexion(String identifiant, String motDePasse) {
		Utilisateur utilisateur = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SELECT_CONNEXION);
			pstmt.setString(1, identifiant);
			pstmt.setString(2, identifiant);
			pstmt.setString(3, motDePasse);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String pseudo = rs.getString("pseudo");

				utilisateur = new Utilisateur(pseudo);
				utilisateur.setNoUtilisateur(rs.getInt("noUtilisateur"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return utilisateur;
	}

}
