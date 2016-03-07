package sistemaDeVendas.services;

import java.io.Serializable;

import javax.inject.Inject;

import sistemaDeVendas.enuns.StatusPedido;
import sistemaDeVendas.exceptions.BusinessException;
import sistemaDeVendas.jpa.Transactional;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.repositories.PedidoRepository;

public class EmissaoPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private CadastroPedidoService pedidoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private PedidoRepository pedidoRepository;
	
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		pedido = pedidoService.salvar(pedido);
		
		if(pedido.isNotEmissivel()) {
			throw new BusinessException("pedido não pode ser emitido com o status " + pedido.getStatus().getDescricao() + ".");
		}
		estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		
		pedido = pedidoRepository.salvar(pedido);
		
		return pedido;
	}

}
