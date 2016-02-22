package revisao;

public class PalavraInvertidaTest {
	
	public static void main(String[] args) {
		String nome = inverterNome("edney");
		System.out.println(nome);
	}
	
	public static String inverterNome(String nome) {
		String invertida = "";
		
		for(int i = nome.length() - 1; i >= 0; i--) {
			invertida += nome.charAt(i);
		}
		
		return invertida;
	}

}
