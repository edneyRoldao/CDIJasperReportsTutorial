package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.filters.ProdutoFilter;
import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Produto;
import sistemaDeVendas.repositories.ProdutoRepository;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository repository;

	// atributos não injetados
	private List<Produto> produtosFiltrados;
	private ProdutoFilter filter;
	private Produto produtoSelecionado;

	// Construtor
	public PesquisaProdutosBean() {
		filter = new ProdutoFilter();
	}

	public void pesquisar() {
		produtosFiltrados = repository.filtrados(filter);
	}
	
	public void remover() {
		repository.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
		FacesUtil.addInfoMessage("Produto " + produtoSelecionado.getSku() + " removido com sucesso !");
	}

	// Getters and Setters
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFilter() {
		return filter;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
