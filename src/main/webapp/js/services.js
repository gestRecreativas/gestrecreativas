function VerTodosEstablecimientos(checkbox){
	if (checkbox.checked){
		window.location.href = "establecimientos.html?verTodos=1";
	} else {
		window.location.href = "establecimientos.html?verTodos=0";
	}
}

function GotoEstablecimiento(idEstablecimiento){
	$.get( "establecimiento.html", {idEstablecimiento : idEstablecimiento}, function( html ) {
		$("#page-wrapper").html(html);
		var uriMaquinas = uriMaquinasEstablecimiento + idEstablecimiento;
		$.get( uriMaquinas, function( data ) {
			var object = data;
			generateRowsTableMaquinas(data);
			FullTable('#dataTables-maquinas', columnSetMaquinas, true);
		})
		.fail(function(err) {
			getCapaError(errorMessage);
	    	console.log("Error en la llamada: " + err);		
		});
	});
}

function GotoMaquina(idMaquina){
	$.get( "maquina.html", {idMaquina : idMaquina}, function( html ) {
		$("#page-wrapper").html(html);
		$('.input-number').on('input', function () {
			this.value = this.value.replace(/[^[0-9]+([,][0-9][0-9])]/g,'');
		});
	});
}

function GotoCreditos(idEstablecimiento){
	$.get( "credito.html", {idEstablecimiento : idEstablecimiento}, function( html ) {
		$("#page-wrapper").html(html);
		$('.input-number').on('input', function () {
			this.value = this.value.replace(/[^[0-9]+([,][0-9][0-9])]/g,'');
		});
	});
}

function GotoTicket(idEstablecimiento){
	$.get( "ticket.html", {idEstablecimiento : idEstablecimiento, idUsuario : idUsuario}, function( html ) {
		$("#page-wrapper").html(html);
	});
}

function GotoHistoricoTicket(idEstablecimiento){
	$.get( "historico.html", {idEstablecimiento : idEstablecimiento, idUsuario : idUsuario}, function( html ) {
		$("#page-wrapper").html(html);
	});
}

function GotoCreateUpdateMaquina(idMaquina){
	if (idMaquina != null){
		$.get( "createOrUpdateMaquina.html", {idMaquina : idMaquina}, function( html ) {
			$("#page-wrapper").html(html);
			$('.input-number').on('input', function () {
				this.value = this.value.replace(/[^[0-9]+([,][0-9][0-9])]/g,'');
			});
			var idTipoMaquina = $("#idTipoMaquina").val();
			var tipoMaquinaSelect = document.getElementById("tipoMaquina");
			for (i = 0; i < tipoMaquinaSelect.length; i++) {
				if (tipoMaquinaSelect[i].value == idTipoMaquina) {
					tipoMaquinaSelect[i].selected = true;
				}   
			}
			var idEmpresaMaquina = $("#idEmpresaMaquina").val();
			var empresaMaquinaSelect = document.getElementById("empresaMaquina");
			for (i = 0; i < empresaMaquinaSelect.length; i++) {
				if (empresaMaquinaSelect[i].value == idEmpresaMaquina) {
					empresaMaquinaSelect[i].selected = true;
				}   
			}
			CargaEstablecimientos(idEmpresaMaquina);
		});
	} else {
		$.get( "createOrUpdateMaquina.html", function( html ) {
			$("#page-wrapper").html(html);
			$('.input-number').on('input', function () {
				this.value = this.value.replace(/[^[0-9]+([,][0-9][0-9])]/g,'');
			});
		});
	}
}

function GotoCreateUpdateEstablecimiento(idEstablecimiento){
	if (idEstablecimiento != null){
		$.get( "createOrUpdateEstablecimiento.html", {idEstablecimiento : idEstablecimiento}, function( html ) {
			$("#page-wrapper").html(html);
			$('.input-number').on('input', function () {
				this.value = this.value.replace(/[^[0-9]+([,][0-9][0-9])]/g,'');
			});
			var idEmpresaMaquina = $("#idEmpresaMaquina").val();
			var empresaMaquinaSelect = document.getElementById("empresaMaquina");
			for (i = 0; i < empresaMaquinaSelect.length; i++) {
				if (empresaMaquinaSelect[i].value == idEmpresaMaquina) {
					empresaMaquinaSelect[i].selected = true;
				}   
			}
		});
	} else {
		$.get( "createOrUpdateEstablecimiento.html", function( html ) {
			$("#page-wrapper").html(html);
			$('.input-number').on('input', function () {
				this.value = this.value.replace(/[^[0-9]+([,][0-9][0-9])]/g,'');
			});
		});
	}
}

function CambiaEmpresaMaquina(){
	var idEmpresaMaquina = $("#empresaMaquina").val();
	CargaEstablecimientos(idEmpresaMaquina);
}

function CargaEstablecimientos(idEmpresaMaquina){
	$.get( "establecimientosEmpresaMaquina.html", {idEmpresaMaquina : idEmpresaMaquina}, function( htmlOptions ) {
		document.getElementById("establecimiento").innerHTML= htmlOptions;
		var idEstablecimiento = $("#idEstablecimiento").val();
		if (idEstablecimiento != ""){
			var establecimientoSelect = document.getElementById("establecimiento");
			for (i = 0; i < establecimientoSelect.length; i++) {
				if (establecimientoSelect[i].value == idEstablecimiento) {
					establecimientoSelect[i].selected = true;
				}
			}
		}
	});
}

function BuscarTickets(idEstablecimiento){
	var fechaInicio = $("#fechaInicio").val();
	if (fechaInicio != ""){
		if (!/^[0-3][0-9]\-[0-1][0-9]\-[0-9][0-9][0-9][0-9]$/.exec(fechaInicio)){
			alert("Formato de fecha no válido (dd-mm-yyyy)");
			return false;
		}
	}
	var fechaFin = $("#fechaFin").val();
	if (fechaFin != ""){
		if (!/^[0-3][0-9]\-[0-1][0-9]\-[0-9][0-9][0-9][0-9]$/.exec(fechaFin)){
			alert("Formato de fecha no válido (dd-mm-yyyy)");
			return false;
		}
	}
	$.get( "historico.html", {idEstablecimiento : idEstablecimiento, 
		idUsuario : idUsuario, fechaInicio : fechaInicio, fechaFin : fechaFin}, function( html ) {
		$("#page-wrapper").html(html);
	});
}

function VerTicket(idEstablecimiento, idTicket){
	var fechaInicio = $("#fechaInicio").val();
	if (fechaInicio != ""){
		if (!/^[0-3][0-9]\-[0-1][0-9]\-[0-9][0-9][0-9][0-9]$/.exec(fechaInicio)){
			fechaInicio = "";
		}
	}
	var fechaFin = $("#fechaFin").val();
	if (fechaFin != ""){
		if (!/^[0-3][0-9]\-[0-1][0-9]\-[0-9][0-9][0-9][0-9]$/.exec(fechaFin)){
			fechaFin = "";
		}
	}
	$.get( "ticket.html", {idEstablecimiento : idEstablecimiento, idUsuario : idUsuario, 
		idTicket : idTicket, fechaInicio : fechaInicio, fechaFin : fechaFin}, function( html ) {
		$("#page-wrapper").html(html);
	});
}

function VolverHistoricoTickets(idEstablecimiento, fechaInicio, fechaFin){
	$.get( "historico.html", {idEstablecimiento : idEstablecimiento, 
		idUsuario : idUsuario, fechaInicio : fechaInicio, fechaFin : fechaFin}, function( html ) {
		$("#page-wrapper").html(html);
	});
}

function VerTicketRecaudacion(idEstablecimiento, idTicket){
	var fechaRecaudacion = $("#fecha").val();
	$.get( "ticket.html", {idEstablecimiento : idEstablecimiento, idUsuario : idUsuario, 
		idTicket : idTicket, fechaRecaudacion : fechaRecaudacion}, function( html ) {
		$("#page-wrapper").html(html);
	});
}

function VolverRecaudaciones(){
	$("#formRecaudaciones").submit();
}

function SaveContadores(idMaquina){
	var validados = true;
	var entradaAnt = $("#entradaAnt").val();
	if (entradaAnt == undefined || entradaAnt == null || estaVacio(entradaAnt)){
		validados = false;
	}
	var entrada = $("#entrada").val();
	if (entrada == undefined || entrada == null || estaVacio(entrada)){
		validados = false;
	}
	var salidaAnt = $("#salidaAnt").val();
	if (salidaAnt == undefined || salidaAnt == null || estaVacio(salidaAnt)){
		validados = false;
	}
	var salida = $("#salida").val();
	if (salida == undefined || salida == null || estaVacio(salida)){
		validados = false;
	}
	var comentarios = $("#comentarios").val();
	if (validados){
		$.post(uriContadores, {
			idMaquina : idMaquina,
			entradaAnt : entradaAnt,
			entrada : entrada,
			salidaAnt : salidaAnt,
			salida : salida,
			comentarios : comentarios,
			idUsuario : idUsuario
			},
			function( jsonObj ){
				if (jsonObj.Resultado === 'OK'){
					$("#SaveResultContadores").html("Datos guardados correctamente");
					$("#SaveResultContadores").attr('class', 'resultSuccess');
					$("#SaveResultContadores").show();
					$("#SaveResultContadores").fadeOut(2500);
					var liquidacion = jsonObj.Liquidacion;
					$("#bruto").val(liquidacion.bruto);
					if (liquidacion.fallos != null && liquidacion.fallos != undefined && liquidacion.fallos != 'null'){
						$("#fallos").val(liquidacion.fallos);
					} else {
						$("#fallos").val("0.00");
					}
					$("#recaudacion").val(liquidacion.recaudacion);
					$("#retencion").val(liquidacion.retencion);
					$("#porcenEst").val(liquidacion.porcentajeEst);
					$("#pagoEst").val(liquidacion.pagoEst);
					$("#neto").val(liquidacion.neto);
					$("#acumulado").val(liquidacion.retencionAcumuladaAnio);
					$("#delete1").prop("disabled", false);
					$("#delete2").prop("disabled", false);
				} else {
					$("#SaveResultContadores").html("Se ha producido un error");
					$("#SaveResultContadores").attr('class', 'resultError');
					$("#SaveResultContadores").show();
					$("#SaveResultContadores").fadeOut(2500);
				}
			}
		);
	} else {
		alert("Por favor, complete los contadores de entrada y salida");
	}
}

function DeleteLiquidacion(idMaquina){
	if (confirm("Va a borrar los contadores, liquidación y ticket actual del establecimiento. ¿Está seguro?")){
		$.post(uriDelete, {
			idMaquina : idMaquina
			},
			function( jsonObj ){
				if (jsonObj.Resultado === 'OK'){
					GotoMaquina(idMaquina);
				} else {
					$("#SaveResultContadores").html("Se ha producido un error al borrar los datos");
					$("#SaveResultContadores").attr('class', 'resultError');
					$("#SaveResultContadores").show();
					$("#SaveResultContadores").fadeOut(2500);
				}
			}
		);
	}
}

function SaveLiqMaquina(idMaquina){
	var validados = true;
	var bruto = $("#bruto").val();
	if (bruto == undefined || bruto == null || estaVacio(bruto)){
		validados = false;
	}
	var fallos = $("#fallos").val();
	var recaudacion = $("#recaudacion").val();
	var retencion = $("#retencion").val();
	if (retencion == undefined || retencion == null || estaVacio(retencion)){
		validados = false;
	}
	var porcentajeEst = $("#porcentajeEst").val();
	var pagoEst = $("#pagoEst").val();
	if (pagoEst == undefined || pagoEst == null || estaVacio(pagoEst)){
		validados = false;
	}
	var neto = $("#neto").val();
	if (neto == undefined || neto == null || estaVacio(neto)){
		validados = false;
	}
	if (validados){
		$.post(uriLiquidacion, {
			idMaquina : idMaquina,
			bruto : bruto,
			fallos : fallos,
			recaudacion : recaudacion,
			retencion : retencion,
			porcentajeEst : porcentajeEst,
			pagoEst : pagoEst,
			neto : neto,
			idUsuario : idUsuario
			},
			function( jsonObj ){
				if (jsonObj.Resultado === 'OK'){
					$("#SaveResultLiquidacion").html("Datos guardados correctamente");
					$("#SaveResultLiquidacion").attr('class', 'resultSuccess');
					$("#SaveResultLiquidacion").show();
					$("#SaveResultLiquidacion").fadeOut(2500);
				} else if (jsonObj.Resultado === 'ERROR_NO_CONTADORES'){
					$("#SaveResultLiquidacion").html("Por favor, guarde primero los contadores");
					$("#SaveResultLiquidacion").attr('class', 'resultWarning');
					$("#SaveResultLiquidacion").show();
					$("#SaveResultLiquidacion").fadeOut(4000);
				} else {
					$("#SaveResultLiquidacion").html("Se ha producido un error");
					$("#SaveResultLiquidacion").attr('class', 'resultError');
					$("#SaveResultLiquidacion").show();
					$("#SaveResultLiquidacion").fadeOut(2500);
				}
			}
		);
	} else {
		alert("Por favor, complete los campos obligatorios (Bruto, Retención, Pago al establecimiento y Neto)");
	}
}

function recalculaImportes(cambioBruto){
	var bruto = $("#bruto").val();
	if (cambioBruto){
		var fallos = $("#fallos").val();
		if (fallos != undefined && fallos != null && !estaVacio(fallos)){
			var nuevoBruto = Number(bruto) - Number(fallos);
			$("#bruto").val(nuevoBruto);
			bruto = nuevoBruto;
		}
	}
	var retencion = $("#retencion").val();
	var porcentajeEst = $("#porcentajeEst").val();
	if (bruto != undefined && bruto != null && !estaVacio(bruto)
			&& retencion != undefined && retencion != null && !estaVacio(retencion)
			&& porcentajeEst != undefined && porcentajeEst != null && !estaVacio(porcentajeEst)){
		var sinRetencion = Number(bruto) - Number(retencion);
		var pagoEst = (Number(sinRetencion) * Number(porcentajeEst))/100;
		var neto = Number(sinRetencion) - Number(pagoEst);
		$("#pagoEst").val(pagoEst);
		$("#neto").val(neto);
	}
}

function cambioFallos(){
	var fallos = $("#fallos").val();
	if (fallos != undefined && fallos != null){
		if (!estaVacio(fallos)){
			var recaudacion = $("#recaudacion").val();
			var nuevoBruto = Number(recaudacion) - Number(fallos);
			$("#bruto").val(nuevoBruto);
			recalculaImportes(false);
		} else {
			$("#fallos").val("0,00");
		}
	}
}

function SaveCredito(idEstablecimiento){
	var validados = true;
	var idCredito = $("#idCredito").val();
	var importeInicial = $("#importeInicial").val();
	if (importeInicial == undefined || importeInicial == null || estaVacio(importeInicial)){
		validados = false;
	} else {
		var importe = Number(importeInicial);
		if (importe <= 0){
			validados = false;
		}
	}
	var importePendiente = $("#importePendiente").val();
	if (importePendiente == undefined || importePendiente == null || estaVacio(importePendiente)){
		validados = false;
	}
	var cuota = $("#cuota").val();
	if (cuota == undefined || cuota == null || estaVacio(cuota)){
		validados = false;
	} else {
		var importeCuota = Number(cuota);
		if (importeCuota < 0){
			validados = false;
		}
	}
	if (validados){
		$.post(uriCredito, {
			idEstablecimiento : idEstablecimiento,
			idCredito : idCredito,
			importeInicial : importeInicial,
			importePendiente : importePendiente,
			cuota : cuota,
			idUsuario : idUsuario
			},
			function( jsonObj ){
				if (jsonObj.Resultado === 'OK'){
					$("#SaveResultCredito").html("Datos guardados correctamente");
					$("#SaveResultCredito").attr('class', 'resultSuccess');
					$("#SaveResultCredito").show();
					$("#SaveResultCredito").fadeOut(2500);
				} else if (jsonObj.Resultado === 'ERROR_NO_MAQUINAS'){
					$("#SaveResultCredito").html("No podemos conceder un crédito a un establecimiento sin máquinas");
					$("#SaveResultCredito").attr('class', 'resultWarning');
					$("#SaveResultCredito").show();
					$("#SaveResultCredito").fadeOut(4000);
				} else {
					$("#SaveResultCredito").html("Se ha producido un error");
					$("#SaveResultCredito").attr('class', 'resultError');
					$("#SaveResultCredito").show();
					$("#SaveResultCredito").fadeOut(2500);
				}
			}
		);
	} else {
		alert("Por favor, complete los campos obligatorios (Importe Inicial, Importe Pendiente y Cuota)\nEl importe inicial no puede ser 0.");
	}
}

function ShowRetencionTotal(){
	var fecha = new Date();
	alert("La retención acumulada de " + fecha.getFullYear() + " es: " + $("#acumulado").val() + " €");
}

var printWindow = null;

function ImprimirTicket(establecimiento, fecha){
	if (printWindow == null || printWindow.close){
		printWindow = window.open("printTicket.html", "TICKET_" + establecimiento + "_" + fecha, "");
	} else {
		printWindow.focus();
	}
}

function getInfoTicket(){
	return $("#printZone").html();
}

function MailTicket(idEstablecimiento, establecimiento, fecha){
	html = getInfoTicket();
	$.post(uriMailTicket, {
		idEstablecimiento : idEstablecimiento,
		establecimiento : establecimiento,
		fecha : fecha,
		html : html,
		idUsuario : idUsuario
		},
		function( jsonObj ){
			if (jsonObj.Resultado === 'OK'){
				$("#ResultEnvioMail").html("Email enviado");
				$("#ResultEnvioMail").attr('class', 'resultSuccess');
				$("#ResultEnvioMail").show();
				$("#ResultEnvioMail").fadeOut(2500);
			} else {
				$("#ResultEnvioMail").html("Error de envío");
				$("#ResultEnvioMail").attr('class', 'resultError');
				$("#ResultEnvioMail").show();
				$("#ResultEnvioMail").fadeOut(2500);
			}
		}
	);
}

function CreateOrUpdateMaquina(idMaquina){
	var validados = true;
	var nombre = $("#nombre").val();
	if (nombre == undefined || nombre == null || estaVacio(nombre)){
		validados = false;
	}
	var codigo = $("#codigo").val();
	if (codigo == undefined || codigo == null || estaVacio(codigo)){
		validados = false;
	}
	var porcentajeEst = $("#porcentajeEst").val();
	if (porcentajeEst == undefined || porcentajeEst == null || estaVacio(porcentajeEst)){
		validados = false;
	}
	var cargas = $("#cargas").val();
	if (cargas == undefined || cargas == null || estaVacio(cargas)){
		validados = false;
	}
	var tipoMaquina = $("#tipoMaquina").val();
	if (tipoMaquina == undefined || tipoMaquina == null || estaVacio(tipoMaquina)){
		validados = false;
	}
	var empresaMaquina = $("#empresaMaquina").val();
	if (empresaMaquina == undefined || empresaMaquina == null || estaVacio(empresaMaquina)){
		validados = false;
	}
	var establecimiento = $("#establecimiento").val();
	if (establecimiento == undefined || establecimiento == null || estaVacio(establecimiento)){
		validados = false;
	}
	if (validados){
		$.post(uriCreateOrUpdateMaquina, {
			idMaquina : idMaquina,
			nombre : nombre,
			codigo : codigo,
			porcentajeEst : porcentajeEst,
			cargas : cargas,
			tipoMaquina : tipoMaquina,
			empresaMaquina : empresaMaquina,
			establecimiento : establecimiento,
			idUsuario : idUsuario
			},
			function( jsonObj ){
				if (jsonObj.Resultado === 'OK'){
					$("#SaveResultAdminMaquina").html("Datos guardados correctamente");
					$("#SaveResultAdminMaquina").attr('class', 'resultSuccess');
					$("#SaveResultAdminMaquina").show();
					$("#SaveResultAdminMaquina").fadeOut(2500);
				} else {
					$("#SaveResultAdminMaquina").html("Se ha producido un error");
					$("#SaveResultAdminMaquina").attr('class', 'resultError');
					$("#SaveResultAdminMaquina").show();
					$("#SaveResultAdminMaquina").fadeOut(2500);
				}
			}
		);
	} else {
		alert("Por favor, complete todos los campos");
	}
}

function CreateOrUpdateEstablecimiento(idEstablecimiento){
	var validados = true;
	var nombre = $("#nombre").val();
	if (nombre == undefined || nombre == null || estaVacio(nombre)){
		validados = false;
	}
	var codigoEstablecimiento = $("#codigoEstablecimiento").val();
	if (codigoEstablecimiento == undefined || codigoEstablecimiento == null || estaVacio(codigoEstablecimiento)){
		validados = false;
	}
	var email = $("#email").val();
	if (email == undefined || email == null || estaVacio(email)){
		validados = false;
	}
	var bote = $("#bote").val();
	if (bote == undefined || bote == null || estaVacio(bote)){
		validados = false;
	}
	var empresaMaquina = $("#empresaMaquina").val();
	if (empresaMaquina == undefined || empresaMaquina == null || estaVacio(empresaMaquina)){
		validados = false;
	}
	if (validados){
		$.post(uriCreateOrUpdateEstablecimiento, {
			idEstablecimiento : idEstablecimiento,
			nombre : nombre,
			codigoEstablecimiento : codigoEstablecimiento,
			email : email,
			bote : bote,
			empresaMaquina : empresaMaquina
			},
			function( jsonObj ){
				if (jsonObj.Resultado === 'OK'){
					$("#SaveResultAdminEstablecimiento").html("Datos guardados correctamente");
					$("#SaveResultAdminEstablecimiento").attr('class', 'resultSuccess');
					$("#SaveResultAdminEstablecimiento").show();
					$("#SaveResultAdminEstablecimiento").fadeOut(2500);
				} else {
					$("#SaveResultAdminEstablecimiento").html("Se ha producido un error");
					$("#SaveResultAdminEstablecimiento").attr('class', 'resultError');
					$("#SaveResultAdminEstablecimiento").show();
					$("#SaveResultAdminEstablecimiento").fadeOut(2500);
				}
			}
		);
	} else {
		alert("Por favor, complete todos los campos");
	}
}