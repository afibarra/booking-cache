package me.afibarra.bookingcache.model;

public class CustomerBuilder {

    private String customerId;
    private String firstName;

    public CustomerBuilder setCustomerId(String customerId) {
        this.customerId = customerId;

        return this;
    }

    public CustomerBuilder setFirstName(String firstName) {
        this.firstName = firstName;

        return this;
    }

    public Customer buildCustomer() {
        return new Customer(
            customerId,
            firstName
        );
    }
}
