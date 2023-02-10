package br.com.techgold;

import br.com.techgold.security.TokenService;

public class GeraSenha {
	
	public String codificar(String pass) {
		//TOKEN pref eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzIjoi
		int qtd = pass.length();
		String prefixo = gerarCod(qtd);
		String sufixo = gerarCod(qtd);
		String password = inverter(pass);
		String result = prefixo + password + sufixo;
		TokenService token = new TokenService();
		String cod = token.gerarToken(result);
		return cod.substring(49, cod.length());
	}
	
	public String decodificar(String token) {
		TokenService tokenSevice = new TokenService();
		String pass = tokenSevice.getPass("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzIjoi"+token);
		int qtd = pass.length()/3;
		String resultParcial;
		resultParcial = pass.substring(qtd, qtd*2);
		return new StringBuilder(resultParcial).reverse().toString();
	}
	
	public String gerarCod(int qtd) {
		String[] caract = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
				"A","B","C","D","E","F","G","H","I","J","k","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","!","#","@","#","$","%","&","*","(",")","-","_","+",
				"-","<",">","/","Ç","?","[","]","{","}",",",".","|",":",";","'"};

		String senha ="";
		for(int x=0; x<qtd; x++) {
			int j = (int) (Math.random()*caract.length);
			senha += caract[j];
		}
		return senha;
	}
	
	public String inverter(String valor) {
		String invertida = new StringBuilder(valor).reverse().toString();
		return invertida;
	}
}
