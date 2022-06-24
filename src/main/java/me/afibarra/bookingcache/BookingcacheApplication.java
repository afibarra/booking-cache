package me.afibarra.bookingcache;

import me.afibarra.bookingcache.configuration.RedisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
    RedisConfiguration.class
})
public class BookingcacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingcacheApplication.class, args);
    }

}
