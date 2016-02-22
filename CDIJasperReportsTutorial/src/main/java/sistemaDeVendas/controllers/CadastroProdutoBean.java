package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Categoria;
import sistemaDeVendas.model.Produto;
import sistemaDeVendas.repositories.CategoriaRepository;

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

	// Esso construtor é utilizado para evitar o nullPointer do objeto produto
	public CadastroProdutoBean() {
		produto = new Produto();
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
		System.out.println("Categoria selecionada: " + categoriaPai.getDescricao());
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
