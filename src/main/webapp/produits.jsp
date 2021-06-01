<%@ page isELIgnored ="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Produits</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
</head>
<body>
<%@include file="header.jsp" %>
	<div class="container col-md-9 col-md-offset-2">
	<p></p>
		<div class="panel panel-primary">
			<div class="panel-heading">Recherche des produits</div>
			<div class="panel-body">
			<form action="chercher.php" method="get">
				<Label>Mot clé</Label>
				<input type="text" name="motCle" value="${model.motCle}"/>
				<button type="submit" class="btn btn-primary">Chercher</button>
				</form>
				<table class="table table-striped">
					<tr>
						<th>ID</th><th>Désignation</th><th>Prix</th><th>Quantité</th>
					</tr>
					<c:forEach items="${model.produits}" var="p">
					<tr>
						<td>${p.id}</td>
						<td>${p.designation}</td>
						<td>${p.prix}</td>
						<td>${p.quantite}</td>
						<td><a onclick="return confirm('êtes vous sûre de vouloire supprimer?')" href="Supprime.php?id=${p.id}">Supprimer</a></td>
						<td><a href="Edit.php?id=${p.id}">Edit</a></td>
					</tr>
					</c:forEach>
					
				</table>
			</div>
		</div>
	</div>
	
</body>
</html>