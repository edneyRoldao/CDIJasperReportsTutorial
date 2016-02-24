package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import sistemaDeVendas.filters.ProdutoFilter;
import sistemaDeVendas.model.Produto;
import sistemaDeVendas.repositories.ProdutoRepository;

@ManagedBean
@ViewScoped
public class PesquisaProdutosBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoRepository repository;
	
	//atributos não injetados
	private List<Produto> produtosFiltrados;
	private ProdutoFilter filter;

	//Construtor
	public PesquisaProdutosBean() {
		filter = new ProdutoFilter();
	}
	
	
	public void pesquisar() {
		produtosFiltrados = repository.filtrados(filter);
	}
	
	
	//Getters and Setters
	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}
	
	public ProdutoFilter getFilter() {
		return filter;
	}
	
}
