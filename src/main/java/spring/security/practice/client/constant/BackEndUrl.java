package spring.security.practice.client.constant;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class BackEndUrl {
    private final String baseUrl = "http://localhost:8081/api/v1";

    public String authUrl() {
        return "http://localhost:8081/auth";
    }

    public String fakultasUrl() {
        return Strings.concat(baseUrl, "/fakultas");
    }
}
