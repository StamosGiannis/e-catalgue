package gr.aueb.cf.e_shop.authentication;

import gr.aueb.cf.e_shop.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration

public class SecurityConfig {

    @Autowired
    private CustomAuthProvider customAuthProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(customAuthProvider.authenticationProvider())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/img/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/admins/**").hasAnyAuthority(Role.ROLE_ADMIN.name())
                        .requestMatchers("/customers/**").hasAnyAuthority(Role.ROLE_CUSTOMER.name())
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                .loginPage("/login").permitAll()
                )
                .httpBasic(withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe((rememberMeConfigurer) -> rememberMeConfigurer
                        .alwaysRemember(true)
                        .rememberMeParameter("rememberMe")
                        .rememberMeCookieName("remember-me")
                        .tokenValiditySeconds(24 * 60 * 60)
                );

        return http.build();
    }
}
