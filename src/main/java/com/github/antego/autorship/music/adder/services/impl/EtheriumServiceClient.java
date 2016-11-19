package com.github.antego.autorship.music.adder.services.impl;

import com.github.antego.autorship.music.adder.models.MusicToEtheriumData;
import com.github.antego.autorship.music.adder.models.ResponseEtherium;
import com.github.antego.autorship.music.adder.models.exceptions.EtheriumConnectException;
import com.github.antego.autorship.music.adder.services.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("etherium-service")
public class EtheriumServiceClient implements RestClient<MusicToEtheriumData> {

    private static final Logger log = LoggerFactory.getLogger(EtheriumServiceClient.class);
    private final RestTemplate template;
    @Value("${app.etherium.host}")
    private String host;
    @Value("${app.etherium.port}")
    private String port;

    @Autowired
    public EtheriumServiceClient(RestTemplate template) {
        this.template = template;
    }

    @Override
    public String request(HttpMethod method, String path, MusicToEtheriumData data) {
        try {
            String url = String.format(REST_URI, host, port) + path;
            HttpEntity<MusicToEtheriumData> request = new HttpEntity<>(data);

            ResponseEntity<ResponseEtherium> response = template.exchange(url, method, request, ResponseEtherium.class);
            HttpStatus statusCode = response.getStatusCode();

            if (statusCode != HttpStatus.OK) {
                log.error("request to etherium -> error, status code '{}', reason '{}'", statusCode.name(), statusCode.getReasonPhrase());
                throw new EtheriumConnectException(String.format("status code %s, reason: %s", statusCode.name(), statusCode.getReasonPhrase()));
            }

            ResponseEtherium responseBody = response.getBody();
            if (responseBody == null) {
                log.error("request to etherium -> error, response body is null");
                throw new EtheriumConnectException("response body is null");
            }

            String result = responseBody.getData();
            log.info("request to etherium -> ok, result '{}'", result);
            return result;
        } catch (EtheriumConnectException e) {
            throw e;
        } catch (Exception e) {
            log.error("request to etherium -> error", e);
            throw new EtheriumConnectException("Error connection to Etherium service.");
        }
    }
}
