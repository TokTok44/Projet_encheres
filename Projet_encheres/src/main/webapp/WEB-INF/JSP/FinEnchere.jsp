<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Fin des enchères</title>
	</head>
	<body>
		<header>
			
		</header>
		<main>
			
			<core:choose>
				<core:when test="${sessionScope.utilisateur.pseudo.equals(requestScope.articleRecherche.acheteur.pseudo) }">
					<h1>Vous avez remporté la vente</h1>
				</core:when>
				<core:otherwise>
					<h1>${requestScope.articleRecherche.acheteur.pseudo } a remporté l'enchère</h1>
				</core:otherwise>
			</core:choose>
		
			<p>${requestScope.articleRecherche.nomArticle }</p>
			<p>Description : ${requestScope.articleRecherche.description }</p>
			<p>Meilleure offre : ${requestScope.articleRecherche.prixVente } pts </p>
			<core:if test="${!sessionScope.utilisateur.pseudo.equals(requestScope.articleRecherche.acheteur.pseudo) }">
				<p>par </p>
				<a href="${pageContext.request.contextPath }/Encheres/ServletAffichageCompte?noUtilisateur=${requestScope.articleRecherche.acheteur.noUtilisateur }">${requestScope.articleRecherche.acheteur.pseudo }</a>
			</core:if>
			<p>Mise à prix : ${requestScope.articleRecherche.miseAPrix } points</p>
			<p>Retrait : ${requestScope.articleRecherche.pointRetrait.rue } ${requestScope.articleRecherche.pointRetrait.codePostal } ${requestScope.articleRecherche.pointRetrait.ville }</p>
			<p>Vendeur : ${requestScope.articleRecherche.vendeur.pseudo }</p>
			<core:if test="${sessionScope.utilisateur.pseudo.equals(requestScope.articleRecherche.acheteur.pseudo) && !empty requestScope.articleRecherche.vendeur.telephone }">
				<p>Téléphone : ${requestScope.articleRecherche.vendeur.telephone }</p>
			</core:if>
		</main>
		<footer>
		
		</footer>
	</body>
</html>