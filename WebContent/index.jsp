<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="estilos.css">
<title>Eurohelp Consulting</title>
<meta name="description"
	content="Write some words to describe your html page">
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
		$("#result").hide();
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
		<div class="pageLeftMenu" id="pageLeftMenu">

		</div>
		<div id="graph" class="pageContent">
			<div id="result" align=center></div>
		</div>
		<div class="pageFooter"></div>
	</div>
</body>
</html>

