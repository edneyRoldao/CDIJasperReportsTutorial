package sistemaDeVendas.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.repositories.PedidoRepository;

@Named
public class PedidoConverter implements Converter {
	
	@Inject
	PedidoRepository repository;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Pedido pedido = null;
		
		if(value != null) {
			Long id  = new Long(value);
			pedido = repository.buscarPorId(id);
		}
		
		return pedido;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value != null) {
			//return ((Pedido) value).getId().toString();
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		
		return "";
	}

}
