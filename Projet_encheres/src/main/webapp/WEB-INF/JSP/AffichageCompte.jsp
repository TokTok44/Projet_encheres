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
		<a href="">Enchères</a>
		<a href="${pageContext.request.contextPath }/Encheres/ServletVendre">Vendre un article</a>
		<a href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">Déconnexion</a>
	</header>
	<main>			
				<p>Pseudo : $(requestScope.pseudo)</p>
								
				<p>Nom : $(requestScope.nom)</p>
				
				<p>Prénom : $(requestScope.prenom)</p>
				
				<p>Email : $(requestScope.email)</p>
				
				<p>Téléphone : $(requestScope.telephone)</p>
				
				<p>Rue : $(requestScope.rue)</p>
				
				<p>Code postal : $(requestScope.codePostal)</p>
				
				<p>Ville : $(requestScope.ville)</p>
				
				<a class="modifier" href="${pageContext.request.contextPath }/Encheres/ServletModificationCompte">Modifier</a>
				<a class="annuler" href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Accueil</a>
	
	
	</main>


</body>
</html>