$(document).ready(function() {
    // on ready
 });
 
 
 async function registrarTrnsacciones() {
   let datos = {};
   datos.detalleOperacion = document.getElementById('txtDetalleOperacion').value;
   datos.tipoOperacion = document.getElementById('txtTipoOperacion').value;
   datos.fechaOperacion = new Date();
   datos.cantidad = document.getElementById('txtCantidad').value;
   datos.precioUnitario = document.getElementById('txtPrecioUnitario').value;
   datos.saldoTotal = 0;
   datos.idHojaKardex = document.getElementById('txtIdHojaKardex').value;

 
   const request = await fetch('http://localhost:8080/api/Kardex/operacion/transacciones', {
     method: 'POST',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
     body: JSON.stringify(datos)
   });
 
   const respuesta = await request.text();

   if (respuesta.toLocaleUpperCase() == "OK") {
     alert("La transaccion del Kardex fue creada con exito!");
     window.location.href = 'transacciones.html'  
   } else {
     alert("problemas: " + respuesta);
   }
 
 }