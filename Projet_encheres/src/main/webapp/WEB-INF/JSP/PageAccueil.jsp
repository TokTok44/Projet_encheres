<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="ISO-8859-1">
		<title>Accueil</title>
		
	</head>
	
	<body>
		<header>
			<p>ENI-Encheres</p>
			<jsp:include page="ListeErreur.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
			<a href="${pageContext.request.contextPath }/Encheres/ServletCreationCompte">S'inscrire</a>
			<p> - </p>
			<a href="${pageContext.request.contextPath }/Encheres/ServletPageConnexion">Se connecter</a>
		</header>
		
		<jsp:include page="ListeEnchere.jsp">
			<jsp:param value="" name=""/>
		</jsp:include>
		
		<footer>
		
		</footer>
	</body>
</html>