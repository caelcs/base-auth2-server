package uk.co.caeldev.base.auth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import uk.co.caeldev.springsecuritymongo.config.EnableSecurityMongo;

@Configuration
@EnableSecurityMongo
@Import(value = { AuthorizationServerConfiguration.class })
public class MainConfiguration {
}
