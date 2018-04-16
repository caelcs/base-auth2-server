package uk.co.caeldev.base.auth2.builders;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uk.co.caeldev.base.auth2.features.user.UserResource;
import uk.co.caeldev.base.auth2.persisters.Persister;
import uk.co.caeldev.springsecuritymongo.domain.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.google.common.collect.FluentIterable.from;

public class UserBuilder implements Builder<User> {

    private UUID userUUID = UUID.randomUUID();
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String username;
    private String password;
    private Set<GrantedAuthority> grantedAuthorities;

    private UserBuilder() {
    }

    public static UserBuilder userBuilder() {
        return new UserBuilder();
    }

    public User build() {
        return new User(password, username, userUUID, grantedAuthorities, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled);
    }

    @Override
    public User persist(Persister persister) {
        return persister.persist(build());
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder accountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public UserBuilder accountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public UserBuilder credentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UserBuilder enabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder grantedAuthorities(String... grantedAuthorities) {
        List<String> grantedAuthoritiesString = Lists.newArrayList(grantedAuthorities);
        this.grantedAuthorities = from(grantedAuthoritiesString).transform(toGrantedAuthority()).toSet();
        return this;
    }

    private Function<String, GrantedAuthority> toGrantedAuthority() {
        return new Function<String, GrantedAuthority>() {
            @Override
            public GrantedAuthority apply(String input) {
                    return new SimpleGrantedAuthority(input);
            }
        };
    }

    public UserBuilder fromUserResource(UserResource userResource) {
        this.username = userResource.getUsername();
        this.password = userResource.getPassword();
        this.grantedAuthorities = from(userResource.getAuthorities()).transform(toGrantedAuthorities()).toSet();
        this.accountNonExpired = userResource.isAccountNonExpired();
        this.accountNonLocked = userResource.isAccountNonLocked();
        this.credentialsNonExpired = userResource.isCredentialsNonExpired();
        this.enabled = userResource.isEnabled();
        this.userUUID = UUID.fromString(userResource.getUserUUID());
        return this;
    }

    private Function<String, GrantedAuthority> toGrantedAuthorities() {
        return new Function<String, GrantedAuthority>() {
            @Override
            public GrantedAuthority apply(String input) {
                return new SimpleGrantedAuthority(input);
            }
        };
    }
}
