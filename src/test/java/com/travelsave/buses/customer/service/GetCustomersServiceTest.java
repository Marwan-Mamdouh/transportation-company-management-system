package com.travelsave.buses.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import com.travelsave.buses.customer.services.GetCustomersService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetCustomersServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private GetCustomersService getCustomersService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_multiple_customer_when_get_customers_service_return_all_customers_dto() {
    // gavin
    Customer customer1 = new Customer(1, "test1", "test1", "testemail1@email.com", "01012345679",
        LocalDate.parse("2012-10-12"));
    Customer customer2 = new Customer(2, "test2", "test2", "testemail2@email.com", "01012345679",
        LocalDate.parse("2022-10-12"));
    List<Customer> customers = List.of(customer1, customer2);

    // when
    when(customerRepository.findAll()).thenReturn(customers);
    ResponseEntity<List<CustomerDTO>> response = getCustomersService.execute(null);
    // then

    List<CustomerDTO> customerDTOS = customers.stream().map(CustomerDTO::new).toList();
    assertEquals(ResponseEntity.ok(customerDTOS), response);
    verify(customerRepository, times(1)).findAll();
  }

  @Test
  public void gavin_customers_does_not_exists_when_get_customers_service_return_empty_list() {

    when(customerRepository.findAll()).thenReturn(List.of());

    ResponseEntity<List<CustomerDTO>> response = getCustomersService.execute(null);

    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(customerRepository, times(1)).findAll();
  }
}