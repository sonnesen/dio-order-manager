package me.dio.delivery.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorDetail {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String error;
    private String path;
}
