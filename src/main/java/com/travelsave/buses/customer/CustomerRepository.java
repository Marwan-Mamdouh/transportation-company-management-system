package com.travelsave.buses.customer;

import com.travelsave.buses.customer.model.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  List<Customer> findByFirstNameContaining(String name);

  List<Customer> findByLastNameContaining(String name);
}