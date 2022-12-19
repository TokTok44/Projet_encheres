<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Detail Ventes</title>
</head>
<body>
	<header>
		<p>ENI-Encheres</p>
		<jsp:include page="ListeErreur.jsp">
			<jsp:param value="" name=""/>
		</jsp:include>
		<a href="">Enchères</a>
		<a href="${pageContext.request.contextPath }/Encheres/ServletNouvelleVente">Vendre un article</a>
		<a href="${pageContext.request.contextPath }/Encheres/ServletAffichageCompte">Mon compte</a>
		<a href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">Déconnexion</a>
	</header>
		
	<main>
	
	<h1>Détail vente</h1>
	
	<p>${requestScope.articleRecherche.nomArticle }</p>
	<p>Description : ${requestScope.articleRecherche.description }</p>
	<p>Catégorie : ${requestScope.articleRecherche.categorie.libelle }</p>
	<p>Meilleure offre : ${requestScope.articleRecherche.prixVente } pts par ${requestScope.articleRecherche.acheteur }</p>
	<p>Mise à prix : ${requestScope.articleRecherche.miseAPrix } points</p>
	<p>Fin de l'enchère : ${requestScope.articleRecherche.dateFinEncheres }</p>
	<p>Retrait : ${requestScope.articleRecherche.retrait.rue }<br>
				${requestScope.articleRecherche.retrait.codePostal } ${requestScope.articleRecherche.retrait.ville }</p>
	<p>Vendeur : ${requestScope.articleRecherche.vendeur }</p>
	<p>Ma proposition : 
	<form action="${pageContext.request.contextPath }/Encheres/Servlet" method="post">
		<input name="noArticle" type="hidden" value="${requestScope.articleRecherche.noArticle }"/>
		<input name="valeurEnchere" type="number" min="${(requestScope.articleRecherche.prixVente)+1 }" value="${(requestScope.articleRecherche.prixVente)+1 }"/>
		<input class="encherir" type="submit" value="Enchérir"/>
	</form>
	
	
	
	
	
	</main>
</body>
</html>