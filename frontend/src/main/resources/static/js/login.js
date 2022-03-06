$(document).ready(function() {
   // on ready
});


async function iniciarSesion() {
  let datos = {};
  var correo = document.getElementById('txtEmail').value;
  var contrasenia  = document.getElementById('txtPassword').value;

  const request = await fetch('http://localhost:8080/api/login/{correo}/{contrasenia}?correo='+correo+"&contrasenia="+contrasenia, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      crossDomain: true,
      'Content-Type': 'application/json'
    },
  });

  const respuesta = await request.text();
  if (respuesta == 'OK') {
    localStorage.token = respuesta;
    localStorage.email = correo;
    window.location.href = 'usuarios.html'
  } else {
    alert("Las credenciales son incorrectas. Por favor intente nuevamente.");
  }

}
