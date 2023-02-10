package br.com.techgold.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;



public class TokenService {

	public String gerarToken(String pass) {
		
		try {
		    Algorithm algorithm = Algorithm.HMAC256("T&cHP4$$");
		    String token = JWT.create()
		        .withIssuer("API techpass.com.br")
		        .withClaim("pass", pass)
		        .sign(algorithm);
		    return token;
		    
		} catch (JWTCreationException exception){
			throw new RuntimeException("Erro ao gerar o token" + exception );
		}
	}
	
	public String getPass(String token) {
		
		DecodedJWT decodedJWT;
		try {
		    Algorithm algorithm = Algorithm.HMAC256("T&cHP4$$");
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("API techpass.com.br")
		        .build();
		        
		    decodedJWT = verifier.verify(token);
		    String s = decodedJWT.getClaims().get("pass").toString().replaceAll("\"", " ").trim();
		    return s;
		} catch (JWTVerificationException exception){
			throw new RuntimeException("Erro ao recuperar o token" + exception );
		}
	}
}
