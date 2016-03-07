package sistemaDeVendas.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.annotations.PedidoEdicao;
import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.services.CancelamentePedidoService;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelamentePedidoService cancelamentePedidoService;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	
	public void cancelarPedido() {
		pedido = cancelamentePedidoService.cancelar(pedido);
		pedidoEvent.fire(new PedidoAlteradoEvent(pedido));
		
		FacesUtil.addInfoMessage("Pedido cancelado com sucesso !");
	}
	

}
