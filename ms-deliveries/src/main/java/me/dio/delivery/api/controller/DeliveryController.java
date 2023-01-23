package me.dio.delivery.api.controller;

import me.dio.delivery.api.request.DeliveryRequest;
import me.dio.delivery.api.response.DeliveryResponse;
import me.dio.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/deliveries")
public class DeliveryController {

    final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping
    public ResponseEntity<List<DeliveryResponse>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DeliveryResponse> getDeliveryById(@PathVariable String id) {
        return ResponseEntity.ok(deliveryService.getDeliveryById(id));
    }

    @PostMapping
    public ResponseEntity<DeliveryResponse> createDelivery(@RequestBody DeliveryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.createDelivery(request));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DeliveryResponse> updateDelivery(@PathVariable String id,
                                                           @RequestBody DeliveryRequest request)  {
        return ResponseEntity.ok(deliveryService.updateDelivery(id, request));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable String id) {
        deliveryService.deleteDelivery(id);
        return ResponseEntity.noContent().build();
    }
}
