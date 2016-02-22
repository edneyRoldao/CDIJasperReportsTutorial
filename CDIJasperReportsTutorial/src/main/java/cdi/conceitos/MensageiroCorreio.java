package cdi.conceitos;

public class MensageiroCorreio implements Mensageiro {

	@Override
	public void enviarMensagem(String mensagem) {
		
		System.out.println("Mensagem enviada por correio: " + mensagem);
		
	}

}
