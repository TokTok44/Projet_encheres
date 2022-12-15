<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier mon compte</title>
</head>
<body>
	<header>
		<p>ENI-Encheres</p>
		<jsp:include page="ListeErreur.jsp">
				<jsp:param value="" name=""/>
			</jsp:include>
		<a href="">Enchères</a> <a
			href="${pageContext.request.contextPath }/Encheres/ServletNouvelleVente">Vendre
			un article</a> <a
			href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">Déconnexion</a>
	</header>
	<main>

	<form action="${pageContext.request.contextPath }/Encheres/ServletModificationCompte" method="post">
				<label>Pseudo : </label>
				<input value="${utilisateur.pseudo }" type="text" name="pseudo" required />
				
				<label>Nom : </label>
				<input value="${utilisateur.nom }" type="text" name="nom" required />
				
				<label>Prénom : </label>
				<input value="${utilisateur.prenom }" type="text" name="prenom" required />
				
				<label>Email : </label>
				<input value="${utilisateur.email }" type="email" name="email" required />
				
				<label>Téléphone : </label>
				<input value="${utilisateur.telephone }" type="text" name="telephone" />
				
				<label>Rue : </label>
				<input value="${utilisateur.rue }" type="text" name="rue" required />
				
				<label>Code postal : </label>
				<input value="${utilisateur.codePostal }" type="text" name="codePostal" required />
				
				<label>Ville : </label>
				<input value="${utilisateur.ville }" type="text" name="ville" required />
				
				<label>Mot de passe actuel : </label>
				<input type="password" name="mdp" required>
				
				<label>Nouveau mot de passe : </label>
				<input type="password" name="newMdp"/>
				
				<label>Confirmation du nouveau mot de passe : </label>
				<input type="password" name="confirmationNewMdp" />
				
				<p>Crédit : ${utilisateur.credit }</p>
				
				<input name="modifier" type="submit" value="Enregistrer">
				
				<input name="modifier" type="submit" value="Supprimer mon compte">
				
				<a class="annuler" href="${pageContext.request.contextPath }/Encheres/ServletPageAccueil">Annuler</a>
				

			</form>
	</main>
</body>
</html>