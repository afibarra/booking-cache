package me.afibarra.bookingcache.service;

import me.afibarra.bookingcache.model.Customer;
import me.afibarra.bookingcache.repository.CustomeRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomeRepository customeRepository;

    public CustomerServiceImpl(CustomeRepository customeRepository) {
        this.customeRepository = customeRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customeRepository.save(customer);
    }
}
