package fr.eni.encheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

/**
 * Servlet implementation class ServletSuppressionCompte
 */
@WebServlet("/Encheres/ServletSuppressionCompte")
public class ServletSuppressionCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = null;

		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String mdp = request.getParameter("mdp");
		try {
			UtilisateurManager.getManager().deleteUser(utilisateur, mdp);
			request.getSession().invalidate();
			rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueil.jsp");

		} catch (BusinessException e) {
			e.printStackTrace();
			
			List<Integer> listeErreur = e.getListeCodesErreur();
			request.setAttribute("listeErreur", listeErreur);
			
			rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationCompte.jsp");
		}
		

	}

}
