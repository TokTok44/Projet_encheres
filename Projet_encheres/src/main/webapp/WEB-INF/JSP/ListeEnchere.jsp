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
	
	<fieldset>
	

	
	
	<input id="achats" type="radio" name="achatsOuVente" value="achats" checked>
	<label for="achats">Achats</label>
	
	
	<input id="ouvert" type="checkbox" name="ouvert" value="ouvert" checked>
	<label for="ouvert">enchères ouvertes</label>
	
	
	<input id="enCours" type="checkbox" name="enCours" value="enCours">
	<label for="enCours">mes enchères en cours</label>
	
	
	<input id="remportees" type="checkbox" name="remportees" value="remportees">
	<label for="remportées">mes enchères remportées</label>
	
	
	<input id="ventes" type="radio" name="achatsOuVente" value="ventes">
	<label for="ventes">Mes ventes</label>
	
	<input id="ventesEnCours" type="checkbox" name="ventesEnCours" value="ventesEnCours">
	<label for="ventesEnCours">mes ventes en cours</label>
	
	<input id="ventesNonDebutees" type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees">
	<label for="ventesNonDebutees">ventes non débutées</label>
	
	<input id="ventesTerminees" type="checkbox" name="ventesTerminees" value="ventesTerminees">
	<label for="ventesTerminees">ventes terminées</label>

	</fieldset>
	
	
	
<input class="rechercher" type="submit" value="Rechercher"/>
</form>

<core:forEach var="articleVendu" items="${requestScope.listeArticles }">
	<div>
		<b><a href="${pageContext.request.contextPath }/Encheres/ServletDetailArticle?noArticle=${articleVendu.noArticle}">${articleVendu.nomArticle }</a></b>
		<p>Prix : ${articleVendu.prixVente }</p>
		<p>Fin de l'enchère : ${articleVendu.dateFinEncheres }<p>
		<p>Vendeur : <a href="">${articleVendu.vendeur.pseudo }</a>
	</div>

</core:forEach>

	<script type="text/javascript">
		const achatsRadio = document.getElementById("achats");
		const ventesRadio = document.getElementById("ventes");
		const checkOuvert = document.getElementById("ouvert");
		const checkEnCours = document.getElementById("enCours");
		const checkRemportees = document.getElementById("remportees");
		const checkVentesEnCours = document.getElementById("ventesEnCours");
		const checkVentesNonDebutees = document.getElementById("ventesNonDebutees");
		const checkVentesTerminees = document.getElementById("ventesTerminees");
		
		achatsRadio.addEventListener("change", function(){
				
			checkOuvert.disabled = false;
			checkEnCours.disabled = false;
			checkRemportees.disabled = false;
			
			checkVentesEnCours.disabled = true;
			checkVentesNonDebutees.disabled = true;
			checkVentesTerminees.disabled = true;

		});
		
		ventesRadio.addEventListener("change", function(){
			
			checkOuvert.disabled = true;
			checkEnCours.disabled = true;
			checkRemportees.disabled = true;
						
			checkVentesEnCours.disabled = false;
			checkVentesNonDebutees.disabled = false;
			checkVentesTerminees.disabled = false;
			
		})
	
	</script>