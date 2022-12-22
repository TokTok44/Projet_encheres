package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.exception.BusinessException;

/**
 * Servlet implementation class ServletModificationVente
 */
@WebServlet("/Encheres/ServletModificationVente")
public class ServletModificationVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BusinessException be = new BusinessException();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		if(session.getAttribute("utilisateur") != null) {
			
			int noArticle = 0;
			try {
				noArticle = Integer.parseInt(request.getParameter("noArticle"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				be.ajouterErreur(CodesResultatServlets.ECHEC_CONVERSION_NOARTICLE);
			}
			
			if (request.getParameter("validation").equals("Enregistrer")) {
				
				String nomArticle = request.getParameter("nomArticle");
				String description = request.getParameter("description");
				String debutEncheres = request.getParameter("dateDebutEncheres");
				String finEncheres = request.getParameter("dateFinEncheres");
				String rue = request.getParameter("rue");
				String codePostal = request.getParameter("codePostal");
				String ville = request.getParameter("ville");
				int miseAPrix = 0;
				
				int noCategorie = 0;
				LocalDate dateDebutEncheres = null;
				LocalDate dateFinEncheres = null;
				
				try {
					miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					be.ajouterErreur(CodesResultatServlets.PRIX_NON_VALIDE);
				}
				try {
					noCategorie = Integer.parseInt(request.getParameter("choixCategorie"));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					be.ajouterErreur(CodesResultatServlets.ECHEC_CONVERSION_NOCATEGORIE);
				}
				try {
					dateDebutEncheres = LocalDate.parse(debutEncheres);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					be.ajouterErreur(CodesResultatServlets.FORMAT_DATEDEBUT_INCORRECT);
				}
				try {
					dateFinEncheres = LocalDate.parse(finEncheres);
				} catch (DateTimeParseException e) {
					e.printStackTrace();
					be.ajouterErreur(CodesResultatServlets.FORMAT_DATEFIN_INCORRECT);
				}
				
				if (be.hasErreurs()) {
					
					List<Integer> listeErreur = be.getListeCodesErreur();
					request.setAttribute("listeErreur", listeErreur);
					
					rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationVente.jsp");
					rd.forward(request, response);
				} else {
				
					Retrait retrait = new Retrait(rue,codePostal,ville);
					Categorie categorie = new Categorie(noCategorie);
					ArticleVendu article = new ArticleVendu(noArticle,nomArticle,description,miseAPrix,dateDebutEncheres,dateFinEncheres,retrait);
					article.setCategorie(categorie);
					try {
						
						ArticleManager.getManager().updateArticle(article);
						response.sendRedirect(request.getContextPath() + "/Encheres/ServletDetailArticle?noArticle=" + noArticle);
						
					} catch (BusinessException e) {
						e.printStackTrace();
						
						List<Integer> listeErreur = be.getListeCodesErreur();
						request.setAttribute("listeErreur", listeErreur);
						
						rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationVente.jsp");
						rd.forward(request, response);
					}
				}
				
				
			} else {
				
				ArticleManager.getManager().deleteArticle(noArticle);
				
				response.sendRedirect(request.getContextPath() + "/Encheres/ServletPageAccueil");

			}
			
		}else {
			
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");
			rd.forward(request, response);
		}
		
		
	}

}
