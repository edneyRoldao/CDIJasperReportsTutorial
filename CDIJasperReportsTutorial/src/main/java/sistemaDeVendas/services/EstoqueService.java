package sistemaDeVendas.services;

import java.io.Serializable;

import javax.inject.Inject;

import sistemaDeVendas.jpa.Transactional;
import sistemaDeVendas.model.ItemPedido;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.repositories.PedidoRepository;

public class EstoqueService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void baixarItensEstoque(Pedido pedido) {
		pedido = pedidoRepository.buscarPorId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = pedidoRepository.buscarPorId(pedido.getId());
		
		for(ItemPedido item : pedido.getItens()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
	}

}
