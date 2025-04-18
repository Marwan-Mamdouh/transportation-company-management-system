package com.travelsave.buses.customer.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.exceptions.customer.CustomerNotFoundException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeleteCustomerService implements Command<Integer, Void> {

  private final CustomerRepository customerRepository;

  public DeleteCustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<Void> execute(Integer id) {
    Optional<Customer> customerOptional = customerRepository.findById(id);
    if (customerOptional.isPresent()) {
      customerRepository.delete(customerOptional.get());
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    throw new CustomerNotFoundException();
  }
}
