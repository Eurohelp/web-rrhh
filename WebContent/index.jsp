<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<style>path.link {
  fill: none;
  stroke: #666;
  stroke-width: 1.5px;
}
path.textpath {
    fill: none;
    stroke: none;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Eurohelp Consulting</title>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="http://labratrevenge.com/d3-tip/javascripts/d3.tip.v0.6.3.js"></script>
<script src="<%=request.getContextPath()%>/js/d3sparql.js"></script>
<script src="<%=request.getContextPath()%>/js/script.js"></script>
<script src="<%=request.getContextPath()%>/js/llamadasServlet.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$.post('ServGeneradorIndex', function(data) {
		$('#form').append(data);
		$("#result").hide();
	});
</script>
</head>
<body>
<form name="form" id="form" method="post">
</form>
</body>
</html>