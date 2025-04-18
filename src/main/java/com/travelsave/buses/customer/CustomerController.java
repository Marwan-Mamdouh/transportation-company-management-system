package com.travelsave.buses.customer;

import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import com.travelsave.buses.customer.model.UpdateCustomerCommand;
import com.travelsave.buses.customer.services.CreateCustomerService;
import com.travelsave.buses.customer.services.DeleteCustomerService;
import com.travelsave.buses.customer.services.GetCustomerService;
import com.travelsave.buses.customer.services.GetCustomersService;
import com.travelsave.buses.customer.services.SearchCustomerFirstNameService;
import com.travelsave.buses.customer.services.SearchCustomerLastNameService;
import com.travelsave.buses.customer.services.UpdateCustomerService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  private final CreateCustomerService createCustomerService;

  private final DeleteCustomerService deleteCustomerService;

  private final GetCustomerService getCustomerService;
  private final GetCustomersService getCustomersService;
  private final SearchCustomerFirstNameService searchCustomerFirstNameService;
  private final SearchCustomerLastNameService searchCustomerLastNameService;

  private final UpdateCustomerService updateCustomerService;


  public CustomerController(CreateCustomerService createCustomerService,
      DeleteCustomerService deleteCustomerService, GetCustomerService getCustomerService,
      UpdateCustomerService updateCustomerService, GetCustomersService getCustomersService,
      SearchCustomerLastNameService searchCustomerLastNameService,
      SearchCustomerFirstNameService searchCustomerFirstNameService) {
    this.createCustomerService = createCustomerService;
    this.deleteCustomerService = deleteCustomerService;
    this.getCustomerService = getCustomerService;
    this.getCustomersService = getCustomersService;
    this.searchCustomerLastNameService = searchCustomerLastNameService;
    this.searchCustomerFirstNameService = searchCustomerFirstNameService;
    this.updateCustomerService = updateCustomerService;
  }

  @PostMapping("/customer")
  public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer customer) {
    return createCustomerService.execute(customer);
  }

  @GetMapping("/customers")
  public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
    return getCustomersService.execute(null);
  }

  @GetMapping("/customer/search/lastName")
  public ResponseEntity<List<CustomerDTO>> searchCustomerLastName(@RequestParam String name) {
    return searchCustomerLastNameService.execute(name);
  }

  @GetMapping("/customer/search/firstName")
  public ResponseEntity<List<CustomerDTO>> searchCustomerFirstName(@RequestParam String name) {
    return searchCustomerFirstNameService.execute(name);
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
    return getCustomerService.execute(id);
  }

  @PutMapping("/customer/{id}")
  public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id,
      @RequestBody Customer customer) {
    return updateCustomerService.execute(new UpdateCustomerCommand(id, customer));
  }

  @DeleteMapping("/customer/{id}")
  public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
    return deleteCustomerService.execute(id);
  }
}