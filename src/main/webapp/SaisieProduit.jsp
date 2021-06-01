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
<%@include file="header.jsp"%>
	<div class="container col-md-10 col-md-offset-1">
	<p></p>
		<div class="panel panel-primary">
			<div class="panel-heading">Saisie d'un produit</div>
			<div class="panel-body">
				<form action="SaveProduit.php" method="post">
					<div class="form-group">
						<label class="control-label">Désignation</label>
						<input type="text" name="designation" value="${produit.designation}" class="form-control"/>
						<span></span>
					</div>
					<div class="form-group">
						<label class="control-label">Prix</label>
						<input type="text" name="prix" value="${produit.prix}" class="form-control"/>
						<span></span>
					</div>
					<div class="form-group">
						<label class="control-label">Quantité</label>
						<input type="text" name="quantite" value="${produit.quantite}" class="form-control"/>
						<span></span>
					</div>
					<div>
						<button type="submit" class="btn btn-primary">Save</button>
						<span></span>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>