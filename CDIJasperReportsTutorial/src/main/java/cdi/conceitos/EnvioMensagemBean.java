package cdi.conceitos;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class EnvioMensagemBean {

	
	@Inject
	@QualificadorCriado
	private Mensageiro mensageiro;
	
	private String texto;
	
	
	public void enviarMensagem() {
		mensageiro.enviarMensagem(texto);
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}
