package uk.co.caeldev.base.auth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import uk.co.caeldev.springsecuritymongo.config.EnableSecurityMongo;

@Configuration
@EnableSecurityMongo
@EnableResourceServer
@Import(value = { AuthorizationServerConfiguration.class })
public class MainConfiguration {
}
