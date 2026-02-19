package de.limago.sender;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sender")
@AllArgsConstructor
public class MyController {

    private final Sender sender;
    @GetMapping("/send")
    private String send() {
        sender.send();
        return "Ok";
    }
}
