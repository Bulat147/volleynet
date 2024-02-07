package per.khalilov.volleynet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/chatPage")
public class PagesController {

    @GetMapping
    public String getChatPage(Model model) {
        model.addAttribute("pageId", UUID.randomUUID().toString());
        return "chat";
    }

}
