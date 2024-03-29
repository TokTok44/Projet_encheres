<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
	<!-- Page utilis�e pour la page d'accueil connecte et non connecte -->

<h1>Liste des ench�res</h1>

<h3>Filtres :</h3>

<form action="${pageContext.request.contextPath }/Encheres/ServletListeEncheres" method="post">

	<input placeholder="Le nom de l'article contient" type="text" name="recherche" value="${param.recherche }"/>

	<label>Cat�gorie :</label>
		<select name="choixCategorie">
			<option value="0">Toutes</option>
			<core:forEach var="categorie" items="${requestScope.listeCategorie }">
				<option value="${categorie.noCategorie }">${categorie.libelle }</option>
			</core:forEach>
		</select>
	
	<core:if test="${!empty sessionScope.utilisateur }">
	
	<fieldset>
		<div class="filtre">
			<div>
				<input id="achats" type="radio" name="achatsOuVentes" value="achats" ${(param.achatsOuVentes == 'achats')?'checked':'' }>
				<label for="achats">Achats</label>
			</div>
			<div>
				<input id="ouvert" type="checkbox" name="ouvert" value="ouvert" ${(!empty param.ouvert)?'checked':'' }>
				<label for="ouvert">ench�res ouvertes</label>
			</div>
			<div>
				<input id="enCours" type="checkbox" name="enCours" value="enCours" ${(!empty param.enCours)?'checked':'' }>
				<label for="enCours">mes ench�res en cours</label>
			</div>
			<div>
				<input id="remportees" type="checkbox" name="remportees" value="remportees" ${(!empty param.remportees)?'checked':'' }>
				<label for="remportees">mes ench�res remport�es</label>
			</div>
		</div>
		<div class="filtre">
			<div>
				<input id="ventes" type="radio" name="achatsOuVentes" value="ventes" ${(param.achatsOuVentes == 'ventes')?'checked':'' }>
				<label for="ventes">Mes ventes</label>
			</div>
			<div>
				<input id="ventesEnCours" type="checkbox" name="ventesEnCours" value="ventesEnCours" ${(!empty param.ventesEnCours)?'checked':'' }>
				<label for="ventesEnCours">mes ventes en cours</label>
			</div>
			<div>
				<input id="ventesNonDebutees" type="checkbox" name="ventesNonDebutees" value="ventesNonDebutees" ${(!empty param.ventesNonDebutees)?'checked':'' }>
				<label for="ventesNonDebutees">ventes non d�but�es</label>
			</div>
			<div>
				<input id="ventesTerminees" type="checkbox" name="ventesTerminees" value="ventesTerminees" ${(!empty param.ventesTerminees)?'checked':'' }>
				<label for="ventesTerminees">ventes termin�es</label>
			</div>
		</div>
	</fieldset>
	
	</core:if>
	
	<jsp:include page="ListeErreur.jsp">
		<jsp:param value="" name=""/>
	</jsp:include>
	
<input class="rechercher" type="submit" value="Rechercher"/>
</form>
<div class="liste">
	<core:forEach var="articleVendu" items="${requestScope.listeArticles }">
		<div class="articles">
			<p class="image"></p>
			<div class="">
				<b><a href="${pageContext.request.contextPath }/Encheres/ServletDetailArticle?noArticle=${articleVendu.noArticle}">${articleVendu.nomArticle }</a></b>
				<p>Prix : ${articleVendu.prixVente } points</p>
				<p>Fin de l'ench�re : ${articleVendu.dateFinEncheres }<p>
				<p>Vendeur : <a href="${pageContext.request.contextPath }/Encheres/ServletAffichageCompte?noUtilisateur=${articleVendu.vendeur.noUtilisateur }">${articleVendu.vendeur.pseudo }</a>
			</div>
		</div>
	</core:forEach>
</div>
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