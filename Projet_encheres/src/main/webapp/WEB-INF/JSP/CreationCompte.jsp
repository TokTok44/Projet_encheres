<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
		<header>
			<p>ENI-Ench�res</p>
		</header>
		<main>
			
			<h1>Cr�ation de profil</h1>
			
			<form action="/Encheres/ServletCreationCompte" method="post">
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
				<input type="text" name="mdp" required />
				
				<label>Confirmation :</label>
				<input type="text" name="confirmation" required />
				
				<input type="submit" value="Cr�er">
				
				<a href="/Encheres/ServletPageAccueil">Annuler</a>
			</form>
		</main>
		<footer>
		
		</footer>
	</body>
</html>