package de.limago.sender;

import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Sender {

    private final String destination ="sender-out-0";
    private final StreamBridge bridge;

    public void send(){
        System.out.println("Start sending...");
        bridge.send(destination, Event.builder().payload("test").build());
        System.out.println("ok!");
    }
}
