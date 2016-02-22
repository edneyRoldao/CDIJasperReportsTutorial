package sistemaDeVendas.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.model.Categoria;
import sistemaDeVendas.repositories.CategoriaRepository;

@Named
public class CategoriaConverter implements Converter{
	
	@Inject
	private CategoriaRepository categoriaRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria categoria = null;
		
		if(value != null) {
			Long id = new Long(value);
			categoria = categoriaRepository.buscarPorId(id);
		}
		
		return categoria;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			return ((Categoria) value).getId().toString();
		}
		
		return "";
	}

}
