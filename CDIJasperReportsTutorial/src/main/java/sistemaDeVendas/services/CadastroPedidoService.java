package sistemaDeVendas.services;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import sistemaDeVendas.enuns.StatusPedido;
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
		pedido = repository.salvar(pedido);
		
		return pedido;
	}

}
