package sistemaDeVendas.mail;

import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.outjected.email.api.SessionConfig;
import com.outjected.email.impl.SimpleMailConfig;

public class MailConfigProducer {
	
	@Produces
	@ApplicationScoped
	public SessionConfig getMailConfig() throws IOException {

		Properties prop = new Properties();
		prop.load(getClass().getResourceAsStream("/mail.properties"));
		
		SimpleMailConfig config = new SimpleMailConfig();
		config.setServerHost(prop.getProperty("mail.server.host"));
		config.setServerPort(Integer.parseInt(prop.getProperty("mail.server.port")));
		config.setEnableSsl(Boolean.parseBoolean(prop.getProperty("mail.enable.ssl")));
		config.setAuth(Boolean.parseBoolean(prop.getProperty("mail.auth")));
		config.setUsername(prop.getProperty("mail.username"));
		config.setPassword(prop.getProperty("mail.password"));
		
		return config;
	}

}
