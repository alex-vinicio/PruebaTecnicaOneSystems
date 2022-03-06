$(document).ready(function() {
    // on ready
 });
 
 
 async function registrarKardex() {
   let datos = {};
   datos.ubicacion = document.getElementById('txtUbicacion').value;
   datos.nombreProveedor = document.getElementById('txtNombreProveedor').value;
   datos.referencia = document.getElementById('txtReferencia').value;
   datos.metodo = document.getElementById('txtMetodo').value;
   datos.unidad = document.getElementById('txtUnidad').value;
   datos.cantidadMinima = 0;
   datos.cantidadMaxima = document.getElementById('txtCantidadMaxima').value;
   datos.id_producto = document.getElementById('txtIdProducto').value;
 
   const request = await fetch('http://localhost:8080/api/Kardex/registrar', {
     method: 'POST',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
     body: JSON.stringify(datos)
   });
 
   const respuesta = await request.text();
   if (respuesta == 'OK') {
     alert("El Kardex fue creada con exito!");
     window.location.href = 'kardex.html'  
   } else {
     alert("problemas: " + respuesta);
   }
 
 }