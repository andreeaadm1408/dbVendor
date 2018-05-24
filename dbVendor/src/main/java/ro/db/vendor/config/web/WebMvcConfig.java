package ro.db.vendor.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

/**
 * Developer: Viorelt
 * <p>
 **/
@Configuration
@ComponentScan("ro.db.vendor.controller")               //va cauta dupa @Controller/@RestController
@EnableWebMvc
public class WebMvcConfig {


    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.mediaType("json", MediaType.APPLICATION_JSON);

    }

    private final static Logger log = LoggerFactory
            .getLogger(WebMvcConfig.class);
}