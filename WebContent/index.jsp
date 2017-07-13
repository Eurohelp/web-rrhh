<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<title>Eurohelp Consulting</title>
<meta name="description"
	content="Write some words to describe your html page">
	<!-- JavaScript -->
<script src="//cdn.jsdelivr.net/alertifyjs/1.10.0/alertify.min.js"></script>
<!-- CSS -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/alertify.min.css"/>
<!-- Default theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/default.min.css"/>
<!-- Semantic UI theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/semantic.min.css"/>
<!-- Bootstrap theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/bootstrap.min.css"/>

<!-- 
    RTL version
-->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/alertify.rtl.min.css"/>
<!-- Default theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/default.rtl.min.css"/>
<!-- Semantic UI theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/semantic.rtl.min.css"/>
<!-- Bootstrap theme -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/alertifyjs/1.10.0/css/themes/bootstrap.rtl.min.css"/>
<link rel="stylesheet" type="text/css" href="estilos.css">
<!-- Fuente -->
<link href="https://fonts.googleapis.com/css?family=Bellefair"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script
	src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script>

<script src="<%=request.getContextPath()%>/js/d3sparql.js"></script>
<script src="<%=request.getContextPath()%>/js/script.js"></script>
<script src="<%=request.getContextPath()%>/js/llamadasServlet.js"></script>

<script>
	$.post('ServGeneradorIndex', function(data) {
		$('#pageLeftMenu').append(data);
	});
</script>
</head>
<body>
	<div class="blended_grid">
		<div class="pageHeader">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="navbar-header">
						<a class="navbar-brand"> Eurohelp Consulting </a>
					</div>
				</div>
			</nav>
		</div>
		<div class="pageLeftMenu" id="pageLeftMenu"></div>
		<div id="graph" class="pageContent">
			<div class="input-group custom-search-form" id="search"
				style="visibility: hidden">
				<input type="text" class="form-control" id="busqueda"
					placeholder="Introduce valor a buscar"> <span
					class="input-group-btn">
					<button class="btn btn-primary" type="button" onclick="growNode()">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
			<!-- /input-group -->
			<div id="result" align=center></div>
		</div>
		<div class="pageFooter"></div>
	</div>
</body>
</html>

