package com.travelsave.buses.customer.services;

import com.travelsave.buses.Command;
import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService implements Command<Customer, CustomerDTO> {

  private final CustomerRepository customerRepository;

  public CreateCustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<CustomerDTO> execute(Customer customer) {
//    CustomerValidator.execute(customer);
    Customer savedCustomer = customerRepository.save(customer);
    return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDTO(savedCustomer));
  }
}