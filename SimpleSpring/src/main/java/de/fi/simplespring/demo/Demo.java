package de.fi.simplespring.demo;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")// Default
//@Scope("prototype")
@Lazy
public class Demo {

    public Demo() {

        System.out.println("Ctor Demo");
    }

    @PostConstruct
    public void sayHello() {
        System.out.println("Hello World");
    }


    // Achtung: Nicht bei Prototype
    @PreDestroy
    public void close() {
        System.out.println("Goodbye World");
    }
}
