package br.com.lucasladeira.vuttrapi.security.filters;

import br.com.lucasladeira.vuttrapi.repositories.AppUserRepository;
import br.com.lucasladeira.vuttrapi.entities.AppUser;
import br.com.lucasladeira.vuttrapi.security.entities.UserDetailsSS;
import br.com.lucasladeira.vuttrapi.security.services.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private JwtService jwtService;

    private AppUserRepository appUserRepository;

    public JWTAuthorizationFilter(JwtService jwtService, AppUserRepository appUserRepository) {
        this.jwtService = jwtService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("Chamando JWTAuthorizationFilter!!!");
        String token = getToken(request);
        logger.info("TOKEN JWT PEGO: " + token);

        boolean jwtIsValid = jwtService.validateToken(token);

        logger.info("JWT IS VALID?: " + jwtIsValid);

        if (jwtIsValid){
            authenticateClient(token);
        }

        //Continua com o fluxo
        filterChain.doFilter(request, response);
    }

    private void authenticateClient(String token) {
        String username = jwtService.getUsername(token);
        AppUser appUser = appUserRepository.findByUsername(username).get();
        UserDetailsSS userDetailsSS = new UserDetailsSS(appUser.getId(), appUser.getUsername(), appUser.getPassword(), appUser.getProfiles());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetailsSS, null, userDetailsSS.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            logger.info("TOKEN INVALIDO!!!");
            return null;
        }
        return token.substring(7, token.length());
    }
}
