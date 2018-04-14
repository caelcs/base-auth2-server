package uk.co.caeldev.base.auth2.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ MainConfiguration.class })
public @interface EnableBaseAuthServer {
}
