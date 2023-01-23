package me.dio.delivery.api.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryRequest {

    @NotBlank
    private UUID customerId;
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;
    @NotNull
    @PositiveOrZero
    private BigDecimal amount;
}
