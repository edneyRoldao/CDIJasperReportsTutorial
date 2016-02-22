package revisao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Se não definirmos um escopo padrao para o nosso ele será @Dependent
 * Com CDI os pacotes que devemos importar são do javax.enterprise.context
 *		
 *	@Dependent: tempo de vida curto, ele é instanciado e já é eliminado.
 *		Quando o bean é dependent, ele assume o escopo da classe que injeta ele.
 *
 *	Quando usamos o escopo de Session, temos que usar implements serializable, pois este objeto vai percorre por todo o sistema
 *	
 *	O WELD cria um proxy das nossas classes.
 *
 * 	Não exite viewScoped no CDI
 * 
 * @author EdneyRoldao
 *
 */
@Named("firstBean")
@RequestScoped
public class TestCDIWorking {
	
	@Inject
	private Calculadora calculadora;

	public double getPreco() {
		return calculadora.calcular(10, 23.35);
	}
	
}
