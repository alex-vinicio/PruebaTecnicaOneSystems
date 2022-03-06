$(document).ready(function() {
    // on ready
 });
 
 
 async function registrarProducto() {
   let datos = {};
   datos.nombreProducto = document.getElementById('txtNombre').value;
   datos.detalleProducto = document.getElementById('txtDetalle').value;
   datos.categoria = document.getElementById('txtCategoria').value;
   datos.fechaRegistro = new Date();
 
   const request = await fetch('http://localhost:8080/api/producto/registrar', {
     method: 'POST',
     headers: {
       'Accept': 'application/json',
       'Content-Type': 'application/json'
     },
     body: JSON.stringify(datos)
   });
 
   const respuesta = await request.text();
   if (respuesta == '') {
     alert("El producto fue creada con exito!");
     window.location.href = 'producto.html'  
   } else {
     alert("Problemas del servidor.");
   }
 
 }