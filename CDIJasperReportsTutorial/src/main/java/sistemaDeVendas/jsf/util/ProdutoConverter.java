package sistemaDeVendas.jsf.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.model.Produto;
import sistemaDeVendas.repositories.ProdutoRepository;

@Named
public class ProdutoConverter implements Converter{
	
	@Inject
	private ProdutoRepository ProdutoRepository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto produto = null;
		
		if(value != null) {
			Long id = new Long(value);
			produto = ProdutoRepository.buscarPorId(id);
		}
		
		return produto;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			//return ((Produto) value).getId().toString();
			Produto produto  = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}
		
		return "";
	}

}
