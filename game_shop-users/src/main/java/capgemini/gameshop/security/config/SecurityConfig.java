package capgemini.gameshop.security.config;

import capgemini.gameshop.security.filter.CustomAuthenticationFilter;
import capgemini.gameshop.security.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter =
                new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/users/login");

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers("api/users/login", "api/users/token").permitAll();
        http.authorizeRequests().antMatchers(GET, "api/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(POST, "api/users").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(PUT, "api/users/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(DELETE, "api/users/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET, "api/adresses/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(POST, "api/adresses").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(PUT, "api/adresses/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(DELETE, "api/adresses/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}
