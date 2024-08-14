package com.example.apisecuritybasic.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll()//página de login é acessível para todos
                .requestMatchers("/style.css").permitAll()//acessivel para todos
                .requestMatchers("/erros/**").permitAll()//acessivel a todos
                .requestMatchers("/home/**").hasRole("USER")//acessivel apenas pelos usuário com a role user
                .requestMatchers("/admin/**").hasRole("ADMIN")//acessivel com a role admin
                .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")//metodos post da tela adimin são acessiveis apenas pela role admin
                .anyRequest().authenticated()
        ).formLogin(form -> form
         .loginPage("/login")//definindo a pagina onde havera o login (form)
         .loginProcessingUrl("/perform_login") // url do login
         .defaultSuccessUrl("/home",true) // se o login for bem sucedido leva para a pagina home
         .failureUrl("/login?error=true")// se não for bem sucedido ela para a pagina de login com erro
         .permitAll()
        )
         .logout(logout -> logout
         .logoutUrl("/logout")// pagina de url do logout
         .logoutSuccessUrl("/login?logout=true")
         .permitAll()
         )
         .csrf(csrf -> csrf
         .ignoringRequestMatchers("/perform_login")
         .ignoringRequestMatchers("/logout")
         );
        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withUsername("usuario")
                .password(passwordEncoder().encode("senha"))//codifica a senha
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
