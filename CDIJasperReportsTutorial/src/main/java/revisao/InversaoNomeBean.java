package revisao;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean
public class InversaoNomeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String nomeInvertido;
	private int quantidadePalavras;
	
	
	public void inverterNome() {
		nomeInvertido = "";
		
		if(nome != null) {
			for(int i = nome.length() - 1; i >= 0; i--) {
				nomeInvertido += nome.charAt(i);
			}
		}
		quantidadePalavras = nomeInvertido.length();
		System.out.println(nomeInvertido);
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeInvertido() {
		return nomeInvertido;
	}

	public void setNomeInvertido(String nomeInvertido) {
		this.nomeInvertido = nomeInvertido;
	}

	public int getQuantidadePalavras() {
		return quantidadePalavras;
	}

	public void setQuantidadePalavras(int quantidadePalavras) {
		this.quantidadePalavras = quantidadePalavras;
	}

}
