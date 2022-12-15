<%@page import="java.time.LocalDate"%>
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
				<select name="choixCategorie">
				<option value=""></option>
				    <core:forEach var="categorie" items="${requestScope.listeCategorie }">
				     <option value="${categorie.noCategorie }">${categorie.libelle }</option>
				    </core:forEach>
				</select>
				
				<label>Photo de l'article :</label>
				<input  />
				
				<label>Mise à prix :</label>
				<input type="number" min="0" name="miseAPrix" />
				
				
				
				<label>Début enchère :</label>
				<input value="<%=LocalDate.now() %>" type="date" name="dateDebut" min="<%=LocalDate.now() %>"required />
				
				<label>Fin de l'enchère :</label>
				<input value="<%=(LocalDate.now()).plusDays(1) %>" type="date" name="dateFin" min="<%=(LocalDate.now()).plusDays(1) %>" required />
				
				<p>Retrait</p>
				<label>Rue :</label>
				<input value="${param.rue }" type="text" name="rue" required />
				
				<label>Code postal :</label>
				<input value="${param.codePostal }" type="text" name="codePostal" required />
				
				<label>Ville :</label>
				<input value="${param.ville }" type="text" name="ville" required />
				
				<input class="creer" type="submit" value="Enregistrer">
				
				<a class="annuler" href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Annuler</a>

			</form>
		</main>
</body>
</html>