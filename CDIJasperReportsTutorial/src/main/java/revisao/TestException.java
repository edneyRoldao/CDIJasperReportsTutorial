package revisao;

import java.io.Serializable;

import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import sistemaDeVendas.exceptions.BusinessException;

@Named
@ViewScoped
public class TestException implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public void getFacesException() {
		throw new BusinessException("Houve um erro inesperado no sistema !");
	}
	
	public void getGeneralExceptions() {
		throw new RuntimeException();
	}
	
	public void getViewExpiredException() {
		throw new ViewExpiredException();
	}

}
