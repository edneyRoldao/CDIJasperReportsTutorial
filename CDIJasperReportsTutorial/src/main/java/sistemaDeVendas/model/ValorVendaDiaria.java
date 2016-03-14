package sistemaDeVendas.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Os atributos desse classe são utilizados para representar os nosso Alias para consulta no banco
 * @author Edney Roldao
 *
 */
public class ValorVendaDiaria implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date data;
	private BigDecimal valor;

	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
