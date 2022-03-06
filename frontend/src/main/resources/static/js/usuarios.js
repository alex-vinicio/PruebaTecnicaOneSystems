// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


async function cargarUsuarios() {
  const request = await fetch('http://localhost:8080/api/usuario/listarTodos', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();

  let listadoHtml = '';
  let index = 1;
  for (let usuario of usuarios) {
    let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.ciUsuarioEcuador + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
    let usuarioHtml = '<tr><td>'+index+'</td><td>'+usuario.correoUsuario+'</td><td>' + usuario.nombreUsuario + ' </td><td>' + usuario.apellidoUsuario + '</td><td>'+telefonoTexto
                    + '</td><td>' + botonEliminar + '</td></tr>';
    listadoHtml += usuarioHtml;
    index++;
  }

document.querySelector('#usuarios tbody').outerHTML = listadoHtml;

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