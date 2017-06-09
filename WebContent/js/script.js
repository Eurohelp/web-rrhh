function crearGrafo(data) {
	var w = $(window).width();
	var h = 1000;
	var linkDistance = 150;
	var colors = d3.scale.category20();
	config = {
		"key1" : "s", // SPARQL variable name for node1 (optional; default
		// is the 1st variable)
		"key2" : "o", // SPARQL variable name for node2 (optional; default
		// is the 2nd varibale)
		"label1" : "s", // SPARQL variable name for the label of node1
		// (optional; default is the 3rd variable)
		"label2" : "o", // SPARQL variable name for the label of node2
		// (optional; default is the 4th variable)
		"value1" : "p",
		"value2" : "p"
	}
	var json = d3sparql.graph(data, config);
	var graph = (json.head && json.results) ? d3sparql.graph(json, config)
			: json
	var svg = d3.select("body").append("svg").attr({
		"width" : w,
		"height" : h,
		"id" : "result"
	});

	var force = d3.layout.force().nodes(graph.nodes).links(graph.links).size(
			[ w, h ]).linkDistance([ linkDistance ]).charge([ -500 ])
			.theta(0.1).gravity(0.05).start();

	var links = svg.selectAll("line").data(graph.links).enter().append("line")
			.attr("id", function(d, i) {
				return d.target.value
			}).style("stroke", "#ccc").on("mouseover", function(d, i) {
				onMouseOver(removeSymbols(d.target.value));
			}).on("mouseout", function(d, i) {
				onMouseOut();
			});
	//

	var nodes = svg.selectAll("circle").data(graph.nodes).enter().append(
			"circle").attr({
		"r" : 15,
		"id" : function(d, i) {
			return "c" + d.key
		}
	}).style("fill", function(d) {
		return colors(d.label);
	}).call(force.drag)

	var nodelabels = svg.selectAll(".nodelabel").data(graph.nodes).enter()
			.append("text").attr({
				"x" : function(d) {
					return d.x;
				},
				"y" : function(d) {
					return d.y;
				},
				"class" : "nodelabel",
				'font-size' : 10,
				'text-anchor' : 'middle'
			}).text(function(d) {
				return d.key;
			});
	// se refiere las aristas del grafo
	var edgepaths = svg.selectAll(".edgepath").data(graph.links).enter()
			.append('path').attr(
					{
						'd' : function(d) {
							return 'M ' + d.source.x + ' ' + d.source.y + ' L '
									+ d.target.x + ' ' + d.target.y
						},
						'class' : 'edgepath',
						'stroke-opacity' : 0,
						'id' : function(d, i) {
							return 'edgepath' + i
						},
					}).on("mouseover", function(d, i) {
				onMouseOver(removeSymbols(d.target.value));
			}).on("mouseout", function(d, i) {
				onMouseOut();
			});

	var edgelabels = svg.selectAll(".linkLabel").data(graph.links).enter()
			.append('text').attr({
				'class' : '.linkLabel',
				'id' : function(d, i) {
					return removeSymbols(d.target.value);
				},
				'dx' : 20,
				'dy' : 0,
				'font-size' : 10,
				'fill' : 'gray'
			});

	// VALOR DE LOS NODOS EN D.
	edgelabels.append('textPath').attr('xlink:href', function(d, i) {
		return '#edgepath' + i
	}).text(function(d, i) {
		return d.target.value
	});

	$("[class^='.linkLabel']").hide();

	svg.append('defs').append('marker').attr({
		'id' : 'arrowhead',
		'viewBox' : '-0 -5 10 10',
		'refX' : 25,
		'refY' : 0,
		// 'markerUnits':'strokeWidth',
		'orient' : 'auto',
		'markerWidth' : 10,
		'markerHeight' : 10,
		'xoverflow' : 'visible'
	}).append('svg:path').attr('d', 'M 0,-5 L 10 ,0 L 0,5')
			.attr('fill', '#ccc').attr('stroke', '#ccc');

	force.on("tick", function() {

		links.attr({
			"x1" : function(d) {
				return d.source.x;
			},
			"y1" : function(d) {
				return d.source.y;
			},
			"x2" : function(d) {
				return d.target.x;
			},
			"y2" : function(d) {
				return d.target.y;
			}
		});

		nodes.attr({
			"cx" : function(d) {
				return d.x;
			},
			"cy" : function(d) {
				return d.y;
			}
		});

		nodelabels.attr("x", function(d) {
			return d.x;
		}).attr("y", function(d) {
			return d.y;
		});

		edgepaths.attr('d', function(d) {
			var path = 'M ' + d.source.x + ' ' + d.source.y + ' L '
					+ d.target.x + ' ' + d.target.y;
			// console.log(d)
			return path
		});

		edgelabels.attr('transform', function(d, i) {
			if (d.target.x < d.source.x) {
				bbox = this.getBBox();
				rx = bbox.x + bbox.width / 2;
				ry = bbox.y + bbox.height / 2;
				return 'rotate(180 ' + rx + ' ' + ry + ')';
			} else {
				return 'rotate(0)';
			}
		});
	});
}

function onMouseOver(pNodo) {
	console.log("el nodo seleccionado :" + pNodo);
	$("[id=" + pNodo + "]").show();

}

function onMouseOut() {
	$("[class^='.linkLabel']").hide();
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
