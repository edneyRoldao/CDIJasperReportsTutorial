package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.enuns.StatusPedido;
import sistemaDeVendas.filters.PedidoFilter;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.repositories.PedidoRepository;

@Named
@ViewScoped
public class PesquisaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PedidoRepository repository;

	private PedidoFilter filter;
	private List<Pedido> listaFiltradaPedidos;

	// Construtor
	public PesquisaPedidosBean() {
		filter = new PedidoFilter();
		listaFiltradaPedidos = new ArrayList<>();
	}

	// Métodos
	public void buscarListaFiltrada() {
		listaFiltradaPedidos = repository.filtrarPedidos(filter);
	}

	public StatusPedido[] getStatuses() {
		return StatusPedido.values();
	}

	// Getters and Setters
	public List<Pedido> getListaFiltradaPedidos() {
		return listaFiltradaPedidos;
	}

	public PedidoFilter getFilter() {
		return filter;
	}

}
