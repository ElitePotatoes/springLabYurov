package tech.reliab.course.toropchida.springlabyurov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.toropchida.springlabyurov.entity.CreditAccount;
import tech.reliab.course.toropchida.springlabyurov.service.CreditAccountService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class CreditAccountController {
    public final CreditAccountService creditAccountService;

    @GetMapping("/credit_account")
    public ResponseEntity<List<CreditAccount>> getAllCreditAccounts() {
        try {
            return new ResponseEntity<>(creditAccountService.getAllCreditAccounts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/credit_account/{id}")
    public ResponseEntity<Optional<CreditAccount>> getCreditAccountById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(creditAccountService.getCreditAccount(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/credit_account")
    public ResponseEntity<CreditAccount> createCreditAccount(@RequestBody CreditAccount creditAccount) {
        try {
            return new ResponseEntity<>(creditAccountService.createCreditAccount(creditAccount), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/credit_account/{id}")
    public ResponseEntity<CreditAccount> updateCreditAccount(@PathVariable Long id, @RequestBody CreditAccount ca) {
        try {
            return new ResponseEntity<>(creditAccountService.updateCreditAccount(id, ca), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/credit_account")
    public HttpStatus deleteCreditAccounts() {
        try {
            creditAccountService.deleteAllCreditAccounts();
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/credit_account/{id}")
    public HttpStatus deleteCreditAccount(@PathVariable Long id) {
        try {
            creditAccountService.deleteCreditAccount(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}