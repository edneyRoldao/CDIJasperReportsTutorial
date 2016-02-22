package cdi.conceitos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.enterprise.inject.Produces;

/**
 * 
 * Esta classe auxiliar não é um bean do CDI, porém ela possuí metodos com a annotation @Produces
 * 		Quando um método está anotado com @Produces, chamamos ele de um método produtor, estes métodas são reconhecidos e mapeados pelo CDI.	
 * 
 * 
 * @author EdneyRoldao
 *
 */
public class FormatadorDataUtil {
	
	@Produces
	public DateFormat formatoDeDataBrasil() {
		return new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
	}
	
	@Produces
	@QualificadorDataUS
	public DateFormat formatoDeDataUS() {
		return new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
	}

	

}
