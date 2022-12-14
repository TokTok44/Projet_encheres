<%@page import="fr.eni.encheres.exception.LecteurMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Cr�ation de profil</title>
		<link href="../../${pageContext.request.contextPath }/CSS/CreationCompte.css" rel="stylesheet">
	</head>
	
	<body>
		<header>
			<p>ENI-Ench�res</p>
		</header>
		<main>
			
			<h1>Cr�ation de profil</h1>
			<jsp:include page="ListeErreur.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
			<form action="${pageContext.request.contextPath }/Encheres/ServletCreationCompte" method="post">
				<label>Pseudo :</label>
				<input type="text" name="pseudo" required />
				
				<label>Nom :</label>
				<input type="text" name="nom" required />
				
				<label>Pr�nom :</label>
				<input type="text" name="prenom" required />
				
				<label>Email :</label>
				<input type="text" name="email" required />
				
				<label>T�l�phone :</label>
				<input type="text" name="telephone" />
				
				<label>Rue :</label>
				<input type="text" name="rue" required />
				
				<label>Code postal :</label>
				<input type="text" name="codePostal" required />
				
				<label>Ville :</label>
				<input type="text" name="ville" required />
				
				<label>Mot de passe :</label>
				<input type="password" name="mdp" required />
				
				<label>Confirmation :</label>
				<input type="password" name="confirmation" required />
				
				<input class="creer" type="submit" value="Cr�er">
				
				<a class="annuler" href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Annuler</a>
			</form>
		</main>
		<footer>
		
		</footer>
	</body>
</html>