package capgemini.gameshop.security.config;

import capgemini.gameshop.security.filter.CustomAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
//@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(GET,"/api/products").permitAll();
        http.authorizeRequests().antMatchers(POST, "api/products").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT, "api/products/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE, "api/products/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET, "api/orders/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(POST, "api/orders").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(PUT, "api/orders/**").hasAnyAuthority("USER");
        http.authorizeRequests().antMatchers(DELETE, "api/orders/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }

//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }
}
