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

import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

/**
 * Servlet implementation class ServletModificationProfil
 */
@WebServlet("/Encheres/ServletModificationCompte")
public class ServletModificationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationCompte.jsp");
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

		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");
		String newMdp = request.getParameter("newMdp");
		String confirmationNewMdp = request.getParameter("confirmationNewMdp");

		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
		utilisateur.setNoUtilisateur(utilisateurSession.getNoUtilisateur());
		utilisateur.setCredit(utilisateurSession.getCredit());
		
		try {
			switch (request.getParameter("modifier")) {
			case "Enregistrer":
				UtilisateurManager.getManager().updateUser(utilisateur, newMdp, confirmationNewMdp);

				
				session.setAttribute("utilisateur", utilisateur);

				rd = request.getRequestDispatcher("/WEB-INF/JSP/AffichageCompte.jsp");
				break;

			case "Supprimer mon compte":
				Utilisateur utilisateurASupprimer = (Utilisateur) request.getSession().getAttribute("utilisateur");
				UtilisateurManager.getManager().deleteUser(utilisateurASupprimer, mdp);
				request.getSession().invalidate();
				rd = request.getRequestDispatcher("/WEB-INF/JSP/PageAccueil.jsp");
				break;

			default:
				break;
			}
			;

		} catch (BusinessException e) {
			e.printStackTrace();

			List<Integer> listeErreur = e.getListeCodesErreur();
			request.setAttribute("listeErreur", listeErreur);

			rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationCompte.jsp");
		}

		rd.forward(request, response);
	}

}
