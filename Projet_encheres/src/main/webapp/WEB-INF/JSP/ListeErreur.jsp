<%@page import="fr.eni.encheres.exception.LecteurMessage"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<core:if test="${requestScope.listeErreur.size()>0 }">
	<% 
		List<Integer> listeErreur = (List<Integer>) request.getAttribute("listeErreur"); 
		for(int codeErreur : listeErreur){
	%>
		<p><%= LecteurMessage.getMessageErreur(codeErreur) %></p>
	<%
		}
	%>
</core:if>