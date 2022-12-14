<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nouvelle Vente</title>
</head>
<body>
		<header>
			<p>ENI-Enchères</p>
		</header>
		
		<main>
			
			<h1>Nouvelle vente</h1>
			<jsp:include page="ListeErreur.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
			<form action="${pageContext.request.contextPath }/Encheres/ServletNouvelleVente" method="post">
				<label>Article :</label>
				<input value="${param.nomArticle }" type="text" name="nomArticle" required />
				
				<label>Description :</label>
				<input value="${param.description }" type="text" name="description" required />
				
				<label>Catégorie :</label>
				<input value="${param.prenom }" type="text" name="prenom" required />
				
				<label>Email :</label>
				<input value="${param.email }" type="text" name="email" required />
				
				<label>Téléphone :</label>
				<input value="${param.telephone }" type="text" name="telephone" />
				
				<label>Rue :</label>
				<input value="${param.rue }" type="text" name="rue" required />
				
				<label>Code postal :</label>
				<input value="${param.codePostal }" type="text" name="codePostal" required />
				
				<label>Ville :</label>
				<input value="${param.ville }" type="text" name="ville" required />
				
				<label>Mot de passe :</label>
				<input type="password" name="mdp" required />
				
				<label>Confirmation :</label>
				<input type="password" name="confirmation" required />
				
				<input class="creer" type="submit" value="Créer">
				
				<a class="annuler" href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Annuler</a>

			</form>
		</main>
</body>
</html>