package uk.co.caeldev.base.auth2.features.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import uk.co.caeldev.spring.mvc.resources.DomainResourceAssemblerSupport;
import uk.co.caeldev.springsecuritymongo.domain.User;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.google.common.collect.Sets.newHashSet;

@Component
public class UserResourceAssembler extends DomainResourceAssemblerSupport<User, UserResource> {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserResourceAssembler.class);

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public User toDomain(final UserResource userResource,
                         final User user) {
        LOGGER.debug("Transform UserResource to User");
        return new User(userResource.getPassword(), userResource.getUsername(),
                Objects.isNull(userResource.getUserUUID())? UUID.randomUUID():
                        UUID.fromString(userResource.getUserUUID()),
                toGrantedAuthorities(userResource),
                userResource.isAccountNonExpired(),
                userResource.isAccountNonLocked(),
                userResource.isCredentialsNonExpired(),
                userResource.isEnabled());
    }

    @Override
    public UserResource toResource(final User user) {
        LOGGER.debug("Transform User to UserResource");
        return new UserResource(user.getUsername(),
                user.getPassword(),
                Objects.isNull(user.getUserUUID())? null : user.getUserUUID().toString(),
                toSetOfStrings(user),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled());
    }

    private Set<String> toSetOfStrings(final User user) {

        if (Objects.isNull(user.getAuthorities()) || user.getAuthorities().isEmpty()) {
            return newHashSet();
        }

        return user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
    }

    private Set<GrantedAuthority> toGrantedAuthorities(final UserResource userResource) {

        if (Objects.isNull(userResource.getAuthorities()) || userResource.getAuthorities().isEmpty()) {
            return newHashSet();
        }

        return userResource.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
