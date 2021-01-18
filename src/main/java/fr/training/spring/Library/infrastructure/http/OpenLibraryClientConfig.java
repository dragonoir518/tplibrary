package fr.training.spring.Library.infrastructure.http;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenLibraryClientConfig {

    // Best practice is to externalize this url in properies
    private static final String BASE_URL = "https://openlibrary.org";

    /**
     * Configure a RestTemplate with errorHandler to call openlibrary.org
     *
     * @param restTemplateBuilder injected By Spring
     *
     * @return a RestTemplate
     */
    @Bean
    public RestTemplate getRestClient(final RestTemplateBuilder restTemplateBuilder) {

        final List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        return restTemplateBuilder.interceptors(interceptors) //
                .rootUri(BASE_URL) //
                .build();
    }


}
