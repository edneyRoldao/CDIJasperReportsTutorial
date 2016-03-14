package sistemaDeVendas.controllers;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import sistemaDeVendas.annotations.PedidoEdicao;
import sistemaDeVendas.jsf.util.FacesUtil;
import sistemaDeVendas.mail.Mailer;
import sistemaDeVendas.model.Pedido;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	@PedidoEdicao
	Pedido pedido;
	
	@Inject
	Mailer mailer;
	
	
	public void enviarPedido() {
		MailMessage message = mailer.newMessage();
		message.to(pedido.getCliente().getEmail())
		.subject("Pedido " + pedido.getId())
		.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/sistemaDeVendas/emails/pedido.template")))
		.send();
		
		FacesUtil.addInfoMessage("Email enviado com sucesso !");
	}
	

}
