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

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/Encheres/ServletDetailArticle")
public class ServletDetailArticle extends HttpServlet {
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

			int noArticle = 0;
			try {
				noArticle = Integer.parseInt(request.getParameter("noArticle"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				// TODO ajouter une erreur be ?
			}

			ArticleVendu articleRecherche = ArticleManager.getManager().selectArticle(noArticle);
			request.setAttribute("articleRecherche", articleRecherche);

			rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailArticle.jsp");
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
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		int noUtilisateur = (int) session.getAttribute("noUtilisateur");
		
		int noArticle = 0;
		try {
			noArticle = Integer.parseInt(request.getParameter("noArticle"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		int valeurEnchere = 0;
		try {
			valeurEnchere = Integer.parseInt(request.getParameter("valeurEnchere"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		ArticleVendu articleRecherche = ArticleManager.getManager().updateArticle(noArticle,noUtilisateur,valeurEnchere);
		request.setAttribute("articleRecherche", articleRecherche);
		
		doGet(request, response);
	}

}
