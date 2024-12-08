package lk.iit.ticketingsystem.config;

 // Replace with your actual package

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration  // This annotation tells Spring that this is a configuration class
public class WebConfig implements WebMvcConfigurer {

    // Override the addCorsMappings method to configure CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // This method allows you to specify which origins can access your backend
        registry.addMapping("/**")  // Apply this CORS configuration to all endpoints
                .allowedOrigins("http://localhost:5173")  // The frontend URL
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allows all headers
                .allowCredentials(true);  // Allows sending credentials (cookies, etc.)
    }
}

