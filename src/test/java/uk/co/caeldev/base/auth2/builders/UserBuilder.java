package uk.co.caeldev.base.auth2.builders;

import com.google.common.collect.Lists;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uk.co.caeldev.base.auth2.persisters.Persister;
import uk.co.caeldev.springsecuritymongo.domain.User;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return new User(password, username, userUUID, grantedAuthorities, accountNonExpired, accountNonLocked,
                credentialsNonExpired, enabled);
    }

    @Override
    public User persist(final Persister persister) {
        return persister.persist(build());
    }

    public UserBuilder username(final String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(final String password) {
        this.password = password;
        return this;
    }

    public UserBuilder accountNonExpired(final boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public UserBuilder accountNonLocked(final boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public UserBuilder credentialsNonExpired(final boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public UserBuilder enabled(final boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public UserBuilder grantedAuthorities(final String... grantedAuthorities) {
        final List<String> grantedAuthoritiesString = Lists.newArrayList(grantedAuthorities);
        this.grantedAuthorities = grantedAuthoritiesString.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return this;
    }
}
