package de.fi.simplespring.demo;


import de.fi.simplespring.translator.Translator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")// Default
//@Scope("prototype")
//@Lazy
@RequiredArgsConstructor
public class Demo {



    @Qualifier("upper")
    private final Translator translator;

    @Value("${Demo.message}")
    private final String message;

/*
    public Demo(@Qualifier("upper") final Translator translator, @Value("${Demo.message}") final String message) {
        // Erstens Objekte können finalö sein
        // Zweitens Es ist sichergestellt, dass die Abhängigkeit da ist
        // Abhängigkeiten könne im Ctor genutzt werden
        this.message = message;
        this.translator = translator;
        System.out.println(translator.translate("Ctor Demo"));
        System.out.println(translator.translate(message));
    }

*/
    @PostConstruct
    public void sayHello() {

        System.out.println(translator.translate(message));
    }


    // Achtung: Nicht bei Prototype
    @PreDestroy
    public void close() {
        System.out.println("Goodbye World");
    }
}
