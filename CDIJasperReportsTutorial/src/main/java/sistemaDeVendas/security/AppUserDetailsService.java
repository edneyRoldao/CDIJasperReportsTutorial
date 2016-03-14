package sistemaDeVendas.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import sistemaDeVendas.model.Grupo;
import sistemaDeVendas.model.Usuario;

public class AppUserDetailsService implements UserDetailsService {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("mySQLConfig");
	private static EntityManager em = factory.createEntityManager();

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// Eu não sei qual é o erro, não estou conseguindo recuperar um
		// BeanManager por meio de lookup (erro no JNDI)
		// UsuarioRepository userRepository =
		// CDIServiceLocator.getBean(UsuarioRepository.class);
		Usuario usuario = buscarPorEmail(email);

		SystemUser user = null;

		if (usuario != null) {
			user = new SystemUser(usuario, getGroups(usuario));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGroups(Usuario usuario) {

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}

		return authorities;
	}

	private Usuario buscarPorEmail(String email) {
		Usuario user = null;

		try {
			user = em.createQuery("from Usuario where lower(email) = :email", Usuario.class)
					.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// Nenhum usuario encontrado com o e-mail informado
		}

		return user;
	}

}
