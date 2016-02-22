package revisao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class MessagesTestBean {

	public void addMessage() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Message header - MSG comp", "Message complete.");
		context.addMessage("destinoErroBind", msg);
	}

	public void addMessageFromGrowl() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message header - growl comp", "Message complete.");
		context.addMessage("destinoErroBind2", msg);
	}
	
	
}
