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
			<p>ENI-Encheres</p>
			<a href="">Ench�res</a>
			<a href="">Vendre un article</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletModificationProfil">Mon profil</a>
			<a href="${pageContext.request.contextPath }/Encheres/ServletDeconnexion">D�connexion</a>
		</header>
		<jsp:include page="ListeEnchere.jsp">
			<jsp:param value="" name=""/>
		</jsp:include>
		<footer>
		
		</footer>
	</body>
</html>