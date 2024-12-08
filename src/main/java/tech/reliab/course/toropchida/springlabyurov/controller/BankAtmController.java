package tech.reliab.course.toropchida.springlabyurov.controller;

import tech.reliab.course.toropchida.springlabyurov.entity.BankAtm;
import tech.reliab.course.toropchida.springlabyurov.service.BankAtmService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BankAtmController {
    public final BankAtmService bankAtmService;

    @GetMapping("/bank_atm")
    public ResponseEntity<List<BankAtm>> getAllBankAtms() {
        try {
            return new ResponseEntity<>(bankAtmService.getAllBankAtms(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bank_atm/{id}")
    public ResponseEntity<Optional<BankAtm>> getBankAtmById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(bankAtmService.getBankAtm(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bank_atm")
    public ResponseEntity<BankAtm> createBankAtm(@RequestBody BankAtm bank) {
        try {
            return new ResponseEntity<>(bankAtmService.createBankAtm(bank), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bank_atm/{id}")
    public ResponseEntity<BankAtm> updateBankAtm(@PathVariable Long id, @RequestBody BankAtm bankAtmDetails) {
        try {
            return new ResponseEntity<>(bankAtmService.updateBankAtm(id, bankAtmDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bank_atm")
    public HttpStatus deleteBankAtms() {
        try {
            bankAtmService.deleteAllBanksAtms();
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/bank_atm/{id}")
    public HttpStatus deleteBankAtm(@PathVariable Long id) {
        try {
            bankAtmService.deleteBankAtm(id);
            return HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}