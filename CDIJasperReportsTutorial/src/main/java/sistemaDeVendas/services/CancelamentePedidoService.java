package sistemaDeVendas.services;

import java.io.Serializable;

import javax.inject.Inject;

import sistemaDeVendas.enuns.StatusPedido;
import sistemaDeVendas.exceptions.BusinessException;
import sistemaDeVendas.jpa.Transactional;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.repositories.PedidoRepository;

public class CancelamentePedidoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository pedidoRepository;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Pedido cancelar(Pedido pedido) {
		pedido = pedidoRepository.buscarPorId(pedido.getId());
		
		if(pedido.isNotCancelavel()) {
			throw new BusinessException("Pedido não pode ser cancelado no status " + pedido.getStatus().getDescricao() + ".");
		}
		
		if(pedido.isEmitido()) {
			estoqueService.retornarItensEstoque(pedido);
		}
		
		pedido.setStatus(StatusPedido.CANCELADO);
		
		pedido = pedidoRepository.salvar(pedido);
		
		return pedido;
	}
	
	
}
