package br.com.lucasladeira.vuttrapi.security.config;

import br.com.lucasladeira.vuttrapi.repositories.AppUserRepository;
import br.com.lucasladeira.vuttrapi.security.filters.JWTAuthorizationFilter;
import br.com.lucasladeira.vuttrapi.security.services.UserDetailsServiceImpl;
import br.com.lucasladeira.vuttrapi.security.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private JwtService jwtService;

    //Enderecos publicos
    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**",
            "/auth/**",
            "/login/**"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/user/**",
    };

    //Enderecos de acesso ROLE USER
    private static final String[] USER_MATCHERS = {
        "/tools/**"
    };

    //Enderecos de acesso ROLE ADMIN
    private static final String[]ADMIN_MATCHERS = {

    };


    //1.Configuracoes de autenticacao
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth
                .inMemoryAuthentication()
                .withUser("lucas").password(passwordEncoder().encode("321")).roles("USER")
                .and()
                .withUser("oLadeira").password(passwordEncoder().encode("123")).roles("ADMIN", "USER");
*/

        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    //2.Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers(PUBLIC_MATCHERS).permitAll()
                    .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .cors().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(jwtService, appUserRepository), UsernamePasswordAuthenticationFilter.class);
    }

    //3.Configuracoes de recursos estaticos(arquivos css, js, imagens etc) nao vou utilizar
    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    //4. Escolhendo o encoder da senha (senhas precisa ser criptografadas antes de serem salvas no bd)
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
