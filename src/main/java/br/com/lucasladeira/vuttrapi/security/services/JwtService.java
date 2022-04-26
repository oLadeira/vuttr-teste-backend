package br.com.lucasladeira.vuttrapi.security.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${api.jwt.secret}")
    private String secret;

    @Value("${api.jwt.expiration}")
    private String expiration;


    public String generateToken(String username){
        return Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(expiration)))
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        System.out.println("CHAVE SECRETA VALIDATE: " + secret);
        try {
            //Validando Token
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
    }
}
