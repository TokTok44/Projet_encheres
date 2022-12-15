<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- Page utilisée pour la page d'accueil connecte et non connecte -->

<h1>Liste des enchères</h1>

<h3>Filtres :</h3>

<form action="${pageContext.request.contextPath }/Encheres/ServletListeEncheres" method="post">

	<input placeholder="Le nom de l'article contient" type="text" name="recherche"/>

	<label>Catégorie :</label>
		<select name="choixCategorie">
			<option value="0">Toutes</option>
			<core:forEach var="categorie" items="${requestScope.listeCategorie }">
				<option value="${categorie.noCategorie }">${categorie.libelle }</option>
			</core:forEach>
		</select>

<input class="rechercher" type="submit" value="Rechercher"/>
</form>

<core:forEach var="articleVendu" items="${requestScope.listeArticles }">
	<div>
		<b><a href="">${articleVendu.nomArticle }</a></b>
		<p>Prix : ${articleVendu.prixVente }</p>
		<p>Fin de l'enchère : ${articleVendu.dateFinEnchere }<p>
		<p>Vendeur : <a href="">${articleVendu.vendeur }</a>
	
	</div>

</core:forEach>