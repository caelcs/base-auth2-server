package uk.co.caeldev.base.auth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http
            .requestMatchers().antMatchers("/users", "/user")
            .and()
            .authorizeRequests().antMatchers("/users").access("#oauth2.hasScope('write')")
            .and()
            .authorizeRequests().antMatchers("/user").access("#oauth2.hasScope('read') or #oauth2.isClient()");
    }
}
