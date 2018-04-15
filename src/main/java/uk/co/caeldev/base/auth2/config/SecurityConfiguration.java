package uk.co.caeldev.base.auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import uk.co.caeldev.springsecuritymongo.MongoUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired @Lazy
    private MongoUserDetailsManager mongoUserDetailsManager;

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .requestMatchers().antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
            .and()
            .authorizeRequests()
                .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").failureUrl("/login").permitAll()
            .and()
            .logout().permitAll();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(
                        "/fonts/**");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUserDetailsManager());
    }

    @Bean
    public UserDetailsService getUserDetailsManager() {
        return mongoUserDetailsManager;
    }

    @Bean(name="authenticationManager")
    @Lazy
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
