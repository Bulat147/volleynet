package per.khalilov.volleynet.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import per.khalilov.volleynet.models.Account;
import per.khalilov.volleynet.repositories.AccountsRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AccountsRepository accountsRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/SignInPage.html")
                        .loginProcessingUrl("/signIn")  // *
                        .usernameParameter("name")
                        .defaultSuccessUrl("/Good.html")
                        .failureUrl("/SignInPage.html")
                )
                .logout(logout -> logout
                        .deleteCookies("JSESSIONID")
                        .logoutRequestMatcher(req ->
                                req.getContextPath().equals("/logout")
                                && req.getMethod().equals("GET"))
                        .invalidateHttpSession(true))
                .authorizeHttpRequests(
                        (castomiser) -> {
                            castomiser.requestMatchers("/account/admins").authenticated();
                            castomiser.anyRequest().permitAll();
                        }
                );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Account account = accountsRepository.findByName(username).orElseThrow(NoSuchFieldError::new);
            return new AccountDetails(account);
        };
    }
}
