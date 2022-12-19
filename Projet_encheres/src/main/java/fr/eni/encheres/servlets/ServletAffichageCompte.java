package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

/**
 * Servlet implementation class ServletAffichageCompte
 */
@WebServlet("/Encheres/ServletAffichageCompte")
public class ServletAffichageCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("utilisateur") != null) {
			
			Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
			int noUtilisateurCo = utilisateurConnecte.getNoUtilisateur();
			
			int noVendeur = Integer.parseInt(request.getParameter("noUtilisateur"));
			
			if (noUtilisateurCo == noVendeur) {
				rd = request.getRequestDispatcher("/WEB-INF/JSP/AffichageCompte.jsp");
			}else {
				
				try {
					Utilisateur utilisateurRecherche = UtilisateurManager.getManager().selectUser(noVendeur);
					request.setAttribute("utilisateurRecherche", utilisateurRecherche);
				} catch (BusinessException e) {
					e.printStackTrace();
				}
				
				
				rd = request.getRequestDispatcher("/WEB-INF/JSP/AffichageAutreCompte.jsp");
			}
			
			
		} else {
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");

		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
