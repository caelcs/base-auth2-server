package uk.co.caeldev.base.auth2.features.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import uk.co.caeldev.spring.mvc.ResponseEntityBuilder;
import uk.co.caeldev.springsecuritymongo.domain.User;

import java.security.Principal;

import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserDetailsManager userDetailsManager;
    private final UserResourceAssembler userResourceAssembler;

    public UserController(@Qualifier("mongoUserDetailsManager") final UserDetailsManager userDetailsManager,
                          final UserResourceAssembler userResourceAssembler) {
        this.userDetailsManager = userDetailsManager;
        this.userResourceAssembler = userResourceAssembler;
    }

    @GetMapping("/user")
    public Principal user(final Principal user) {
        return user;
    }

    @PostMapping(value = "/users",
            consumes = {APPLICATION_JSON_VALUE},
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> createUser(@RequestBody final UserResource userResource) {
        LOGGER.info("Creating user");

        final UserResource payload = new UserResource(userResource.getUsername(), userResource.getPassword(), null, newHashSet("ROLE_USER"), true, true, true, true);

        final User user = userResourceAssembler.toDomain(payload, new User());

        userDetailsManager.createUser(user);

        final UserDetails userCreated = userDetailsManager.loadUserByUsername(userResource.getUsername());

        return ResponseEntityBuilder.
                <UserResource>responseEntityBuilder()
                .statusCode(CREATED)
                .entity(userResourceAssembler.toResource((User)userCreated))
                .build();
    }

    @GetMapping(value = "/users",
            produces = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<UserResource> getUserByUsername(@RequestParam final String username) {
        final UserDetails userDetails = userDetailsManager.loadUserByUsername(username);

        if (userDetails == null) {
            return ResponseEntityBuilder.
                    <UserResource>responseEntityBuilder()
                    .statusCode(NOT_FOUND)
                    .build();
        }

        return ResponseEntityBuilder.
                <UserResource>responseEntityBuilder()
                .statusCode(OK)
                .entity(userResourceAssembler.toResource((User)userDetails))
                .build();
    }
}
