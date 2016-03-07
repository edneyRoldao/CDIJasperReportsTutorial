package sistemaDeVendas.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.annotations.PedidoEdicao;
import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.services.EmissaoPedidoService;

@Named
@RequestScoped
public class EmissaoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EmissaoPedidoService emissaoService;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;

	
	public void emitirPedido() {
		pedido.removerItemVazio();
		
		try {
			pedido = emissaoService.emitir(pedido);
			
			//Lançando um event do CDI para atualizar o Objeto Pedido injetado na Bean CadastroPedidoBean
			pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(pedido));
			
			FacesUtil.addInfoMessage("Pedido emitido com sucesso !");
		}finally {
			pedido.adicionarItemVazio();
		}

	}

}
