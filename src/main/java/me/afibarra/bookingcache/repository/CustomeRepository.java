package me.afibarra.bookingcache.repository;

import me.afibarra.bookingcache.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomeRepository extends CrudRepository<Customer, String> {

}
