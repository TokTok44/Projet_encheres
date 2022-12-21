package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletListeEncheres
 */
@WebServlet("/Encheres/ServletListeEncheres")
public class ServletListeEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateur") != null) {

			/*
			 * Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			 * int noUtilisateur = utilisateur.getNoUtilisateur();
			 */

			rd = request.getRequestDispatcher("/WEB-INF/JSP/.jsp");
		} else {
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");

		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Categorie> listeCategorie = CategorieManager.getManager().selectAll();
		request.setAttribute("listeCategorie", listeCategorie);
		
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateur") != null) {

			Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
			int noUtilisateur = utilisateur.getNoUtilisateur();

			String recherche = request.getParameter("recherche");

			int choixCategorie = 0;
			try {
				choixCategorie = Integer.parseInt(request.getParameter("choixCategorie"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			boolean ventes = false;
			String achatsOuVente = request.getParameter("achatsOuVentes");

			boolean encheresOuvertes = false;
			boolean mesEncheresOuvertes = false;
			boolean mesEncheresTerminees = false;
			boolean ventesEnCours = false;
			boolean ventesAVenir = false;
			boolean ventesTerminees = false;

			if (achatsOuVente.equalsIgnoreCase("ventes")) {
				ventes = true;

				if (request.getParameter("ventesEnCours") != null) {
					ventesEnCours = true;
				}

				if (request.getParameter("ventesNonDebutees") != null) {
					ventesAVenir = true;
				}

				if (request.getParameter("ventesTerminees") != null) {
					ventesTerminees = true;
				}
			} else {

				if (request.getParameter("ouvert") != null) {
					encheresOuvertes = true;
				}

				if (request.getParameter("enCours") != null) {
					mesEncheresOuvertes = true;
				}

				if (request.getParameter("remportees") != null) {
					mesEncheresTerminees = true;
				}
			}

			List<ArticleVendu> listeArticlesVendus = ArticleManager.getManager().selectArticleFiltre(choixCategorie,
					noUtilisateur, recherche, ventes, ventesEnCours, ventesAVenir, ventesTerminees, encheresOuvertes,
					mesEncheresOuvertes, mesEncheresTerminees);
			
			request.setAttribute("listeArticlesVendus", listeArticlesVendus);

			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueilConnecter.jsp");
		} else {

			String recherche = request.getParameter("recherche");

			int choixCategorie = 0;
			try {
				choixCategorie = Integer.parseInt(request.getParameter("choixCategorie"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}

			List<ArticleVendu> listeArticles = ArticleManager.getManager().selectAllArticle(choixCategorie,recherche);

			request.setAttribute("listeArticles", listeArticles);
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueil.jsp");

		}
		rd.forward(request, response);
	}

}
