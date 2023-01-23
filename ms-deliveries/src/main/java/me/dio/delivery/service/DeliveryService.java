package me.dio.delivery.service;

import lombok.RequiredArgsConstructor;
import me.dio.delivery.api.request.DeliveryRequest;
import me.dio.delivery.api.response.DeliveryResponse;
import me.dio.delivery.exception.ResourceNotFoundException;
import me.dio.delivery.repository.DeliveryRepository;
import me.dio.delivery.model.Delivery;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {
    public static final String DELIVERY = "Delivery";
    final DeliveryRepository deliveryRepository;
    final ModelMapper modelMapper;

    public List<DeliveryResponse> getAllDeliveries() {
        return modelMapper.map(deliveryRepository.findAll(), List.class);
    }

    public DeliveryResponse getDeliveryById(String id) {
        return deliveryRepository.findById(UUID.fromString(id))
                .map(response -> modelMapper.map(response, DeliveryResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException(DELIVERY, id));
    }

    public DeliveryResponse createDelivery(DeliveryRequest request) {
        Delivery toCreated = modelMapper.map(request, Delivery.class);
        return modelMapper.map(deliveryRepository.save(toCreated), DeliveryResponse.class);
    }

    public DeliveryResponse updateDelivery(String id, DeliveryRequest request) {
        if (!deliveryRepository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(DELIVERY, id);
        }
        Delivery toUpdate = modelMapper.map(request, Delivery.class);
        toUpdate.setId(UUID.fromString(id));
        return modelMapper.map(deliveryRepository.save(toUpdate), DeliveryResponse.class);
    }

    public void deleteDelivery(String id) {
        if (!deliveryRepository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(DELIVERY, id);
        }
        deliveryRepository.deleteById(UUID.fromString(id));
    }
}
