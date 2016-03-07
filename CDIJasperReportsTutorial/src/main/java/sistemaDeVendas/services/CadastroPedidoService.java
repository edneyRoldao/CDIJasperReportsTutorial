package sistemaDeVendas.services;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import sistemaDeVendas.enuns.StatusPedido;
import sistemaDeVendas.exceptions.BusinessException;
import sistemaDeVendas.jpa.Transactional;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.repositories.PedidoRepository;

public class CadastroPedidoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private PedidoRepository repository;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		
		if(pedido.isNovo()) {
			pedido.setDataCriacao(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido.calcularValorTotal();
		
		if(pedido.isNotAlteravel()) {
			throw new BusinessException("Pedido não pode ser alterado no status " + pedido.getStatus().getDescricao() + ".");
		}
		
		if(pedido.getItens().isEmpty()) {
			throw new BusinessException("O pedido deve possuir pelo menos um item");
		}
		
		if(pedido.isValorNegativo()) {
			throw new BusinessException("O valor total do pedido não pode ser negativo");
		}
		
		pedido = repository.salvar(pedido);
		return pedido;
	}

}
