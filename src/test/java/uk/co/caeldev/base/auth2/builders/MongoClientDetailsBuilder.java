package uk.co.caeldev.base.auth2.builders;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uk.co.caeldev.base.auth2.persisters.Persister;
import uk.co.caeldev.springsecuritymongo.domain.MongoClientDetails;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MongoClientDetailsBuilder implements Builder<MongoClientDetails>{

    private String clientSecret;
    private Set<String> resourceIds;
    private String clientId;
    private Set<String> scopes;
    private Set<String> authorizedGrantTypes;
    private List<GrantedAuthority> authorities;
    private Set<String> registeredRedirectUris;
    private int accessTokenValiditySeconds;
    private int refreshTokenValiditySeconds;
    private Map<String, Object> additionalInformation;
    private Set<String> autoApproveScopes = Sets.newHashSet();

    private MongoClientDetailsBuilder() {
    }

    @Override
    public MongoClientDetails build() {
        return new MongoClientDetails(clientId, clientSecret, scopes, resourceIds, authorizedGrantTypes,
                registeredRedirectUris, authorities, accessTokenValiditySeconds, refreshTokenValiditySeconds,
                additionalInformation, autoApproveScopes);
    }

    @Override
    public MongoClientDetails persist(Persister persister) {
        return persister.persist(build());
    }

    public static MongoClientDetailsBuilder mongoClientDetailsBuilder() {
        return new MongoClientDetailsBuilder();
    }

    public MongoClientDetailsBuilder clientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }

    public MongoClientDetailsBuilder scopes(final String... scopes) {
        this.scopes = Sets.newHashSet(scopes);
        return this;
    }

    public MongoClientDetailsBuilder authorizedGrantTypes(final String... authorizedGrantTypes) {
        this.authorizedGrantTypes = Sets.newHashSet(authorizedGrantTypes);
        return this;
    }


    public MongoClientDetailsBuilder clientSecret(final String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public MongoClientDetailsBuilder resourceIds(final String... resourceIds) {
        this.resourceIds = Sets.newHashSet(resourceIds);
        return this;
    }

    public MongoClientDetailsBuilder accessTokenValiditySeconds(final int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        return this;
    }

    public MongoClientDetailsBuilder refreshTokenValiditySeconds(final int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        return this;
    }

    public MongoClientDetailsBuilder additionalInformation(final Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public MongoClientDetailsBuilder autoApproveScopes(final String... autoApproveScopes) {
        this.autoApproveScopes = Sets.newHashSet(autoApproveScopes);
        return this;
    }

    public MongoClientDetailsBuilder redirect(final String... redirect) {
        this.registeredRedirectUris = Sets.newHashSet(redirect);
        return this;
    }

    public MongoClientDetailsBuilder authorities(final String... authorities) {
        final List<String> authoritiesString = Lists.newArrayList(authorities);
        this.authorities = authoritiesString.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return this;
    }
}
