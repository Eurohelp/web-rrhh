<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pruebas varias</title>
<script src="http://d3js.org/d3.v3.min.js"></script>
<script src="<%=request.getContextPath()%>/js/d3sparql.js"></script>
<script src="<%=request.getContextPath()%>/js/script.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$.get('ServGeneradorIndex', function(data) {
		$('body').append(data);
	});
</script>
<script>
	function mishelle() {
		var form = $('#form');
		var json = "";
		$
				.ajax({
					url : 'ServGetJson',
					data : form.serialize(),
					type : 'post',
					success : function(data) {
						console.log(data);
						obj=JSON.parse(data);
						crearGrafo(obj);
					}
				});

	}
</script>

</head>
<body>


</body>
</html>