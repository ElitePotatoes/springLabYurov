package tech.reliab.course.toropchida.springlabyurov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.toropchida.springlabyurov.entity.Bank;
import tech.reliab.course.toropchida.springlabyurov.service.BankService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BankController {
    public final BankService bankService;

    @GetMapping("/bank")
    public ResponseEntity<List<Bank>> getAllBanks() {
        try {
            return new ResponseEntity<>(bankService.getAllBanks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<Optional<Bank>> getBankById(@PathVariable Long id) {
        try {
            bankService.outputAllBankInfo(id);
            return new ResponseEntity<>(bankService.getBank(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bank")
    public ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        try {
            return new ResponseEntity<>(bankService.createBank(bank), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bank/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long id, @RequestBody Bank bankDetails) {
        try {
            return new ResponseEntity<>(bankService.updateBank(id, bankDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bank")
    public HttpStatus deleteBanks() {
        try {
            bankService.deleteAllBanks();
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/bank/{id}")
    public HttpStatus deleteBank(@PathVariable Long id) {
        try {
            bankService.deleteBank(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}