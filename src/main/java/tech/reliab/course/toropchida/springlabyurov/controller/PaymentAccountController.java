package tech.reliab.course.toropchida.springlabyurov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.toropchida.springlabyurov.entity.PaymentAccount;
import tech.reliab.course.toropchida.springlabyurov.service.PaymentAccountService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PaymentAccountController {
    public final PaymentAccountService paymentAccountService;

    @GetMapping("/payment_account")
    public ResponseEntity<List<PaymentAccount>> getAllPaymentAccounts() {
        try {
            return new ResponseEntity<>(paymentAccountService.getAllPaymentAccounts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/payment_account/{id}")
    public ResponseEntity<Optional<PaymentAccount>> getPaymentAccountById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(paymentAccountService.getPaymentAccount(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/payment_account")
    public ResponseEntity<PaymentAccount> createPaymentAccount(@RequestBody PaymentAccount paymentAccount) {
        try {
            return new ResponseEntity<>(paymentAccountService.createPaymentAccount(paymentAccount), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/payment_account/{id}")
    public ResponseEntity<PaymentAccount> updatePaymentAccount(@PathVariable Long id, @RequestBody PaymentAccount pa) {
        try {
            return new ResponseEntity<>(paymentAccountService.updatePaymentAccount(id, pa), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/payment_account")
    public HttpStatus deletePaymentAccounts() {
        try {
            paymentAccountService.deleteAllPaymentAccounts();
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/payment_account/{id}")
    public HttpStatus deletePaymentAccount(@PathVariable Long id) {
        try {
            paymentAccountService.deletePaymentAccount(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}