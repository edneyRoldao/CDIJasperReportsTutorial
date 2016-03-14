package sistemaDeVendas.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class SpringSecurity {
	
	@Inject
	private ExternalContext externalContext;

	public String getUserName() {
		String name = null;
		SystemUser userLogged = getUserLogged();
		
		if(userLogged != null) {
			name = userLogged.getUsuario().getNome();
		}
		
		return name;
	}

	private SystemUser getUserLogged() {
		SystemUser user = null;
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

		if(auth != null && auth.getPrincipal() != null) {
			user  = (SystemUser) auth.getPrincipal();
		}
		
		return user;
	}
	
	public boolean isEmitirPedidoPermitido() {
		return externalContext.isUserInRole("ADMIN") || externalContext.isUserInRole("SELLER");
	}
	
	public boolean isCancelarPedidoPermitido() {
		return externalContext.isUserInRole("ADMIN") || externalContext.isUserInRole("SELLER");
	}
	
}
