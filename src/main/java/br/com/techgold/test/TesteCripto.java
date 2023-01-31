package br.com.techgold.test;
import br.com.techgold.GeraSenha;

public class TesteCripto {

	public static void main(String[] args) {
		
		
		GeraSenha g = new GeraSenha();
		String senhaCodificada =  g.codificar("12/21/1234");
		String senhaDecodificada = g.decodificar(senhaCodificada);
		
		System.out.println(senhaCodificada);
		System.out.println("\n");
		System.out.println(senhaDecodificada);
		
		
		boolean valida = senhaDecodificada.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d");
		
		System.out.println(valida);
		
	}
	
}
