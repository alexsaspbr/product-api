package tech.ada.products_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = false)
public class ResponseDTO <T> {

    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;

}
