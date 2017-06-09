<form name="form" id="form" method="post" >
<center>
<table border>
<TR>
<TD>${categoria}</TD>
<#list listaCategoria as cat>
<TD>
<INPUT TYPE="checkbox" onchange="obtenerJson()" name="${cat}" value="${cat}">${cat}
</TD>
</#list>
<#if listaHabilidades?has_content && listaCertificaciones?has_content>
<TR>
<TD>${habilidad}</TD>
<TD>
<#list listaHabilidades as hab>
<#--Si se ha seleccionado la categoria se mostraran las habilidades y las certificaciones correspondientes-->
<INPUT TYPE="checkbox" onchange="obtenerJson()" name="${hab}" value="${hab}">${hab}
</TD>
<TR>
</#list>
<TR>
<TD>${certificacion}</TD>
<#list listaCertificaciones as cert>
<TD>
<INPUT TYPE="checkbox" onchange="obtenerJson()" name="${cert}" value="${cert}">${cert}
</TD>
</#list>
</#if>

<#if listaIdiomas?has_content && listaExperiencia?has_content && listaUniversidades?has_content>
<#list listaIdiomas as idiom>
<TR>
<TD>${idioma}</TD>
<TD>
<INPUT TYPE="checkbox" onchange="obtenerJson()" name="${idiom}" value="${idiom}">${idiom}
</TD>
</#list>
<#list listaUniversidades as univ>
<TR>
<TD>${universidad}</TD>
<TD>
<INPUT TYPE="checkbox" onchange="obtenerJson()" name="${univ}" value="${univ}">${univ}
</TD>
</#list>
<#list listaExperiencia as exp>
<TR>
<TD>${experiencia}</TD>
<TD>
<INPUT TYPE="checkbox" onchange="obtenerJson()" name="${exp}" value="${exp}">${exp}
</TD>
</#list>
</#if>

<TR><TH>Pulse aquí:</TH>
<TD ALIGN=CENTER>
<button class="btn" type="button" onclick="obtenerJson">Hide</button></table>
</center>
</form>
<div id="result" align=center><input id="busqueda" name="targetNode" type="text"/><button onclick="growNode()">Buscar</button><button onclick="shrinkNode()">Esconder</button></div>