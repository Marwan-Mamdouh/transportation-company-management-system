package com.travelsave.buses.customer.services;

import com.travelsave.buses.Query;
import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import com.travelsave.buses.exceptions.customer.CustomerNotFoundException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetCustomerService implements Query<Integer, CustomerDTO> {

  private final CustomerRepository customerRepository;

  public GetCustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<CustomerDTO> execute(Integer id) {
    Optional<Customer> customerOptional = customerRepository.findById(id);
    if (customerOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(new CustomerDTO(customerOptional.get()));
    }
    throw new CustomerNotFoundException();
  }
}