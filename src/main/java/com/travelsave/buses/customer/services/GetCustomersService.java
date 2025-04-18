package com.travelsave.buses.customer.services;

import com.travelsave.buses.Query;
import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetCustomersService implements Query<Void, List<CustomerDTO>> {

  private final CustomerRepository customerRepository;

  public GetCustomersService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<List<CustomerDTO>> execute(Void input) {
    List<Customer> customers = customerRepository.findAll();
    List<CustomerDTO> customerDTOS = customers.stream().map(CustomerDTO::new).toList();
    return ResponseEntity.ok().body(customerDTOS);
  }
}
