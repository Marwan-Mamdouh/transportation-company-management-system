package com.travelsave.buses.customer.services;

import com.travelsave.buses.Query;
import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.CustomerDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SearchCustomerLastNameService implements Query<String, List<CustomerDTO>> {

  private final CustomerRepository customerRepository;

  public SearchCustomerLastNameService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public ResponseEntity<List<CustomerDTO>> execute(String name) {
    return ResponseEntity.ok(
        customerRepository.findByLastNameContaining(name).stream().map(CustomerDTO::new).toList());
  }
}
