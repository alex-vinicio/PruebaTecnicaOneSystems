$(document).ready(function() {
   // on ready
});


async function registrarUsuario() {
  let datos = {};
  datos.nombreUsuario = document.getElementById('txtNombre').value;
  datos.apellidoUsuario = document.getElementById('txtApellido').value;
  datos.correoUsuario = document.getElementById('txtEmail').value;
  datos.contrasenia = document.getElementById('txtPassword').value;
  datos.ciUsuarioEcuador = document.getElementById('txtCi').value;
  datos.rolUsuario = document.getElementById('txtRol').value;
  datos.numeroTarjeta = document.getElementById('txtTarjeta').value;
  datos.telefono = document.getElementById('txtTelefono').value

  let repetirPassword = document.getElementById('txtRepetirPassword').value;

  if (repetirPassword != datos.contrasenia) {
    alert('La contraseña que escribiste es diferente.');
    return;
  }

  const request = await fetch('http://localhost:8080/api/usuario/registrar', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });

  const respuesta = await request.text();
  console.log(respuesta);
  if (respuesta == '') {
    alert("La cuenta fue creada con exito!");
    window.location.href = 'usuarios.html'  
  } else {
    alert("Su cedula o correo esta incorrecta o repetida.");
  }

}
