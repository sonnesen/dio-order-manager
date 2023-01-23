package me.dio.order.service;

import lombok.RequiredArgsConstructor;
import me.dio.order.api.request.OrderRequest;
import me.dio.order.api.response.OrderResponse;
import me.dio.order.exception.ResourceNotFoundException;
import me.dio.order.model.Order;
import me.dio.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    public static final String ORDER = "Order";
    final OrderRepository orderRepository;
    final ModelMapper modelMapper;

    public List<OrderResponse> getAllOrders() {
        return modelMapper.map(orderRepository.findAll(), List.class);
    }

    public OrderResponse getOrderById(String id) {
        return orderRepository.findById(UUID.fromString(id))
                .map(response -> modelMapper.map(response, OrderResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException(ORDER, id));
    }

    public OrderResponse createOrder(OrderRequest request) {
        Order toCreated = modelMapper.map(request, Order.class);
        return modelMapper.map(orderRepository.save(toCreated), OrderResponse.class);
    }

    public OrderResponse updateOrder(String id, OrderRequest request) {
        if (!orderRepository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(ORDER, id);
        }
        Order toUpdate = modelMapper.map(request, Order.class);
        toUpdate.setId(UUID.fromString(id));
        return modelMapper.map(orderRepository.save(toUpdate), OrderResponse.class);
    }

    public void deleteOrder(String id) {
        if (!orderRepository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(ORDER, id);
        }
        orderRepository.deleteById(UUID.fromString(id));
    }
}
