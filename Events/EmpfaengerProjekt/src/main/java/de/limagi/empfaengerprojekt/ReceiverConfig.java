package de.limagi.empfaengerprojekt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLOutput;
import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class ReceiverConfig {

    @Bean
    public Consumer<Event> receive() {
        return System.out::println;
    }
//    public Function<Event, Event> myFunc() {
//        return System.out::println;
//    }
}
