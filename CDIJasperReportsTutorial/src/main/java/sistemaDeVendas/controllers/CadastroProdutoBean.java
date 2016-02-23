package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Categoria;
import sistemaDeVendas.model.Produto;
import sistemaDeVendas.repositories.CategoriaRepository;
import sistemaDeVendas.services.CadastroProdutoService;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Produto produto;
	private Categoria categoriaPai;
	private List<Categoria> categorias;
	private List<Categoria> subcategorias;

	@Inject
	private CategoriaRepository categoriaRepository;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;

	
	// Esso construtor é utilizado para evitar o nullPointer do objeto produto
	public CadastroProdutoBean() {
		limpar();
	}

	
	public void carregarSubcategorias() {
		subcategorias = categoriaRepository.buscarSubcategoriaPorIdPai(getCategoriaPai());
	}
	
	
	/**
	 * Este método vai carregar um lista de categorias para ser selecionada no
	 * componente selectOneMenu.
	 * 
	 * @return uma lista de categorias.
	 */
	public void inicializarObjetos() {
		System.out.println("Inicializando lista de categorias...");

		if (FacesUtil.isNotPostBack()) {
			categorias = categoriaRepository.buscarTodos();
		}
	}

	/**
	 * Método que cadastra um produto no banco de dados.
	 */
	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();
		FacesUtil.addInfoMessage("Produto cadastrado com sucesso !");
	}
	
	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subcategorias = new ArrayList<>();
	}

	// Getters and Setters
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public CategoriaRepository getCategoriaRepository() {
		return categoriaRepository;
	}

	public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Categoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

}
