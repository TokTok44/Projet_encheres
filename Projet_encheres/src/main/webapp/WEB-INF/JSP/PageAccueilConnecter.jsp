<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
	</head>
	<body>
		<header>
			<p>ENI-Encheres</p>
			<a href="">Enchères</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletVendre">Vendre un article</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletAffichageCompte">Mon compte</a>
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