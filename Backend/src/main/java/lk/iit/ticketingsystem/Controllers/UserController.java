//package lk.iit.ticketingsystem.Controllers;
//
//import lk.iit.ticketingsystem.Database.UserRepository;
//import lk.iit.ticketingsystem.Models.User;
//import lk.iit.ticketingsystem.services.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//public class UserController {
//    private final UserService userService;
//    private UserRepository userRepository;
//
//    public UserController(UserRepository userRepository, UserService userService) {
//        this.userRepository = userRepository;
//        this.userService = userService;
//    }
//
//    @GetMapping("/test")
//    public String test() {
//        return "test";
//    }
//
//    @PostMapping("/add-user")
//    public String addUser(@RequestBody User user) {
//        try {
//            userRepository.save(user);
//            return "User added successfully";
//        } catch (Exception e) {
//            return "Error in saving user: " + e.getMessage();
//        }
//    }
//
//
//
////    public String addUser(@RequestHeader HttpHeaders headers) {
////        final String fullname = headers.getFirst("fullname");
////        final String username = headers.getFirst("username");
////        final String password = headers.getFirst("password");
////        final String email = headers.getFirst("email");
////        final String role = headers.getFirst("role");
////        User newUser = new User(fullname, username, password, email, role);
////
////        userRepository.save(newUser);
////        return "success";
//
////    @PostMapping("add-user-json")
//
//    @GetMapping("/get-persons")
//    public List<User> getPersons() {
//        return userService.getUsers();
//    }
//
//
//
//}
