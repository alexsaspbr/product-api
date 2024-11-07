package tech.ada.products_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@JsonIgnoreProperties(ignoreUnknown = false)
public class ResponsePagingDTO<T> {

    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<T> list;
    private int pageNumber;
    private long offset;

}
