package spring.security.practice.client.auth.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationReq {
    private String email;
    private String password;
}
