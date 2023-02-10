package br.com.techgold.test;

import br.com.techgold.GeraSenha;
import br.com.techgold.security.TokenService;

public class TesteCripto {

	public static void main(String[] args) {
		
		/*
		GeraSenha g = new GeraSenha();
		String senhaCodificada =  g.codificar("12/21/1234");
		String senhaDecodificada = g.decodificar(senhaCodificada);
		
		System.out.println(senhaCodificada);
		System.out.println("\n");
		System.out.println(senhaDecodificada);
		
		
		boolean valida = senhaDecodificada.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d");
		
		System.out.println(valida);
		*/
		
		System.out.println("############  CRIPTOGRAFANDO  ################\n");
		TokenService token = new TokenService();
		GeraSenha g = new GeraSenha();
		String senhaCodificada =  g.codificar("teste");
		String tokenGerado = token.gerarToken(senhaCodificada);
		System.out.println(tokenGerado + "\n");
		System.out.println("############  CRIPTOGRAFANDO  ################\n");
		
		System.out.println();
		
		System.out.println("############  DECRIPTOGRAFANDO  ##############\n");
		String tokenRecuperado = token.getPass(tokenGerado);
		String tokenDecriptografado = g.decodificar(tokenRecuperado);
		System.out.println(tokenDecriptografado + "\n");
		System.out.println("############  DECRIPTOGRAFANDO  ##############\n");
		
		
		
	}
	
}
