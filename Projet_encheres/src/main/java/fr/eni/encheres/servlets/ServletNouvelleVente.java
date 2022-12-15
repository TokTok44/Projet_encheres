package fr.eni.encheres.servlets;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.jni.Local;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exception.BusinessException;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet("/Encheres/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Categorie> listeCategorie = CategorieManager.getManager().selectAll();
		request.setAttribute("listeCategorie", listeCategorie);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/NouvelleVente.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = null;
		BusinessException be = new BusinessException();
		
		HttpSession session = request.getSession();
		Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("noUtilisateur");
		Utilisateur vendeur = new Utilisateur(utilisateurSession.getNoUtilisateur());
		
		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		String choixCategorie = request.getParameter("choixCategorie");
		int miseAPrix = 0;
		try {
			miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatServlets.PRIX_NON_VALIDE);
		}
		LocalDate dateDebut = null;
		try {
			dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
		} catch (DateTimeException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatServlets.FORMAT_DATEDEBUT_INCORRECT);
		}
		LocalDate dateFin = null;
		try {
			dateFin = LocalDate.parse(request.getParameter("dateFin"));
		} catch (DateTimeException e) {
			e.printStackTrace();
			be.ajouterErreur(CodesResultatServlets.FORMAT_DATEFIN_INCORRECT);
		}
		
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		Categorie categorie = new Categorie(choixCategorie);
		Retrait retrait = new Retrait(rue,codePostal,ville);
		ArticleVendu articleVendu = new ArticleVendu(vendeur,nomArticle,description,dateDebut,dateFin,miseAPrix,categorie,retrait);
		
		
		if (be.hasErreurs()) {
			request.setAttribute("listeCodesErreur", be.getListeCodesErreur());
			
			rd = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
			rd.forward(request, response);
		}else {
			try {
				ArticleManager.getManager().insertArticle(articleVendu);
				
				rd = request.getRequestDispatcher("/WEB-INF/PageAccueilConnecter.jsp");
				rd.forward(request, response);
				
			} catch (BusinessException e) {
				e.printStackTrace();
				
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				
				rd = request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp");
			}
		}
		
	}

}
