package me.dio.customer.service;

import lombok.RequiredArgsConstructor;
import me.dio.customer.api.request.CustomerRequest;
import me.dio.customer.api.response.CustomerResponse;
import me.dio.customer.exception.ResourceNotFoundException;
import me.dio.customer.model.Customer;
import me.dio.customer.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerService {
    public static final String CUSTOMER = "Customer";
    final CustomerRepository customerRepository;
    final ModelMapper modelMapper;

    public List<CustomerResponse> getAllCustomers() {
        return modelMapper.map(customerRepository.findAll(), List.class);
    }

    public CustomerResponse getCustomerById(String id) {
        return customerRepository.findById(UUID.fromString(id))
                .map(response -> modelMapper.map(response, CustomerResponse.class))
                .orElseThrow(() -> new ResourceNotFoundException(CUSTOMER, id));
    }

    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer toCreated = modelMapper.map(request, Customer.class);
        return modelMapper.map(customerRepository.save(toCreated), CustomerResponse.class);
    }

    public CustomerResponse updateCustomer(String id, CustomerRequest request) {
        if (!customerRepository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(CUSTOMER, id);
        }
        Customer toUpdate = modelMapper.map(request, Customer.class);
        toUpdate.setId(UUID.fromString(id));
        return modelMapper.map(customerRepository.save(toUpdate), CustomerResponse.class);
    }

    public void deleteCustomer(String id) {
        if (!customerRepository.existsById(UUID.fromString(id))) {
            throw new ResourceNotFoundException(CUSTOMER, id);
        }
        customerRepository.deleteById(UUID.fromString(id));
    }
}
