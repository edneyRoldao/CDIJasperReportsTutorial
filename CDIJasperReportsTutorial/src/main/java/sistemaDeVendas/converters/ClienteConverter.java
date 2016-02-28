package sistemaDeVendas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.model.Cliente;
import sistemaDeVendas.repositories.ClienteRepository;

@Named
public class ClienteConverter implements Converter {
	
	@Inject
	ClienteRepository repository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Cliente cliente = null;
		
		if(value != null) {
			Long id  = new Long(value);
			cliente = repository.buscarPorId(id);
		}
		
		return cliente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null) {
			return ((Cliente) value).getId().toString();
		}
		
		return "";
	}

}
