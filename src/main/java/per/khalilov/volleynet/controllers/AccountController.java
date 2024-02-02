package per.khalilov.volleynet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/all")
    public String getAll() {
        return "all";
    }

    @GetMapping("/admins")
    public String getAdmins() {
        return "admins";
    }

}
