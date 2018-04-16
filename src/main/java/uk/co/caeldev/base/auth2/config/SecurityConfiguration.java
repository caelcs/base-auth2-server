package uk.co.caeldev.base.auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@Configuration
@Order(99)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthorisationSettings authorisationSettings;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
                .authorizeRequests()
                .antMatchers( authorisationSettings.getLogInUrl())
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(authorisationSettings.getLogInUrl())
                .failureUrl(authorisationSettings.getFailureUrl());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        final List<String> ignoreWebStaticResources = authorisationSettings.getIgnoreWebStaticResources();
        final String[] ignores = ignoreWebStaticResources.stream().toArray(String[]::new);
        web.ignoring()
                .antMatchers(ignores);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
