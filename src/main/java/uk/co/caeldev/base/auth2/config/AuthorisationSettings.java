package uk.co.caeldev.base.auth2.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "auth")
public class AuthorisationSettings {

    private String logInViewName;

    private String logInUrl;

    private String failureUrl;

    private List<String> ignoreWebStaticResources;

    public String getLogInViewName() {
        return logInViewName;
    }

    public void setLogInViewName(final String logInViewName) {
        this.logInViewName = logInViewName;
    }

    public String getLogInUrl() {
        return logInUrl;
    }

    public void setLogInUrl(final String logInUrl) {
        this.logInUrl = logInUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(final String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public List<String> getIgnoreWebStaticResources() {
        return ignoreWebStaticResources;
    }

    public void setIgnoreWebStaticResources(final List<String> ignoreWebStaticResources) {
        this.ignoreWebStaticResources = ignoreWebStaticResources;
    }
}
