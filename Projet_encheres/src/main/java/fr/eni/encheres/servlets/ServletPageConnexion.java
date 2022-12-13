package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

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
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");
		RequestDispatcher rd = null;
		
		Utilisateur utilisateur = UtilisateurManager.getManager().selectConnexion(identifiant, mdp);
		
		if(utilisateur != null) {
			
			request.setAttribute("pseudo", utilisateur.getPseudo());
			
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueilConnecter.jsp");
			
		}else {
			
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageConnexion.jsp");
			
		}
		
		
		rd.forward(request, response);
		
	}

}
