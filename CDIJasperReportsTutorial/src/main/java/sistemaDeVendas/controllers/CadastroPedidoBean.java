package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import sistemaDeVendas.enuns.FormaPagamento;
import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Cliente;
import sistemaDeVendas.model.EnderecoEntrega;
import sistemaDeVendas.model.ItemPedido;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.model.Produto;
import sistemaDeVendas.model.Usuario;
import sistemaDeVendas.repositories.ClienteRepository;
import sistemaDeVendas.repositories.ProdutoRepository;
import sistemaDeVendas.repositories.UsuarioRepository;
import sistemaDeVendas.services.CadastroPedidoService;
import sistemaDeVendas.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// Atributos
	@Inject
	private ClienteRepository clienteRepository;

	@Inject
	private CadastroPedidoService service;

	@Inject
	UsuarioRepository usuarioRepository;
	
	@Inject
	ProdutoRepository produtoRepository;

	private String sku;
	private Pedido pedido;
	private Produto produtoLinhaEditavel;
	private List<Usuario> listaVendedores;

	// Construtor
	public CadastroPedidoBean() {
		limpar();
	}

	// Métodos
	public void salvarPedido() {
		pedido = service.salvar(pedido);
		FacesUtil.addInfoMessage("Pedido cadastrado com sucesso !");
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			listaVendedores = usuarioRepository.listarVendedores();
			pedido.adicionarItemVazio();
			recalcularPedido();
		}
	}
	
	public void recalcularPedido() {
		if(pedido != null) 
			pedido.calcularValorTotal();
	}
	

	public List<Cliente> completarCliente(String nome) {
		return clienteRepository.listarPorNome(nome);
	}

	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}
	
	public boolean isEditandoPedido() {
		return pedido.getId() != null;
	}
	
	public List<Produto> completarProduto(String nome) {
		return produtoRepository.buscarPorNome(nome);
	}
	
	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = pedido.getItens().get(0);
		
		if(produtoLinhaEditavel != null) {
			if(existeItemComProduto(produtoLinhaEditavel)) {
				FacesUtil.addInfoMessage("Já existe um item no pedido com o produto informado");
			}else {
				item.setProduto(produtoLinhaEditavel);
				item.setValorUnitario(produtoLinhaEditavel.getValorUnitario());
				pedido.adicionarItemVazio();
				produtoLinhaEditavel = null;
				sku = null;
				pedido.calcularValorTotal();
			}
		}
	}
	
	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for(ItemPedido item : getPedido().getItens()) {
			if(produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}
		return existeItem;
	}
	
	public void carregarProdutoPorSku() {
		if(StringUtils.isNotEmpty(sku)) {
			produtoLinhaEditavel = produtoRepository.buscarPorSKU(sku);
			carregarProdutoLinhaEditavel();
		}
	}
	

	// Getters and Setters
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getListaVendedores() {
		return listaVendedores;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}