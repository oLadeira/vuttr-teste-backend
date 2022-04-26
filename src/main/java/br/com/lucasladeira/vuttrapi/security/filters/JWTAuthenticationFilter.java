package br.com.lucasladeira.vuttrapi.security.filters;

import br.com.lucasladeira.vuttrapi.security.dto.AuthDto;
import br.com.lucasladeira.vuttrapi.security.entities.UserDetailsSS;
import br.com.lucasladeira.vuttrapi.security.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

//Filtro que intercepta a autenticacao para quando usuario for autenticado um token JWT ser gerado
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private JwtService jwtGenerate;

    @Autowired
    private AuthenticationManager authenticationManager;


    //Tentativa de autenticacao
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        logger.info("Tentando autenticar");
        try {
            AuthDto credencials = new ObjectMapper()
                    .readValue(request.getInputStream(), AuthDto.class); //pegando os parametros da requisicao
                                                                        // e transformando em AuthDto

            //Gerando um token de autenticacao(nao é o jwt)
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(credencials.getUsername(), credencials.getPassword(), new ArrayList<>());

            //Executa o metodo de autenticar
            return authenticationManager.authenticate(authToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Em caso de autenticacao bem sucedida, gerar o token JWT
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        String username = ((UserDetailsSS) authResult.getPrincipal()).getUsername();

        logger.info("Usuario autenticado");

        String jwtToken = jwtGenerate.generateToken(username);
        logger.info(jwtToken);
        response.addHeader("Authorization", "Bearer " + jwtToken);
    }
}
