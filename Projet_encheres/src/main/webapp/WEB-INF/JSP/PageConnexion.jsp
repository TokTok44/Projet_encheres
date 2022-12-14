<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Connexion</title>
	</head>
	<body>
		<header>
			<p>ENI-Enchères</p>
			<jsp:include page="ListeErreur.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
		</header>
		
		<main>
			<form action="${pageContext.request.contextPath }/Encheres/ServletPageConnexion" method="post">
				<label for="identifaint">Identifiant : </label>
				<input type="text" name="identifiant" id="identifiant">
				<label for="mdp">Mot de passe : </label>
				<input type="password" name="mdp" id="mdp">
				
				<input type="submit" value="Connexion" name="connexion">
				
				<input type="checkbox" value="memoriser" id="memoriser">
				<label for="memoriser">Se souvenir de moi</label>
				<a href="${pageContext.request.contextPath }/Encheres/ServletOublieMDP"></a>
				
			</form>
			
			<a href="${pageContext.request.contextPath }/Encheres/ServletCreationCompte">Créer un compte</a>
		</main>
		
		<footer>
		
		</footer>
	</body>
</html>