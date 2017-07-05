<center>
<table border>
<TR>
<TD>${categoria}</TD><TD>
<#list listaCategoria as cat>
<INPUT TYPE="checkbox" name="${categoria}" value="${cat}" id ="${cat}">${cat}
</#list></TD>
<#if listaHabilidades?has_content && listaCertificaciones?has_content>
<TR>
<TD>${habilidad}</TD>
<TD>
<#list listaHabilidades as hab>
<#--Si se ha seleccionado la categoria se mostraran las habilidades y las certificaciones correspondientes-->
<INPUT TYPE="checkbox" name="${habilidad}" value="${hab}" id ="${hab}">${hab}
</#list></TD>
<TR> 
<TD>${certificacion}</TD><TD>
<#list listaCertificaciones as cert>
<INPUT TYPE="checkbox" name="${certificacion}" value="${cert}" id ="${cert}">${cert}
</#list></TD>
</#if>
<#if listaIdiomas?has_content && listaExperiencia?has_content && listaUniversidades?has_content>
<TR>
<TD>${idioma}</TD>
<TD>
<#list listaIdiomas as idiom>
<INPUT TYPE="checkbox" name="${idioma}"  value="${idiom}" id ="${idiom}">${idiom}
</#list></TD>
<TR>
<TD>${universidad}</TD>
<TD>
<#list listaUniversidades as univ>
<INPUT TYPE="checkbox"  name="${universidad}"  value="${univ}" id ="${univ}">${univ}
</#list></TD>
<TR>
<TD>${experiencia}</TD>
<TD>
<#list listaExperiencia as exp>
<INPUT TYPE="checkbox" name="${experiencia}"  value="${exp}" id ="${exp}">${exp}
</#list></TD>
</#if>
<TR><TH>Pulse aquí:</TH>		
<TD ALIGN=CENTER>		
<button class="btn" type="button" onclick="generateAll()">Hide</button></table>		
</table>
</center>
<#--

<input id="busqueda" name="targetNode" type="text"/><button onclick="growNode()">Buscar</button><button onclick="shrinkNode()">Esconder</button>-->