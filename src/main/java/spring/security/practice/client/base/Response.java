package spring.security.practice.client.base;

import lombok.*;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private int statusCode;
    private Object message;
    private T data;
    private List<FieldError> errors = new ArrayList<>();
    private int total;
}
