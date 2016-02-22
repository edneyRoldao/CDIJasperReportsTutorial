package cdi.conceitos;

@QualificadorCriado
public class MensageiroSMS implements Mensageiro {

	@Override
	public void enviarMensagem(String mensagem) {

		System.out.println("Mensagem enviada por SMS: " + mensagem);
		
	}

}
