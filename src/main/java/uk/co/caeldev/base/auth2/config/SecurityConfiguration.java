package uk.co.caeldev.base.auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(101)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthorisationSettings authorisationSettings;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .requestMatchers().antMatchers("/", authorisationSettings.getLogInUrl(), "/oauth/authorize", "/oauth/confirm_access")
            .and()
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage(authorisationSettings.getLogInUrl()).failureUrl(authorisationSettings.getFailureUrl()).permitAll()
            .and()
            .logout().permitAll();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/fonts/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
