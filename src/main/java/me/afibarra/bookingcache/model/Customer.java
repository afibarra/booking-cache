package me.afibarra.bookingcache.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import java.util.StringJoiner;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Customer")
public class Customer {

    @Id
    @JsonIgnore
    private String id;
    private final String customerId;
    private final String firstName;

    public Customer(String customerId, String firstName) {
        this.customerId = customerId;
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Customer.class.getSimpleName() + "[", "]")
            .add("id='" + id + "'")
            .add("customerId='" + customerId + "'")
            .add("firstName='" + firstName + "'")
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return id.equals(customer.id) && customerId.equals(customer.customerId) && firstName.equals(
            customer.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, firstName);
    }
}
