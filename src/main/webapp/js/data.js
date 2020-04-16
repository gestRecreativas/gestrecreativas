var uriTextDataTable = "./js/datatable.json";
var uriEstablecimientosTotal = "./rms/restservice/restapi/getTotalEstablecimientos?idUsuario=" + idUsuario;
var uriEstablecimientosUsuario = "./rms/restservice/restapi/getEstablecimientosUsuario?idUsuario=" + idUsuario;
var uriMaquinasEstablecimiento = "./rms/restservice/restapi/getMaquinasEstablecimiento?idEstablecimiento=";
var uriAdminMaquinas = "./rms/restservice/restapi/getAdminMaquinas?idUsuario=" + idUsuario;
var uriAdminEstablecimientos = "./rms/restservice/restapi/getAdminEstablecimientos?idUsuario=" + idUsuario;
var uriContadores = "./rms/restservice/restapi/postContador";
var uriLiquidacion = "./rms/restservice/restapi/postLiquidacion";
var uriCredito = "./rms/restservice/restapi/postCredito";
var uriDelete = "./rms/restservice/restapi/postDeleteContadores";
var uriMailTicket = "./rms/restservice/restapi/postMailTicket";
var uriCreateOrUpdateMaquina = "./rms/restservice/restapi/postCreateOrUpdateMaquina";
var uriCreateOrUpdateEstablecimiento = "./rms/restservice/restapi/postCreateOrUpdateEstablecimiento";

//Cabecera de la tabla de establecimientos
var columnSetEstablecimiento = [{ title: 'Establecimiento' },{ title: 'Código' }];
//Cabecera de la tabla de maquinas
var columnSetMaquinas = [{ title: 'Máquina' },{ title: 'Código' },{ title: 'Arqueo' }];
//Cabecera de la tabla de adminMaquinas
var columnSetAdminMaquinas = [{ title: 'Máquina' },{ title: 'Establecimiento' }];
//Cabecera de la tabla de adminEstablecimientos
var columnSetAdminEstablecimientos = [{ title: 'Establecimiento' }];

var loadtxt="Cargando datos...";
var selectTxt ="Seleccionar";
var errorMessage = "No hemos podido recuperar la información, es posible que el servicio no esté disponible, por favor intentelo mas tarde.";