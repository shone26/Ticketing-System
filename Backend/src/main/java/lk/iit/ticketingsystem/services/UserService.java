//package lk.iit.ticketingsystem.services;
//
//import lk.iit.ticketingsystem.Database.UserRepository;
//import lk.iit.ticketingsystem.Models.User;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionStatus;
//import org.springframework.transaction.support.DefaultTransactionDefinition;
//
//import java.util.List;
//
//@Service
//public class UserService
//{
//    private UserRepository userRepository;
//    final private PlatformTransactionManager transactionManager;
//    final private DefaultTransactionDefinition transactionDefinition;
//
//    public UserService(UserRepository userRepository, PlatformTransactionManager transactionManager) {
//        this.userRepository = userRepository;
//        this.transactionManager = transactionManager;
//
//        transactionDefinition = new DefaultTransactionDefinition();
//        transactionDefinition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_SERIALIZABLE);
//        transactionDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED); //thread safe
//    }
//
//    public String addUser(User user) {
//        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
//        String message;
//
//        try{
//            synchronized (UserRepository.class) {
//                userRepository.save(user);
//            };
//            message = "User added successfully";
//        }
//        catch(Exception e){
//            transactionManager.rollback(transactionStatus); //
//            message = "error in saving person";
//        }
//
//        return message;
//    }
//    public List<User> getUsers() {
//        return userRepository.findAll();
//    }
//}
