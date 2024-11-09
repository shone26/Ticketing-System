package lk.iit.ticketingsystem.Controllers;

import lk.iit.ticketingsystem.Database.UserRepository;
import lk.iit.ticketingsystem.Models.User;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("add-user")
    public String addUser(@RequestHeader HttpHeaders headers) {
        final String fullname = headers.getFirst("fullname");
        final String username = headers.getFirst("username");
        final String password = headers.getFirst("password");
        final String email = headers.getFirst("email");
        User newUser = new User(fullname, username, password, email);

        userRepository.save(newUser);
        return "success";
    }



}
