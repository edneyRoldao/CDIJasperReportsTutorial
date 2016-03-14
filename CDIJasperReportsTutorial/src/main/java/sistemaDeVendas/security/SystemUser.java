package sistemaDeVendas.security;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import sistemaDeVendas.model.Usuario;

public class SystemUser extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	Usuario usuario;

	public SystemUser(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	// Getter
	public Usuario getUsuario() {
		return usuario;
	}

}
