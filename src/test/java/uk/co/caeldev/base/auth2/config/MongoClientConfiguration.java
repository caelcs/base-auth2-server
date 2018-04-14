package uk.co.caeldev.base.auth2.config;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import uk.co.caeldev.springsecuritymongo.config.MongoSettings;

@Configuration
@EnableConfigurationProperties(MongoSettings.class)
@Profile("test")
public class MongoClientConfiguration {

    @Bean
    public MongoClient mongoClient(final MongoSettings mongoSettings) {
        Fongo fongo = new Fongo(mongoSettings.getDatabase());
        return fongo.getMongo();
    }

}