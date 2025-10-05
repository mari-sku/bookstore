package com.example.bookstore;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
@Bean 
public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    
    // kaikki url:it vaativat kirjautumisen paitsi css-resurssit
    http 
        .authorizeHttpRequests(authorize -> authorize 
       	.requestMatchers("/css/**").permitAll() // Enable css even when logged out
        .requestMatchers("/deletebook/**").hasAuthority("ADMIN")  // vain ADMIN voi poistaa kirjoja
         .requestMatchers(toH2Console()).permitAll()
        .anyRequest().authenticated()
      )
     .csrf(csrf -> csrf
          .ignoringRequestMatchers(toH2Console())
     )
     .headers(headers -> headers
         .frameOptions(frameoptions -> frameoptions
                  .disable())
     )
    // onnistunut kirjautuminen vie /booklist sivulle 
      .formLogin(formlogin -> formlogin
          .loginPage("/login")       // oma login sivu
          .defaultSuccessUrl("/booklist", true)
          .permitAll()
      )
      .logout(logout -> logout
          .permitAll()
      );
      return http.build();
}

}
