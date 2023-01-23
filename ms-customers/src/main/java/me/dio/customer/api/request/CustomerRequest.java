package me.dio.customer.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;
    @NotBlank
    @Size(min = 10, max = 255)
    private String address;
}
