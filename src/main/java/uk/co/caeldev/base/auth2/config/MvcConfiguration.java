package uk.co.caeldev.base.auth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthorisationSettings authorisationSettings;

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController(authorisationSettings.getLogInUrl()).setViewName(authorisationSettings.getLogInViewName());
        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
    }

}
