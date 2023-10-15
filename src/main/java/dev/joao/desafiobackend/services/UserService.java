package dev.joao.desafiobackend.services;

import dev.joao.desafiobackend.domain.User;
import dev.joao.desafiobackend.domain.UserType;
import dev.joao.desafiobackend.dtos.UserDTO;
import dev.joao.desafiobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() ==  UserType.MERCHANT){
            throw new Exception("Merchants are not allowed to send any amount");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Insufficient funds");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("User not found"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }
}
