<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon compte</title>
</head>
<body>
	<header>
		<p>ENI-Encheres</p>
		<jsp:include page="ListeErreur.jsp">
			<jsp:param value="" name="" />
		</jsp:include>
		<a href="">Enchères</a> <a
			href="${pageContext.request.contextPath }/Encheres/ServletNouvelleVente">Vendre
			un article</a> <a
			href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">Déconnexion</a>
	</header>
	<main>

		
			<p>Pseudo : ${requestScope.utilisateurRecherche.pseudo }</p>

			<p>Nom : ${requestScope.utilisateurRecherche.nom }</p>

			<p>Prénom : ${requestScope.utilisateurRecherche.prenom }</p>

			<p>Email : ${requestScope.utilisateurRecherche.email }</p>

			<p>Téléphone : ${requestScope.utilisateurRecherche.telephone }</p>

			<p>Rue : ${requestScope.utilisateurRecherche.rue }</p>

			<p>Code postal : ${requestScope.utilisateurRecherche.codePostal }</p>

			<p>Ville : ${requestScope.utilisateurRecherche.ville }</p>


		

		<a class="accueil"
			href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Accueil</a>


	</main>


</body>
</html>