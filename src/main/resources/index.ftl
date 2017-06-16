<center>
<table border>
<#if !listaHabilidades?has_content && !listaCertificaciones?has_content && !listaIdiomas?has_content && !listaExperiencia?has_content && !listaUniversidades?has_content>
<TR>
<TD>${categoria}</TD><TD>
<#list listaCategoria as cat>
<INPUT TYPE="checkbox" name="${categoria}" onchange="generateAll()" value="${cat}">${cat}
</#list></TD>
</#if>
<#if listaHabilidades?has_content && listaCertificaciones?has_content && !listaIdiomas?has_content && !listaExperiencia?has_content && !listaUniversidades?has_content>
<TR>
<TD>${habilidad}</TD>
<TD>
<#list listaHabilidades as hab>
<#--Si se ha seleccionado la categoria se mostraran las habilidades y las certificaciones correspondientes-->
<INPUT TYPE="checkbox" name="${habilidad}" onchange="generateAll()" value="${hab}">${hab}
</#list></TD>
<TR> 
<TD>${certificacion}</TD><TD>
<#list listaCertificaciones as cert>
<INPUT TYPE="checkbox" name="${certificacion}" onchange="generateAll()" value="${cert}">${cert}
</#list></TD>
</#if>
<#if listaIdiomas?has_content && listaExperiencia?has_content && listaUniversidades?has_content>
<TR>
<TD>${idioma}</TD>
<TD>
<#list listaIdiomas as idiom>
<INPUT TYPE="checkbox" name="${idioma}" onchange="generateAll()" value="${idiom}">${idiom}
</#list></TD>
<TR>
<TD>${universidad}</TD>
<TD>
<#list listaUniversidades as univ>
<INPUT TYPE="checkbox"  name="${universidad}" onchange="generateAll()" value="${univ}">${univ}
</#list></TD>
<TR>
<TD>${experiencia}</TD>
<TD>
<#list listaExperiencia as exp>
<INPUT TYPE="checkbox" name="${experiencia}" onchange="generateAll()" value="${exp}">${exp}
</#list></TD>
</#if>
</table>
</center>
<div id="result" align=center><input id="busqueda" name="targetNode" type="text"/><button onclick="growNode()">Buscar</button><button onclick="shrinkNode()">Esconder</button></div>