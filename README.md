# PruebaTecnicaOneSystems
Desarrollo de un sistema kardex, en el backend con springboot y frontend con HTML5, CSS y Javascript (JQUery,Ajax).
Para el proceso cronologico de añadir productos a los Cardex es la siguiente:

-Puertos del frontend: http://localhost:8088/
-puerto del backend http://localhost:8080/
-acceso al Swagger para ver las API http://localhost:8080/swagger-ui
-acceso a BD en memoria H2 http://localhost:8080/h2-console     //credenciales      alex    admin
-se puede acceder a la configuracion en aplication.properties

-Primero crear o registrar una cuenta en la dirrecion http://localhost:8088/usuarios.html
-una vez creado se procede a logearse con la cuenta creada http://localhost:8088/login.html
*Karcex
-Para el funcionamiento de transacciones en Kardex se debe hacer:
-- Crear un producto en la URL http://localhost:8088/registrarproductos.html
-- Crear una Hoja de Kardex con el ID_producto disponibles o creados con la URL http://localhost:8088/registrarkardex.html
-- una vez creada la hoja Kardex, se procede a añadar operaciones/transacciones al Kardex para esto de tiene:
--- para añadir un producto a un Kardex, se debe ingresar el Id del kardex disponible, segun el producto que contenga el Kardex
--- esta validado el stock para acceder al registro es con la URL http://localhost:8088/registrartransaccion.html
