package dev.joao.desafiobackend.services;

import dev.joao.desafiobackend.domain.User;
import dev.joao.desafiobackend.domain.transactions.Transaction;
import dev.joao.desafiobackend.dtos.TransactionDTO;
import dev.joao.desafiobackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender = userService.findUserById(transactionDTO.senderId());
        User receiver = userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());
        if(!this.authorizeTransaction(sender, transactionDTO.value())){
            throw new Exception("Transaction unauthorized!");
        }

        Transaction newTansaction = new Transaction();
        newTansaction.setAmount(transactionDTO.value());
        newTansaction.setReceiver(receiver);
        newTansaction.setSender(sender);
        newTansaction.setTimeStamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transactionRepository.save(newTansaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        return newTansaction;

    }

    public boolean authorizeTransaction(User user, BigDecimal value){
        ResponseEntity<Map> authorizationReponse = restTemplate.getForEntity("https://run.mocky.io/v3/2e897010-9fc5-469a-b237-ae0e83e0dbfb", Map.class);
        if(authorizationReponse.getStatusCode() == HttpStatus.OK){
            String message = (String) authorizationReponse.getBody().get("message");
            return "Authorized".equalsIgnoreCase(message);
        }else return false;
    }

}
