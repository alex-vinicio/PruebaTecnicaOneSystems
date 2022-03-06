// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#transacciones').DataTable();
  actualizarEmailDelUsuario();
});

function actualizarEmailDelUsuario() {
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


async function cargarUsuarios() {
  const request = await fetch('http://localhost:8080/api/Kardex/operacion/listar', {
    method: 'GET',
    headers: getHeaders()
  });
  const kardexs = await request.json();

  let listadoHtml = '';
  for (let kardex of kardexs) {
    //let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + kardex.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let usuarioHtml = '<tr><td>'+kardex.id+'</td><td>'+kardex.detalleOperacion+'</td><td>' + kardex.tipoOperacion + ' </td><td>' + kardex.fechaOperacion + '</td><td>'+kardex.cantidad
                    + '</td><td> $'+kardex.precioUnitario+'</td> <td> $'+kardex.saldoTotal+'</td> <td>'+kardex.idHojaKardex+'</td> </tr>';
    listadoHtml += usuarioHtml;
  }

document.querySelector('#transacciones tbody').outerHTML = listadoHtml;

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