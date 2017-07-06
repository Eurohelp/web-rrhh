<div class="panel list-group">
 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" data-parent="#menu" name="${categoria}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${categoria}"></span>${categoria}</a>
 <div id="sl" class="sublinks collapse">
  <#list listaCategoria as cat>
  <a class="list-group-item large" data-color="info" id="${cat?replace(" ","")}N" onclick="changeCboxIcon(id)"><span class="glyphicon glyphicon-unchecked" id="${cat?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${categoria}" value="${cat}" id ="${cat}"/>${cat}</a>
 </#list>
 </div>
<#if listaHabilidades?has_content && listaCertificaciones?has_content>
 <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sm" data-parent="#menu" name="${habilidad}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${habilidad}"></span>${habilidad}</a>
 <div id="sm" class="sublinks collapse">
<#list listaHabilidades as hab>
<a class="list-group-item large" data-color="info" id="${hab?replace(" ","")}N" onclick="changeCboxIcon(id)"><span class="glyphicon glyphicon-unchecked" id="${hab?replace(" ","")}cb"></span>	${hab}</a>
 </#list>
 </div>
<a href="#" class="list-group-item" data-toggle="collapse" data-target="#sg" data-parent="#menu" name="${certificacion}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${certificacion}"></span>${certificacion}</a>
 <div id="sg" class="sublinks collapse">
<#list listaCertificaciones as cert>
 <a class="list-group-item large" data-color="info" id="${cert?replace(" ","")}N" onclick="changeCboxIcon(id)"><span class="glyphicon glyphicon-unchecked" id="${cert?replace(" ","")}cb"></span>	${cert}</a>
 </#list>
 </div>
 </#if>
</div>
<div id="boton" align=center>
<button class="btn btn-primary" id="getData" type="button" onclick="generateAll()">BUSCAR</button></d>		</div>
</center>
