package sistemaDeVendas.jsf.util;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sistemaDeVendas.exceptions.BusinessException;

public class JSFExceptionHandler extends ExceptionHandlerWrapper{
	
	// implementando registro de log de erros da aplicação em um arquivo externo com o Log4J e commons-logging
	private static Log log = LogFactory.getLog(JSFExceptionHandler.class);

	private ExceptionHandler wrapped;

	public JSFExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		while(events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
		
			Throwable exception = context.getException();
			BusinessException businessException = getBusinessException(exception);
			
			boolean handled = false;

			try{
				if(exception instanceof ViewExpiredException) {
					handled = true;
					redirect("/");
				}else if(businessException != null) {
					handled = true;
					FacesUtil.addErrorMessage(businessException.getMessage());
				}else {
					handled = true;
					log.error("Erro de sistema: " + exception.getMessage(), exception);
					redirect("/erro.xhtml");
				}
				
			}finally {
				if(handled) events.remove();
			}
		}
		
		getWrapped().handle();
	}

	private BusinessException getBusinessException(Throwable exception) {
		if(exception instanceof BusinessException) {
			return (BusinessException) exception;
		}else if(exception.getCause() != null) {
			return getBusinessException(exception.getCause());
		}
		
		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext(); 
			String contextPath = externalContext.getRequestContextPath();
	
			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();

		} catch (IOException e) {
			throw new FacesException(e);
		}
	}
	
}
