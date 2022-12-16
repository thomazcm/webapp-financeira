package br.com.thomaz.springmvcfinanceira.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Value("${webapp-financeira.endpoint.apiEndpoint}")
    private String apiEndpoint;

    @Autowired private JsonService json;
    @Autowired private HttpClient client;
    
    public HttpResponse<String> doRequest(HttpMethod method, String endpoint) {
        return doRequest(method, endpoint, null);
    }

    public HttpResponse<String> doRequest(HttpMethod method, String endpoint, Object body) {
        Builder requestBuilder = HttpRequest.newBuilder(URI.create(apiEndpoint + endpoint));
        var bodyPublisher = setBodyPublisher(body, requestBuilder);
        var request = requestBuilder.method(method.toString(), bodyPublisher).build();
        return requestResponse(request);
    }

    private HttpResponse<String> requestResponse(HttpRequest request) {
        try {
            return client.send(request, BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private BodyPublisher setBodyPublisher(Object body, Builder requestBuilder) {
        if (body == null) {
            return BodyPublishers.noBody();
        } 
        requestBuilder.setHeader("Content-Type", "application/json");
        return BodyPublishers.ofString(json.asString(body));
    }

}
