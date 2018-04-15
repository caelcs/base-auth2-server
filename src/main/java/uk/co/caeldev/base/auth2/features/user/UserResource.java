package uk.co.caeldev.base.auth2.features.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.springframework.hateoas.ResourceSupport;

import java.util.Set;

public class UserResource extends ResourceSupport {

    private String username;
    private String password;
    private String userUUID;
    private Set<String> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    @JsonCreator
    public UserResource(@JsonProperty("username") String username,
                        @JsonProperty("password") String password,
                        @JsonProperty("userUUID") String userUUID,
                        @JsonProperty("authorities") Set<String> authorities,
                        @JsonProperty("accountNonExpired") boolean accountNonExpired,
                        @JsonProperty("accountNonLocked") boolean accountNonLocked,
                        @JsonProperty("credentialsNonExpired") boolean credentialsNonExpired,
                        @JsonProperty("enabled") boolean enabled) {
        this.username = username;
        this.password = password;
        this.userUUID = userUUID;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
