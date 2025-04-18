package com.travelsave.buses.customer.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import com.travelsave.buses.customer.model.UpdateCustomerCommand;
import com.travelsave.buses.exceptions.customer.CustomerNotFoundException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateCustomerService implements Command<UpdateCustomerCommand, CustomerDTO> {

  private final CustomerRepository customerRepository;

  public UpdateCustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<CustomerDTO> execute(UpdateCustomerCommand command) {
    final int id = command.id();
    final Optional<Customer> optionalCustomer = customerRepository.findById(id);
    if (optionalCustomer.isPresent()) {
      final Customer customer = command.customer();
      customer.setId(id);
//      CustomerValidator.execute(customer);
      customerRepository.save(customer);
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(new CustomerDTO(customer));
    }
    throw new CustomerNotFoundException();
  }
}
