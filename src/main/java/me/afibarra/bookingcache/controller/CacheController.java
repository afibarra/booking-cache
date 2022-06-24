package me.afibarra.bookingcache.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import me.afibarra.bookingcache.model.Customer;
import me.afibarra.bookingcache.model.CustomerBuilder;
import me.afibarra.bookingcache.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/cache")
public class CacheController {

    private final ObjectMapper objectMapper;
    private final CustomerService customerService;

    public CacheController(
        ObjectMapper objectMapper,
        CustomerService customerService) {

        this.objectMapper = objectMapper;
        this.customerService = customerService;
    }

    @GetMapping("/hello")
    public Mono<ResponseEntity<String>> sayHello() {
        return Mono.fromSupplier(() -> ResponseEntity.ok("Hello Cache!"));
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<String> save(@RequestBody String jsonBody)
        throws JsonProcessingException {

        TypeReference<HashMap<String, String>> typeReferenceBody = new TypeReference<>() {
        };
        Map<String, String> bodyAsMap = objectMapper.readValue(jsonBody, typeReferenceBody);

        Customer customer = new CustomerBuilder()
            .setCustomerId(bodyAsMap.get("customerId"))
            .setFirstName(bodyAsMap.get("firstName"))
            .buildCustomer();

        // It is often the case that a source of information is synchronous and also blocking.
        // You can use Mono.fromCallable to deal with such sources.
        Mono<Customer> monoCustomer = Mono.fromCallable(
            () -> customerService.saveCustomer(customer));

        return monoCustomer.map(c -> {
            try {
                return objectMapper.writeValueAsString(c);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
