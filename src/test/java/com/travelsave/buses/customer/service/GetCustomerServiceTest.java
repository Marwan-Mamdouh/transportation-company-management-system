package com.travelsave.buses.customer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.travelsave.buses.customer.CustomerRepository;
import com.travelsave.buses.customer.model.Customer;
import com.travelsave.buses.customer.model.CustomerDTO;
import com.travelsave.buses.customer.services.GetCustomerService;
import com.travelsave.buses.exceptions.customer.CustomerNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetCustomerServiceTest {

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private GetCustomerService getCustomerService;

  @BeforeEach
  protected void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void gavin_customer_exists_when_get_customer_service_return_customer_dto() {
    // gavin
    Customer customer = new Customer(1, "testFirst", "testFirst", "testemail1@email.com",
        "01012345679", LocalDate.parse("2022-10-12"));
    // when
    when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
    ResponseEntity<CustomerDTO> response = getCustomerService.execute(1);

    // then
    assertEquals(ResponseEntity.ok(new CustomerDTO(customer)), response);
    verify(customerRepository, times(1)).findById(1);
  }

  @Test
  public void gavin_customer_does_not_exists_when_get_customer_service_throw_not_found_exception() {
    // gavin
    final int wantedCustomerId = 1;
    when(customerRepository.findById(wantedCustomerId)).thenReturn(Optional.empty());

    // when & then
    assertThrows(CustomerNotFoundException.class,
        () -> getCustomerService.execute(wantedCustomerId));
    verify(customerRepository, times(1)).findById(wantedCustomerId);
  }
}
