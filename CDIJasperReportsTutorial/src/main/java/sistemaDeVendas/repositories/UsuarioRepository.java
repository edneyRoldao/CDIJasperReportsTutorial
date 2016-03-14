package sistemaDeVendas.repositories;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import sistemaDeVendas.model.Usuario;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public Usuario buscarPorId(Long id) {
		return entityManager.find(Usuario.class, id);
	}

	public List<Usuario> listarVendedores() {
		return entityManager.createQuery("from Usuario", Usuario.class).getResultList();
	}

	public Usuario buscarPorEmail(String email) {
		Usuario user = null;

		try {
			user = entityManager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// Nenhum usuario encontrado com o e-mail informado
		}

		return user;
	}

}
