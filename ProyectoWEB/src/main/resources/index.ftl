<form name="form" id="form" method="post" >
<center>
<table border>
[#list cate2 as cat]
<TR>
<TD>${categoria}</TD>
<TD>
<INPUT TYPE="checkbox" onchange="obtenerJson()" name= value="">Programador Senior
</TD>
[/#list]
<TR><TH>Pulse aquí:</TH>
<TD ALIGN=CENTER>
<button class="btn" type="button" onclick="obtenerJson">Hide</button></table>
</center>
</form>
<div id="result" align=center><input id="busqueda" name="targetNode" type="text" /><button onclick="growNode()">Buscar</button><button onclick="shrinkNode()">Esconder</button></div>