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
 * Servlet implementation class ServletPageAccueil
 */
@WebServlet("/Encheres/ServletPageAccueil")
public class ServletPageAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = null;
		List<Categorie> listeCategorie = CategorieManager.getManager().selectAll();
		request.setAttribute("listeCategorie", listeCategorie);

		List<ArticleVendu> listeArticles = ArticleManager.getManager().selectAllArticle(0, "");
		request.setAttribute("listeArticles", listeArticles);
		
		HttpSession session = request.getSession();
		
		if (session.getAttribute("utilisateur") != null) {
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueilConnecter.jsp");
		} else {
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueil.jsp");

		}
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
