package com.travelsave.buses.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import com.travelsave.buses.customer.services.SearchCustomerLastNameService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class SearchCustomerLastNameServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private SearchCustomerLastNameService searchCustomerLastNameService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin() {

  }

  @Test
  public void gavin_customers_exists_when_get_search_customer_lastName_service_return_dto() {
    // gavin
    Customer customer1 = new Customer(1, "test1", "1test", "testemail1@email.com", "01012345679",
        LocalDate.parse("2012-10-12"));
    Customer customer2 = new Customer(2, "test2", "2test", "testemail2@email.com", "01012345679",
        LocalDate.parse("2022-10-12"));
    List<Customer> customers = List.of(customer1, customer2);
    String name = "1test";

    // when
    when(customerRepository.findByLastNameContaining(name)).thenReturn(customers);
    ResponseEntity<List<CustomerDTO>> response = searchCustomerLastNameService.execute(name);

    // then
    List<CustomerDTO> customerDTOs = customers.stream().map(CustomerDTO::new).toList();
    assertEquals(ResponseEntity.ok(customerDTOs), response);
    verify(customerRepository, times(1)).findByLastNameContaining(name);
  }

  @Test
  public void gavin_customer_does_not_exists_when_get_search_customer_lastName_service_return_nothing() {
    String name = "1test";
    // when
    when(customerRepository.findByLastNameContaining("%" + name + "%")).thenReturn(List.of());
    ResponseEntity<List<CustomerDTO>> response = searchCustomerLastNameService.execute(name);

    // then
    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(customerRepository, times(1)).findByLastNameContaining(name);
  }

  @Test
  public void gavin_customer_does_null_when_get_search_customer_lastName_service_return_nothing() {
    // when
    when(customerRepository.findByLastNameContaining(null)).thenReturn(List.of());
    ResponseEntity<List<CustomerDTO>> response = searchCustomerLastNameService.execute(null);

    // then
    assertEquals(ResponseEntity.ok(List.of()), response);
    verify(customerRepository, times(1)).findByLastNameContaining(null);
  }
}
