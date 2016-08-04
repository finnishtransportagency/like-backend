package fi.livi.like.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/*
 * Configurating LOGGING
 * =====================
 * Choose correct logging configuration by VM arguments e.g.
 * -Dlogback.configurationFile=logback-dev.xml
 */
@ComponentScan(basePackages = {"fi.livi.like"})
@SpringBootApplication
public class LikeApplication { //NOSONAR
    
    public static void main(String[] args) {
        SpringApplication.run(LikeApplication.class, args); //NOSONAR
    }
}
