package sistemaDeVendas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.model.Usuario;
import sistemaDeVendas.repositories.UsuarioRepository;

@Named
public class UsuarioConverter implements Converter {
	
	@Inject
	UsuarioRepository repository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		
		Usuario user = new Usuario();
		
		if(value != null) {
			Long id = new Long(value);
			user = repository.buscarPorId(id);
		}
		
		return user;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null) {
			return ((Usuario) value).getId().toString();
		}
		
		return "";
	}
	

}
