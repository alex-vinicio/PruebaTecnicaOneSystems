// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#producto').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


async function cargarUsuarios() {
  const request = await fetch('http://localhost:8080/api/producto/listarTodos', {
    method: 'GET',
    headers: getHeaders()
  });
  const productos = await request.json();

  let listadoHtml = '';
  let index = 1;
  for (let producto of productos) {
    //let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + producto.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let usuarioHtml = '<tr><td>'+index+'</td><td>'+producto.nombreProducto+'</td><td>' + producto.detalleProducto + ' </td><td>' + producto.categoria + '</td></tr>';
    listadoHtml += usuarioHtml;
    index++;
  }

document.querySelector('#producto tbody').outerHTML = listadoHtml;

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