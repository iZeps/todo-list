package training.programming.controller;

import training.programming.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    // fields
    private final DemoService demoService;

    // constructors
    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    // request methods
    @ResponseBody
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("welcome")
    public String welcome(@RequestParam String user, @RequestParam int age, Model model) {
        model.addAttribute("helloMessage", helloMessage(user));
        model.addAttribute("welcomeMessage", welcomeMessage());
        model.addAttribute("age", age);
        log.info("model - {}", model);
        return "welcome";
    }

    // model attributes
    @ModelAttribute("helloMessage")
    public String helloMessage(String user) {
        return demoService.getHelloMessage(user);
    }

    @ModelAttribute("welcomeMessage")
    public String welcomeMessage() {
        return demoService.getWelcomeMessage();
    }
}
