package spring.security.practice.client.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import spring.security.practice.client.auth.model.AuthenticationReq;
import spring.security.practice.client.auth.model.AuthenticationRes;
import spring.security.practice.client.base.Response;
import spring.security.practice.client.constant.BackEndUrl;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final BackEndUrl backEndUrl;

    @Override
    public Optional<AuthenticationRes> authenticate(AuthenticationReq request, HttpServletRequest httpServletRequest) {
        try {
            var url = Strings.concat(backEndUrl.authUrl(), "/authenticate");
            HttpEntity<AuthenticationReq> requestEntity = new HttpEntity<>(request);
            ResponseEntity<Response> response = restTemplate.postForEntity(url, requestEntity, Response.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                byte[] json = objectMapper.writeValueAsBytes(response.getBody().getData());
                AuthenticationRes res = objectMapper.readValue(json, AuthenticationRes.class);
                httpServletRequest.getSession().setAttribute("JWT_TOKEN", res.getAccessToken());

                return Optional.of(res);
            }
        } catch (JsonProcessingException e) {
            return Optional.empty();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }
}
