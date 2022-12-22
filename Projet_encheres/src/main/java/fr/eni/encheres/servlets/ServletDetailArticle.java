package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

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
			
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
			ArticleVendu articleRecherche = ArticleManager.getManager().selectArticle(noArticle);
			boolean ouverte = articleRecherche.getDateDebutEncheres().isBefore(LocalDate.now().plusDays(1));
			articleRecherche.setNoArticle(noArticle);
			request.setAttribute("articleRecherche", articleRecherche);
			
			if (utilisateurConnecte.getPseudo().equals(articleRecherche.getVendeur().getPseudo()) && !ouverte) {
				
				List<Categorie> listeCategorie = CategorieManager.getManager().selectAll();
				request.setAttribute("listeCategorie", listeCategorie);
				
				rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationVente.jsp");
				
			} else if(articleRecherche.getDateFinEncheres().isBefore(LocalDate.now())){
				
				rd = request.getRequestDispatcher("/WEB-INF/JSP/FinEnchere.jsp");
				
			}else {
				request.setAttribute("pseudoVendeur", articleRecherche.getVendeur().getPseudo());
				request.setAttribute("ouverte", ouverte);

				rd = request.getRequestDispatcher("/WEB-INF/JSP/DetailArticle.jsp");
			}
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
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		
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
		
		ArticleVendu article = ArticleManager.getManager().selectArticle(noArticle);
		article.setNoArticle(noArticle);
		Enchere enchere = new Enchere(utilisateurConnecte,article,LocalDate.now(),valeurEnchere);
		
		try {
			enchere = EnchereManager.getManager().insertEnchere(enchere);
		} catch (BusinessException e) {
			e.printStackTrace();
			
			
		}
		
		response.sendRedirect(request.getContextPath() + "/Encheres/ServletDetailArticle?noArticle=" + noArticle);
	}

}
