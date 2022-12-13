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
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/Encheres/ServletCreationCompte")
public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationCompte.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mdp = request.getParameter("mdp");
		RequestDispatcher rd = null;
		
		if(mdp.equals(request.getParameter("confirmation"))) {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String telephone = null;
			if(request.getParameter("telephone") != null) {
				telephone = request.getParameter("telephone");
			}
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			
			Utilisateur utilisateur = new Utilisateur(pseudo,nom,prenom,email,telephone,rue,codePostal,ville,mdp);
			
			UtilisateurManager.getManager().insertUser(utilisateur);
			
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueilConnecter.jsp");
			
		}else {
			
			rd = request.getRequestDispatcher("/WEB-INF/JSP/CreationCompte.jsp");
		
		}
		
		rd.forward(request, response);
		
	}

}
