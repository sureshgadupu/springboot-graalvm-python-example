package dev.fullstackcode.graalvm.controller;


import dev.fullstackcode.graalvm.service.WelcomeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.graalvm.polyglot.*;

@RestController
public class WelcomeController {

    WelcomeService helloService;

    public WelcomeController(WelcomeService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name) {
//        try (Context context = Context.create("python")) {
//            context.eval("python", "from hello import hello");
//            Value helloFunc = context.getBindings("python").getMember("hello");
//            return helloFunc.execute(name).asString();
//
//        }

        return helloService.hello(name);

    }
}
