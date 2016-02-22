package revisao;

import java.io.Serializable;

public class Calculadora implements Serializable{

	private static final long serialVersionUID = 1L;

	public double calcular(int qtd, double valor) {
		return qtd * valor;
	}
	
}
