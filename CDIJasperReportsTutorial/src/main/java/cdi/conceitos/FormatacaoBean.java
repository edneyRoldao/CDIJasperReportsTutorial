package cdi.conceitos;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Esta bean do CDI é um exemplo de como utilizar métodos que retornam o tipo de dado que está injetado no nosso bean.
 * 
 * 	Explicando: Temos aqui um atributo do tipo DateFormat injetado, porém a forma como ele deve formatar a data está separada em um formatadorUtil.
 * 				Este processo segue o mesmo conceito das interfaces, porém, aqui o container vai procurar por um método que esteja anotado com
 * 				@Produces e que retorna o tipo de dado igual ao tipo do atributo injetado.
 * 				Se tivermos dois métodos que retornam o mesmo tipo e anotados com @Produces, temos que usar um qualificador.
 * 
 * @author EdneyRoldao
 *
 */
@Named
@ViewScoped
public class FormatacaoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Inject
	@QualificadorDataUS
	private DateFormat formataData;
	
	private Date dataInformada;
	private String dataFormatada;
	
	
	public void enviar() {
		dataFormatada = formataData.format(dataInformada);
	}

	
	public Date getDataInformada() {
		return dataInformada;
	}

	public void setDataInformada(Date dataInformada) {
		this.dataInformada = dataInformada;
	}

	public String getDataFormatada() {
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

}
