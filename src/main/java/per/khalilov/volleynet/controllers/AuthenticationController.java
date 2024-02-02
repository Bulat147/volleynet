package per.khalilov.volleynet.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import per.khalilov.volleynet.dto.forms.SignUpForm;
import per.khalilov.volleynet.models.Account;
import per.khalilov.volleynet.repositories.AccountsRepository;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final PasswordEncoder passwordEncoder;
    private final AccountsRepository accountsRepository;

    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public Account signUp(HttpServletRequest request) {
        Account account = Account.builder()
                .name(request.getParameter("name"))
                .hashPassword(passwordEncoder.encode(request.getParameter("password")))
                .role(Account.Role.USER)
                .build();
        return accountsRepository.save(account);
    }

    @PostMapping("/signUpForm")
    @ResponseStatus(HttpStatus.CREATED)
    public Account signUpForm(@RequestBody SignUpForm form) {
        Account account = Account.builder()
                .name(form.getName())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(Account.Role.USER)
                .build();
        return accountsRepository.save(account);
    }

    @GetMapping("/signIn")
    public String signIn() {
        return "Good.html";
    }
}
