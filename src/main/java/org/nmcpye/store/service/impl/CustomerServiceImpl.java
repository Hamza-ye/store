package org.nmcpye.store.service.impl;

import java.util.Optional;
import org.nmcpye.store.domain.Customer;
import org.nmcpye.store.repository.CustomerRepository;
import org.nmcpye.store.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Customer}.
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        log.debug("Request to save Customer : {}", customer);
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> partialUpdate(Customer customer) {
        log.debug("Request to partially update Customer : {}", customer);

        return customerRepository
            .findById(customer.getId())
            .map(
                existingCustomer -> {
                    if (customer.getFirstName() != null) {
                        existingCustomer.setFirstName(customer.getFirstName());
                    }
                    if (customer.getLastName() != null) {
                        existingCustomer.setLastName(customer.getLastName());
                    }
                    if (customer.getGender() != null) {
                        existingCustomer.setGender(customer.getGender());
                    }
                    if (customer.getEmail() != null) {
                        existingCustomer.setEmail(customer.getEmail());
                    }
                    if (customer.getPhone() != null) {
                        existingCustomer.setPhone(customer.getPhone());
                    }
                    if (customer.getAddressLine1() != null) {
                        existingCustomer.setAddressLine1(customer.getAddressLine1());
                    }
                    if (customer.getAddressLine2() != null) {
                        existingCustomer.setAddressLine2(customer.getAddressLine2());
                    }
                    if (customer.getCity() != null) {
                        existingCustomer.setCity(customer.getCity());
                    }
                    if (customer.getCountry() != null) {
                        existingCustomer.setCountry(customer.getCountry());
                    }

                    return existingCustomer;
                }
            )
            .map(customerRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Customer> findAll(Pageable pageable) {
        log.debug("Request to get all Customers");
        return customerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(Long id) {
        log.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }
}
