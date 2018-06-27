package hk.oro.iefas.web.core.service;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @version $Revision: 2528 $ $Date: 2018-05-21 19:33:03 +0800 (週一, 21 五月 2018) $
 * @author $Author: marvel.ma $
 */
@Service(value = BaseClientService.BEAN_NAME)
public class BaseClientService {

    public static final String BEAN_NAME = "baseClientService";

    @Value(value = "${iefas.ws.address}")
    private String wsAddress;

    @Autowired
    private RestTemplate restTemplate;

    public void delete(String url, Map<String, ?> uriVariables) {
        restTemplate.delete(this.wsAddress + url, uriVariables);
    }

    public void delete(String url, Object... uriVariables) {
        restTemplate.delete(this.wsAddress + url, uriVariables);
    }

    public void delete(URI url) {
        restTemplate.delete(this.wsAddress + url);
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
        throws RestClientException {
        return restTemplate.getForEntity(this.wsAddress + url, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> getForEntity(String url, Class<T> responseType, Object... uriVariables)
        throws RestClientException {
        return restTemplate.getForEntity(this.wsAddress + url, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> getForEntity(URI url, Class<T> responseType) throws RestClientException {
        return restTemplate.getForEntity(this.wsAddress + url, responseType);
    }

    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) {
        return restTemplate.getForObject(this.wsAddress + url, responseType, uriVariables);
    }

    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.getForObject(this.wsAddress + url, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType,
        Map<String, ?> uriVariables) throws RestClientException {
        return restTemplate.postForEntity(this.wsAddress + url, request, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType,
        Object... uriVariables) throws RestClientException {
        return restTemplate.postForEntity(this.wsAddress + url, request, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> postForEntity(URI url, Object request, Class<T> responseType)
        throws RestClientException {
        return restTemplate.postForEntity(this.wsAddress + url, request, responseType);
    }

    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) {
        return restTemplate.postForObject(this.wsAddress + url, request, responseType, uriVariables);
    }

    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.postForObject(this.wsAddress + url, request, responseType, uriVariables);
    }

    public void put(String url, Object request, Map<String, ?> uriVariables) {
        restTemplate.put(this.wsAddress + url, request, uriVariables);
    }

    public void put(String url, Object request, Object... uriVariables) {
        restTemplate.put(this.wsAddress + url, request, uriVariables);
    }

    public void put(URI url, Object request) {
        restTemplate.put(this.wsAddress + url, request);
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
        ParameterizedTypeReference<T> responseType, Object... uriVariables) {
        return restTemplate.exchange(this.wsAddress + url, method, requestEntity, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
        ParameterizedTypeReference<T> responseType, Map<String, ?> uriVariables) {
        return restTemplate.exchange(this.wsAddress + url, method, requestEntity, responseType, uriVariables);
    }

    public <T> ResponseEntity<T> exchange(URI url, HttpMethod method, HttpEntity<?> requestEntity,
        ParameterizedTypeReference<T> responseType) {
        return restTemplate.exchange(this.wsAddress + url, method, requestEntity, responseType);
    }

}
