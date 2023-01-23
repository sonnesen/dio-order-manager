package me.dio.delivery.api.response;

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
public class DeliveryResponse {
    private UUID id;
    private UUID customerId;
    private String description;
    private BigDecimal amount;
}
