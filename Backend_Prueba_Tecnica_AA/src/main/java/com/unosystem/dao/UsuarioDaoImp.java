package com.unosystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.unosystem.entities.Usuario;
import com.unosystem.services.VeriticacionLista;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    // EntityManager permite interactuar con la BD mediante metodos conocidos o
    // consultar por query propia
    @PersistenceContext
    EntityManager entityManager;

    // la anotacion pemrite sobreescribir la funcion heredada
    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        List<Usuario> listaUsuarios = null;
        String query = "FROM Usuario";

        try {
            listaUsuarios = VeriticacionLista.castList(Usuario.class, entityManager.createQuery(query).getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listaUsuarios;
    }

    @Override
    public void eliminarUsuario(String ci) {
        // se busca al usuario mediante la funcion EntityManager asignada por defecto
        // con la cedula enviada por parametro

        try {
            Usuario usuario = entityManager.find(Usuario.class, ci);
            // EntityManager tambien dispone de un metodo para eliminar el usuario

            if (usuario != null) {
                entityManager.remove(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        try {
            // se guarda el usuario en la base de datos con metodos del EntityManager
            entityManager.merge(usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Usuario validarUsuarioConCredencial(String correo, String contrasenia) {
        List<Usuario> listaU = null;
        Usuario usuarioEncontrado = null;
        // no se añade directamente la variable en Query, evitando asi inyeccion SQL
        String query = "FROM Usuario WHERE correo = :email";

        try {
            // buscar el usuario y añadir a la lista con el correo enviado en el formulario
            listaU = VeriticacionLista.castList(Usuario.class,
                    entityManager.createQuery(query).setParameter("email", correo).getResultList());

            // retornar null si la lista esta vacia
            if (listaU.isEmpty()) {
                return null;
            } else {
                // retorna null si encuentra mas de 1 usuario con el mismo correo
                if (listaU.size() == 1) {
                    // sacar la contraseña de usuario encontrado e instanciar Argon2, libreria de
                    // encriptacion
                    String passwordHashed = listaU.get(0).getContrasenia();
                    usuarioEncontrado = listaU.get(0);
                    Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

                    // copara si la contraseña enviada por el formulario es igual a la encriptada
                    // por Argon2 en la base de datos
                    if (argon2.verify(passwordHashed, contrasenia)) {
                        return usuarioEncontrado;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarioEncontrado;
    }

}
