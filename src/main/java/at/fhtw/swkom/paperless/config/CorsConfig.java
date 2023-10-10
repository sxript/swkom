package at.fhtw.swkom.paperless.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/*")
                .allowedOrigins("")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .allowedHeaders(
                        "User-Agent",
                        "Cache-Control",
                        "X-Requested-With",
                        "Content-Type",
                        "Accept",
                        "Accept-Encoding",
                        "Accept-Language",
                        "Authorization")
                .maxAge(3600);
    }

}
