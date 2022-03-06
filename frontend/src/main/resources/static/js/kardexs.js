// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#kardexs').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


async function cargarUsuarios() {
  const request = await fetch('http://localhost:8080/api/Kardex/listar', {
    method: 'GET',
    headers: getHeaders()
  });
  const kardexs = await request.json();

  let listadoHtml = '';
  for (let kardex of kardexs) {
    //let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + kardex.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let usuarioHtml = '<tr><td>'+kardex.id+'</td><td>'+kardex.ubicacion+'</td><td>' + kardex.nombreProveedor + ' </td><td>' + kardex.referencia + '</td><td>'+kardex.metodo
                    + '</td><td>'+kardex.unidad+'</td> <td>'+kardex.cantidadMaxima+'</td> <td>'+kardex.id_producto+'</td> </tr>';
    listadoHtml += usuarioHtml;
  }

document.querySelector('#kardexs tbody').outerHTML = listadoHtml;

}

function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'crossDomain': true,
     //'Authorization': localStorage.token
   };
}

async function eliminarUsuario(id) {

  if (!confirm('¿Desea eliminar este usuario?')) {
    return;
  }

 const request = await fetch('http://localhost:8080/api/usuario/eliminar/' + id, {
    method: 'DELETE',
    headers: getHeaders()
  });

  location.reload()
}