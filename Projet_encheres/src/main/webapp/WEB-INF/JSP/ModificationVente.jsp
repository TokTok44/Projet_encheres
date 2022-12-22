<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modification d'une vente</title>
	</head>
	<body>
		<header>
			<p>ENI-Enchères</p>
		</header>
		<main>
			<h1>Modification de la vente</h1>
			<form action="${pageContext.request.contextPath }/Encheres/ServletModificationVente" method="post">
				<label>Article :</label>
				<input type="text" name="nomArticle" value="${requestScope.articleRecherche.nomArticle }">
				<label>Description :</label>
				<input type="text" name="description" value="${requestScope.articleRecherche.description }">
				<label>Categorie</label>
				<select name="choixCategorie">
					<option value="${requestScope.articleRecherche.categorie.noCategorie }">${requestScope.articleRecherche.categorie.libelle }</option>
					<core:forEach var="categorie" items="${requestScope.listeCategorie }">
						<core:if test="${!categorie.libelle.equals(requestScope.articleRecherche.categorie.libelle) }">
							<option value="${categorie.noCategorie }">${categorie.libelle }</option>
						</core:if>
					</core:forEach>
				</select>
				<!-- Upload d'un fichier
				<label>Article :</label>
				<input type="text" name="nomArticle" value="${requestScope.articleRecherche.nomArticle }">
				 -->
				<label>Mise à prix :</label>
				<input type="number" name="miseAPrix" value="${requestScope.articleRecherche.miseAPrix }">
				<label>Date début enchère :</label>
				<input type="date" name="dateDebutEncheres" value="${requestScope.articleRecherche.dateDebutEncheres }">
				<label>Date fin enchère :</label>
				<input type="date" name="dateFinEncheres" value="${requestScope.articleRecherche.dateFinEncheres }">
				<fieldset>
					<legend>Retrait</legend>
					<label>Rue :</label>
					<input type="text" name="rue" value="${requestScope.articleRecherche.pointRetrait.rue }">
					<label>Code postal :</label>
					<input type="text" name="codePostal" value="${requestScope.articleRecherche.pointRetrait.codePostal }">
					<label>Ville :</label>
					<input type="text" name="ville" value="${requestScope.articleRecherche.pointRetrait.ville }">
				</fieldset>
				<input type="hidden" value="${requestScope.articleRecherche.noArticle }" name="noArticle">
				<input type="submit" value="Enregistrer" name="validation">
				<a href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Annuler</a>
				<input type="submit" value="Annuler la vente" name="validation">
			</form>
		</main>
		<footer>
			
		</footer>
	</body>
</html>