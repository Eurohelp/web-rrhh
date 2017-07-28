<form name="form" id="form">

     <div class="panel list-group">
         <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" id="${categoria}E" data-parent="#menu" name="${categoria}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${categoria}"></span>${categoria}</a>
         <div id="sl" class="sublinks collapse">
             <#list listaCategoria as cat>
                <a class="list-group-item large" data-color="info" name="${categoria}" id="${cat?replace(" ","")}N" onclick="generarGrafo(id,name)"><span class="glyphicon glyphicon-unchecked" id="${cat?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${categoria}" value="${cat}" id ="${cat?replace(" ","")}"/>${cat}</a>
             </#list>
         </div>
         <#if listaHabilidades?has_content && listaCertificaciones?has_content>
             <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sm"  id="${habilidad}E" data-parent="#menu" name="${habilidad}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${habilidad}"></span>${habilidad}</a>
             <div id="sm" class="sublinks collapse">
                 <#list listaHabilidades as hab>
                     <a class="list-group-item large" data-color="info" name="${habilidad}" id="${hab?replace("+","")?replace(" ","")}N" onclick="generarGrafo(id,name)"><span class="glyphicon glyphicon-unchecked" id="${hab?replace("+","")?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${habilidad}" value="${hab}" id ="${hab?replace("+","")?replace(" ","")}"/>${hab}</a>
                 </#list>
             </div>
             <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sg" data-parent="#menu" id="${certificacion}E" name="${certificacion}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${certificacion}"></span>${certificacion}</a>
             <div id="sg" class="sublinks collapse">
                 <#list listaCertificaciones as cert>
                     <a class="list-group-item large" data-color="info" name="${certificacion}" id="${cert?replace(" ","")}N" onclick="generarGrafo(id,name)"><span class="glyphicon glyphicon-unchecked" id="${cert?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${certificacion}" value="${cert}" id ="${cert?replace(" ","")}"/>	${cert}</a>
                 </#list>
             </div>
         </#if>
         <#if listaIdiomas?has_content && listaUniversidades?has_content && listaExperiencia?has_content>
             <a href="#" class="list-group-item" data-toggle="collapse" data-target="#st" data-parent="#menu"  id="${idioma}E" name="${idioma}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${idioma}"></span>${idioma}</a>
             <div id="st" class="sublinks collapse">
                 <#list listaIdiomas as idiom>
                     <a class="list-group-item large" data-color="info" id="${idiom?replace(" ","")}N" name="${idioma}" onclick="generarGrafo(id,name)"><span class="glyphicon glyphicon-unchecked" id="${idiom?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${idioma}" value="${idiom}" id ="${idiom?replace(" ","")}"/>${idiom}</a>
                 </#list>
             </div>
             <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sr" data-parent="#menu"  id="${universidad}E" name="${universidad}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${universidad}"></span>${universidad}</a>
             <div id="sr" class="sublinks collapse">
                 <#list listaUniversidades as univ>
                     <a class="list-group-item large" data-color="info" id="${univ?replace(" ","")}N" name="${universidad}" onclick="generarGrafo(id,name)"><span class="glyphicon glyphicon-unchecked" id="${univ?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${universidad}" value="${univ}" id ="${univ?replace(" ","")}"/>${univ}</a>
                 </#list>
             </div>
             <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sh" data-parent="#menu"  id="${experiencia}E" name="${experiencia}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${experiencia}"></span>${experiencia}</a>
             <div id="sh" class="sublinks collapse">
                 <#list listaExperiencia as exp>
                     <a class="list-group-item large" data-color="info" id="${exp?replace(" ","")}N" name="${experiencia}" onclick="generarGrafo(id,name)"><span class="glyphicon glyphicon-unchecked" id="${exp?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${experiencia}" value="${exp}" id ="${exp?replace(" ","")}"/>${exp}</a>
                 </#list>
             </div>
         </#if>
     </div>
    <div class="panel list-group">
        <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sl" data-parent="#menu" name="${categoria}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${categoria}"></span>${categoria}</a>
        <div id="sl" class="sublinks collapse">
            <#list listaCategoria as cat>
                <a class="list-group-item large" data-color="info" id="${cat?replace(" ","")}N" onclick="seleccionar(id)"><span class="glyphicon glyphicon-unchecked" id="${cat?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${categoria}" value="${cat}" id ="${cat?replace(" ","")}"/>${cat}</a>
            </#list>
        </div>
        <#if listaHabilidades?has_content && listaCertificaciones?has_content>
            <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sm" data-parent="#menu" name="${habilidad}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${habilidad}"></span>${habilidad}</a>
            <div id="sm" class="sublinks collapse">
                <#list listaHabilidades as hab>
                    <a class="list-group-item large" data-color="info" id="${hab?replace("+","")?replace(" ","")}N" onclick="seleccionar(id)"><span class="glyphicon glyphicon-unchecked" id="${hab?replace("+","")?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${habilidad}" value="${hab}" id ="${hab?replace("+","")?replace(" ","")}"/>${hab}</a>
                </#list>
            </div>
            <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sg" data-parent="#menu" name="${certificacion}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${certificacion}"></span>${certificacion}</a>
            <div id="sg" class="sublinks collapse">
                <#list listaCertificaciones as cert>
                    <a class="list-group-item large" data-color="info" id="${cert?replace(" ","")}N" onclick="seleccionar(id)"><span class="glyphicon glyphicon-unchecked" id="${cert?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${certificacion}" value="${cert}" id ="${cert?replace(" ","")}"/>	${cert}</a>
                </#list>
            </div>
        </#if>
        <#if listaIdiomas?has_content && listaUniversidades?has_content && listaExperiencia?has_content>
            <a href="#" class="list-group-item" data-toggle="collapse" data-target="#st" data-parent="#menu" name="${idioma}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${idioma}"></span>${idioma}</a>
            <div id="st" class="sublinks collapse">
                <#list listaIdiomas as idiom>
                    <a class="list-group-item large" data-color="info" id="${idiom?replace(" ","")}N" onclick="seleccionar(id)"><span class="glyphicon glyphicon-unchecked" id="${idiom?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${idioma}" value="${idiom}" id ="${idiom?replace(" ","")}"/>${idiom}</a>
                </#list>
            </div>
            <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sr" data-parent="#menu" name="${universidad}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${universidad}"></span>${universidad}</a>
            <div id="sr" class="sublinks collapse">
                <#list listaUniversidades as univ>
                    <a class="list-group-item large" data-color="info" id="${univ?replace(" ","")}N" onclick="seleccionar(id)"><span class="glyphicon glyphicon-unchecked" id="${univ?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${universidad}" value="${univ}" id ="${univ?replace(" ","")}"/>${univ}</a>
                </#list>
            </div>
            <a href="#" class="list-group-item" data-toggle="collapse" data-target="#sh" data-parent="#menu" name="${experiencia}C" onclick="changeIcon(name)"><span class="glyphicon glyphicon-chevron-down pull-right" id="span${experiencia}"></span>${experiencia}</a>
            <div id="sh" class="sublinks collapse">
                <#list listaExperiencia as exp>
                    <a class="list-group-item large" data-color="info" id="${exp?replace(" ","")}N" onclick="seleccionar(id)"><span class="glyphicon glyphicon-unchecked" id="${exp?replace(" ","")}cb"></span><input type="checkbox" class="hidden" name="${experiencia}" value="${exp}" id ="${exp?replace(" ","")}"/>${exp}</a>
                </#list>
            </div>
        </#if>
    </div>
</form>
<div id="boton" align=center>
    <button class="btn btn-primary" id="getData" type="button" onclick="validar()">BUSCAR</button>
</div>
