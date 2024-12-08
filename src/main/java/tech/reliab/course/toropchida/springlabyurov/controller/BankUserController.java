package tech.reliab.course.toropchida.springlabyurov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.toropchida.springlabyurov.entity.BankUser;
import tech.reliab.course.toropchida.springlabyurov.service.BankUserService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BankUserController {
    public final BankUserService bankUserService;

    @GetMapping("/bank_user")
    public ResponseEntity<List<BankUser>> getAllBankUsers() {
        try {
            return new ResponseEntity<>(bankUserService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bank_user/{id}")
    public ResponseEntity<Optional<BankUser>> getBankUserById(@PathVariable Long id) {
        try {
//            bankUserService.outputAllUserInfo(id);
            return new ResponseEntity<>(bankUserService.getUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bank_user")
    public ResponseEntity<BankUser> createBankUser(@RequestBody BankUser bank) {
        try {
            return new ResponseEntity<>(bankUserService.createUser(bank), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bank_user/{id}")
    public ResponseEntity<BankUser> updateBankUser(@PathVariable Long id, @RequestBody BankUser bankUserDetails) {
        try {
            return new ResponseEntity<>(bankUserService.updateUser(id, bankUserDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bank_user")
    public HttpStatus deleteBankUsers() {
        try {
            bankUserService.deleteAllUsers();
            return  HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/bank_user/{id}")
    public HttpStatus deleteBankUser(@PathVariable Long id) {
        try {
            bankUserService.deleteUser(id);
            return  HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}