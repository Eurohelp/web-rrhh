function crearGrafo(data) {
	// http://blog.thomsonreuters.com/index.php/mobile-patent-suits-graphic-of-the-day/
	var links =[{source:"http://opendata.euskadi.eus/UDA",target:"UDA",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/UDA",type:"http://opendata.euskadi.eus/certification"},{source:"http://opendata.euskadi.eus/ISTQB",target:"ISTQB",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/ISTQB",type:"http://opendata.euskadi.eus/certification"},{source:"http://opendata.euskadi.eus/TrabajoEntidadesPrivadas",target:"Trabajo en proyectos de entidades privadas",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/TrabajoEntidadesPrivadas",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/TrabajoEntidadesPublicas",target:"Trabajo en proyectos de entidades publicas",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/TrabajoEntidadesPublicas",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/TrabajoDesarrolloGeneral",target:"Trabajo en proyectos desarrollo general",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/TrabajoDesarrolloGeneral",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/TrabajoProyectoID",target:"Trabajo en proyectos I+D",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/TrabajoProyectoID",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/java",target:"Java",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/java",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/html",target:"HTML",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/html",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/c++",target:"C++",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/c++",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/PMP",target:"PMP",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/PMP",type:"http://opendata.euskadi.eus/certification"},{source:"http://opendata.euskadi.eus/r",target:"R",type:"http://schema.org/name"},{source:"http://opendata.euskadi.eus/Analyst",target:"http://opendata.euskadi.eus/r",type:"http://opendata.euskadi.eus/skill"},{source:"http://opendata.euskadi.eus/Analyst",target:"Analista",type:"http://schema.org/name"}]
	;
	console.log(typeof links)

	var nodes = {};

	// Compute the distinct nodes from the links.
	links.forEach(function(link) {
	  link.source = nodes[link.source] || (nodes[link.source] = {name: link.source});
	  link.target = nodes[link.target] || (nodes[link.target] = {name: link.target});
	});

	var w = $(window).width(),
	    h = 1000;

	var force = d3.layout.force()
	    .nodes(d3.values(nodes))
	    .links(links)
	    .size([w, h])
	    .linkDistance(180)
	    .charge(-500).theta(0.1).gravity(0.05)
	    .on("tick", tick)
	    .start();

	var svg = d3.select("body").append("svg:svg")
	    .attr("width", w)
	    .attr("height", h);

	// Per-type markers, as they don't inherit styles.
	svg.append("svg:defs").selectAll("marker")
	    .data(["suit", "licensing", "resolved"])
	  .enter().append("svg:marker")
	    .attr("id", String)
	    .attr("viewBox", "0 -5 10 10")
	    .attr("refX", 15)
	    .attr("refY", -1.5)
	    .attr("markerWidth", 6)
	    .attr("markerHeight", 6)
	    .attr("orient", "auto")
	  .append("svg:path")
	    .attr("d", "M0,-5L10,0L0,5");

	    var link = svg.append("svg:g").selectAll("g.link")
	        .data(force.links())
	      .enter().append('g')
	        .attr('class', 'link');
	    
	    var linkPath = link.append("svg:path")
	        .attr("class", function(d) { return "link " + d.type; })
	        .attr("marker-end", function(d) { return "url(#" + d.type + ")"; })
	        .style("stroke", "#ccc")
	        .on("mouseover", function(d, i) {
				onMouseOver(removeSymbols(d.type));
			}).on("mouseout", function(d, i) {
				onMouseOut();
			});
	    
	    var textPath = link.append("svg:path")
	        .attr("id", function(d) { return d.source.index + "_" + d.target.index; })
	        .attr("class", "textpath");

	var circle = svg.append("svg:g").selectAll("circle")
	    .data(force.nodes())
	  .enter().append("svg:circle")
	    .attr({"r": 15, "fill":"#BDBDBD"})
	    .call(force.drag);

	var text = svg.append("svg:g").selectAll("g")
	    .data(force.nodes())
	  .enter().append("svg:g");

	// A copy of the text with a thick white stroke for legibility.
	text.append("svg:text")
	    .attr("x", 8)
	    .attr("y", ".31em")
	    .attr({"font-size":"10",'text-anchor' : 'middle'})
	    .attr("class", "shadow")
	    .text(function(d) { return d.name; });

	text.append("svg:text")
	    .attr("x", 8)
	    .attr("y", ".31em")
	    .attr({"font-size":"10",'text-anchor' : 'middle'})
	    .text(function(d) { return d.name; });

	var path_label = svg.append("svg:g").selectAll(".path_label")
	    .data(force.links())
	  .enter().append("svg:text")
	    .attr({"class": "path_label","id" : function(d, i) {
			return removeSymbols(d.type);
		}})
	    .append("svg:textPath")
	      .attr("startOffset", "50%")
	      .attr("text-anchor", "middle")
	      .attr("xlink:href", function(d) { return "#" + d.source.index + "_" + d.target.index; })
	      .style("fill", "#000")
	      .style({"font-size":"10","font-family": "Arial"})
	      .text(function(d) { return d.type; });
	

	    function arcPath(leftHand, d ) {
	        var start = leftHand ? d.source : d.target,
	            end = leftHand ? d.target : d.source,
	            dx = end.x - start.x,
	            dy = end.y - start.y,
	            dr = Math.sqrt(dx * dx + dy * dy),
	            sweep = leftHand ? 0 : 1;
	        return "M" + start.x + "," + start.y + "A" + dr + "," + dr + " 0 0," + sweep + " " + end.x + "," + end.y;
	    }
		$("[class^='path_label']").hide();
	    
	// Use elliptical arc path segments to doubly-encode directionality.
	function tick() {
	  linkPath.attr("d", function(d) {
	    return arcPath(false, d);
	  });
	    
	  textPath.attr("d", function(d) {
	    return arcPath(d.source.x < d.target.x, d);
	  });

	  circle.attr("transform", function(d) {
	    return "translate(" + d.x + "," + d.y + ")";
	  });

	  text.attr("transform", function(d) {
	    return "translate(" + d.x + "," + d.y + ")";
	  });
	}
}

function onMouseOver(pNodo) {
	console.log(pNodo);
	$("[id=" + pNodo + "]").show();

}

function onMouseOut() {
	$("[class^='path_label']").hide();
}

function removeSymbols(pString) {
	pString = pString.replace(":", "");
	pString = pString.replace("//", "");
	pString = pString.replace("/", "");
	pString = pString.replace(/\./g, "");
	return pString;
}

function growNode() {
	var userInput = document.getElementById("busqueda");
	var theNode = d3.select("#c" + userInput.value);
	theNode.attr("r", 25);
}

function shrinkNode() {
	var userInput = document.getElementById("busqueda");
	var theNode = d3.select("#c" + userInput.value);
	theNode.attr("r", 15);
}
