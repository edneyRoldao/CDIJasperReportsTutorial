package revisao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProdutoDialogBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private List<String> produtos = new ArrayList<>();
	private String nomeProduto;
	
	
	public void addProduto() {
		produtos.add(nomeProduto);
		nomeProduto = null;
	}

	
	public List<String> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<String> produtos) {
		this.produtos = produtos;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
}
