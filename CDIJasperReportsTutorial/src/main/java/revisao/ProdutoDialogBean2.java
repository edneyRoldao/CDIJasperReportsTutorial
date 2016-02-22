package revisao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProdutoDialogBean2 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private List<String> produtos = new ArrayList<>();
	private String produtoSelected;
	
	
	public ProdutoDialogBean2() {
		produtos.add("Arroz");
		produtos.add("Feijão");
		produtos.add("Batata");
		produtos.add("Frango");
	}
	
	public void remove() {
		produtos.remove(produtoSelected);
	}
	
	public List<String> getProdutos() {
		return produtos;
	}

	public String getProdutoSelected() {
		return produtoSelected;
	}

	public void setProdutoSelected(String produtoSelected) {
		this.produtoSelected = produtoSelected;
	}
	
}
