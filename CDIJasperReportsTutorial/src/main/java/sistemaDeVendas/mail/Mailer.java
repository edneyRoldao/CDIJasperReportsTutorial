package sistemaDeVendas.mail;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.outjected.email.api.MailMessage;
import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.MailMessageImpl;

@RequestScoped
public class Mailer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SessionConfig config;
	
	public MailMessage newMessage() {
		return new MailMessageImpl(config);
	}

}
