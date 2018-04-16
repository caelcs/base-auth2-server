package uk.co.caeldev.base.auth2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ MainConfiguration.class })
@ComponentScan(basePackages = "uk.caeldev.base.auth2")
public @interface EnableBaseAuthServer {
}
