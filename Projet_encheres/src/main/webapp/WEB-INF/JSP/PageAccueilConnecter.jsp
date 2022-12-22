<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<link href="../../${pageContext.request.contextPath }/CSS/ListeEncheres.css" rel="stylesheet">
	</head>
	<body>
		<header>
			<p>ENI-Encheres</p>
			<jsp:include page="ListeErreur.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
			<a href="">Enchères</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletNouvelleVente">Vendre un article</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletAffichageCompte?noUtilisateur=${sessionScope.utilisateur.noUtilisateur }">Mon compte</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">Déconnexion</a>
		</header>
		<main>
			
			<p>Bienvenue ${sessionScope.utilisateur.pseudo }</p>
			<jsp:include page="ListeEnchere.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
		</main>
		<footer>
		
		</footer>
	</body>
</html>