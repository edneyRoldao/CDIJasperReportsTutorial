package sistemaDeVendas.controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sistemaDeVendas.enuns.FormaPagamento;
import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.model.Cliente;
import sistemaDeVendas.model.EnderecoEntrega;
import sistemaDeVendas.model.Pedido;
import sistemaDeVendas.model.Usuario;
import sistemaDeVendas.repositories.ClienteRepository;
import sistemaDeVendas.repositories.UsuarioRepository;
import sistemaDeVendas.services.CadastroPedidoService;

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

	private Pedido pedido;
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

}
