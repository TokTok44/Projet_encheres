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
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

/**
 * Servlet implementation class ServletPageConnexion
 */
@WebServlet("/Encheres/ServletPageConnexion")
public class ServletPageConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Categorie> listeCategorie = CategorieManager.getManager().selectAll();
		request.setAttribute("listeCategorie", listeCategorie);

		List<ArticleVendu> listeArticles = ArticleManager.getManager().selectArticle(0, "");
		request.setAttribute("listeArticles", listeArticles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Categorie> listeCategorie = CategorieManager.getManager().selectAll();
		request.setAttribute("listeCategorie", listeCategorie);

		List<ArticleVendu> listeArticles = ArticleManager.getManager().selectArticle(0, "");
		request.setAttribute("listeArticles", listeArticles);
		
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		RequestDispatcher rd = null;
		
		Utilisateur utilisateur;
		try {
			utilisateur = UtilisateurManager.getManager().selectConnexion(identifiant, mdp);
			if(utilisateur != null) {
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueilConnecter.jsp");
			}else {
				rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			List<Integer> listeErreur = e.getListeCodesErreur();
			request.setAttribute("listeErreur", listeErreur);
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");
		}

		rd.forward(request, response);
		
	}

}
