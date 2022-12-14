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
		<a href="">Enchères</a> <a
			href="${pageContext.request.contextPath }/Encheres/ServletVendre">Vendre
			un article</a> <a
			href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">Déconnexion</a>
	</header>
	<main>

		<core:if test="${!empty sessionScope.utilisateur }">
			<p>Pseudo : ${utilisateur.pseudo }</p>

			<p>Nom : ${utilisateur.nom }</p>

			<p>Prénom : ${utilisateur.prenom }</p>

			<p>Email : ${utilisateur.email }</p>

			<p>Téléphone : ${utilisateur.telephone }</p>

			<p>Rue : ${utilisateur.rue }</p>

			<p>Code postal : ${utilisateur.codePostal }</p>

			<p>Ville : ${utilisateur.ville }</p>

			<p>Crédit : ${utilisateur.credit }</p>

			<a class="modifier"
				href="${pageContext.request.contextPath }/Encheres/ServletModificationCompte">Modifier</a>
		</core:if>
		
		<a class="accueil"
			href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Accueil</a>


	</main>


</body>
</html>