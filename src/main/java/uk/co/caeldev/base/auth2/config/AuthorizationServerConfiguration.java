package uk.co.caeldev.base.auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import uk.co.caeldev.springsecuritymongo.MongoApprovalStore;
import uk.co.caeldev.springsecuritymongo.MongoClientDetailsService;
import uk.co.caeldev.springsecuritymongo.MongoTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private MongoTokenStore mongoTokenStore;
    
    @Autowired
    private MongoClientDetailsService mongoClientDetailsService;
    
    @Autowired
    private MongoApprovalStore mongoApprovalStore;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(mongoClientDetailsService);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(mongoTokenStore)
            .approvalStore(mongoApprovalStore)
            .authenticationManager(authenticationManager);
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(mongoTokenStore);
        return tokenServices;
    }
}

