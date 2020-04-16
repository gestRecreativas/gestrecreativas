/**
 * Crea el DataTable.
 * @returns Array object from table
 */
function FullTable(id, cabecera, responsive){
	FullTable(id, cabecera, responsive, null);
}

function FullTable(id, cabecera, responsive, orderColumn){
	
	var order = 0;	
	if (orderColumn != null)
		order = orderColumn;
	
	var textos = (function () {
	    var json = null;
	    var urilang = "./js/datatable.json";
	    $.ajax({
	        'async': false,
	        'global': false,
	        'url': urilang,
	        'dataType': "json",
	        'success': function (data) {
	            json = data;
	        }
	    });
	    return json;
	})(); 
	
	$(id).DataTable( {
		data: dataSet,
		columns: cabecera,
		responsive: responsive,
		lengthMenu: [[ 25, 50, 100], [25, 50, 100]],
		order: [[ order, "asc" ]],
	    language: textos
	});
}

/**
 * Elimina el DataTable
 * @returns Array object from table
 */
function DestroyTable(id){
	$(id).DataTable()
		.destroy();		
}

/**
 * Vacia el DataTable
 * @returns Array object from table
 */
function ClearTable(id){
	$(id).DataTable()
		.clear()
		.draw();
}
/**
 * Tamaño de la tabla
 * @return numero de rows de la tabla
 */
function SizeTable(id){
	return $(id).DataTable().data().any();
}

/**
 * Elimina la capa procesando
 * @returns Array object from table
 */
function jsRemoveWindowLoad() {
    // eliminamos el div que bloquea pantalla
    $("#WindowLoad").remove();
}
 
/**
 * Crea la capa procesando
 * @returns Array object from table
 */
function jsShowWindowLoad(mensaje) {
    jsRemoveWindowLoad();

    if (mensaje === undefined) mensaje = procestxt;
 
    height = 20;
    var ancho = 0;
    var alto = 0;
 
    if (window.innerWidth == undefined) ancho = window.screen.width;
    else ancho = window.innerWidth;
    if (window.innerHeight == undefined) alto = window.screen.height;
    else alto = window.innerHeight;
 
    var heightdivsito = alto/2 - parseInt(height)/2;//Se utiliza en el margen superior, para centrar
	var Widthdivsito = ancho/2 - 60;//Se utiliza en el margen superior, para centrar

		imgCentro = "<div style='text-align:center;height:" + alto + "px;'>" + 
					"<div  style='color:#000;margin-top:" + heightdivsito + "px; font-size:20px;font-weight:bold'>" + 
						"<div class='loader' style='margin-top:" + heightdivsito + "px;margin-left: " + Widthdivsito + "px;'></div><br/><br/>" +
						mensaje + 
					"</div>" +
				"</div>";
        div = document.createElement("div");
        div.id = "WindowLoad"
        div.style.width = ancho + "px";
        div.style.height = alto + "px";
        $("body").append(div);
        input = document.createElement("input");
        input.id = "focusInput";
        input.type = "text"
        $("#WindowLoad").append(input);
        $("#focusInput").focus();
        $("#focusInput").hide();
        $("#WindowLoad").html(imgCentro);
 
}

function getCapaError(message){
	var capa = '<div class="modal fade" id="myModalErroPopUp" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">';
	capa += '<div class="panel panel-red">' + 
    			'<div class="panel-heading">Uppps!!!</div>' +
    				'<div class="panel-body"><p>' + message + '</p></div>' +
    			'<div class="panel-footer">' +
    			'<button type="button" class="btn btn-default" style="font-size:12px" data-dismiss="modal">OK</button>' +
    			'</div>' +
    		'</div>';
	capa += '</div>';
	$( "body" ).append(capa);
	$("#myModalErroPopUp").modal('show');
}

/**
 * Devuelve las columnas de la tabla de establecimientos
 * @returns Array object from table
 */
function generateRowsTableEstablecimientos(object){
	dataSet = null;
	var output = "[";
	$.each(object, function(i) {
		if (i != 0){
			output += ','
		}
		output += "[";
		output += '"<a href=\'javascript:GotoEstablecimiento(' + object[i].idEstablecimiento + ')\'>' + object[i].nombre + '</a>"';
		output += ',"' + object[i].codigoEstablecimiento + '"';
		output += "]";
	});
	output += "]";
	console.log(output);
	dataSet = JSON.parse(output);
}

/**
 * Devuelve las columnas de la tabla de maquinas
 * @returns Array object from table
 */
function generateRowsTableMaquinas(object){
	dataSet = null;
	var output = "[";
	$.each(object, function(i) {
		if (i != 0){
			output += ','
		}
		output += "[";
		output += '"<a href=\'javascript:GotoMaquina(' + object[i].idMaquina + ')\'>' + object[i].nombre + '</a>"';
		output += ',"' + object[i].codigoMaquina + '"';
		output += ',"' + object[i].cargas + '"';
		output += "]";
	});
	output += "]";
	console.log(output);
	dataSet = JSON.parse(output);
}

/**
 * Devuelve las columnas de la tabla de adminMaquinas
 * @returns Array object from table
 */
function generateRowsTableAdminMaquinas(object){
	dataSet = null;
	var output = "[";
	$.each(object, function(i) {
		if (i != 0){
			output += ','
		}
		output += "[";
		output += '"<a href=\'javascript:GotoCreateUpdateMaquina(' + object[i].idMaquina + ')\'>' + object[i].nombre + '</a>"';
		output += ',"' + object[i].establecimiento + '"';
		output += "]";
	});
	output += "]";
	console.log(output);
	dataSet = JSON.parse(output);
}

/**
 * Devuelve las columnas de la tabla de adminEstablecimientos
 * @returns Array object from table
 */
function generateRowsTableAdminEstablecimientos(object){
	dataSet = null;
	var output = "[";
	$.each(object, function(i) {
		if (i != 0){
			output += ','
		}
		output += "[";
		output += '"<a href=\'javascript:GotoCreateUpdateEstablecimiento(' + object[i].idEstablecimiento + ')\'>' + object[i].nombre + '</a>"';
		output += "]";
	});
	output += "]";
	console.log(output);
	dataSet = JSON.parse(output);
}

function estaVacio(valor){
	if( trim(valor) == '' ){
		return true;
	}
	return false;
}

function trim(cadena){
	for (i = 0; i < cadena.length; ){
		if (cadena.charAt(i) == " "){
			cadena = cadena.substring(i + 1, cadena.length);
		} else {
			break;
		}
	}
	for(i = cadena.length - 1; i >= 0; i = cadena.length - 1){
		if (cadena.charAt(i) == " "){
			cadena = cadena.substring(0, i);
		} else {
			break;
		}
	}
	return cadena;
}

function calculaDiferencia(campo){
	if (campo === 'Entrada'){
		var entradaAnt = $("#entradaAnt").val();
		var entrada = $("#entrada").val();
		if (entradaAnt != undefined && entradaAnt != null && !estaVacio(entradaAnt)
				&& entrada != undefined && entrada != null && !estaVacio(entrada)){
			var dif = Number(entrada) - Number(entradaAnt);
			$("#difEnt").val(dif);
		}
	} else {
		var salidaAnt = $("#salidaAnt").val();
		var salida = $("#salida").val();
		if (salidaAnt != undefined && salidaAnt != null && !estaVacio(salidaAnt)
				&& salida != undefined && salida != null && !estaVacio(salida)){
			var dif = Number(salida) - Number(salidaAnt);
			$("#difSal").val(dif);
		}
	}
}