package tech.reliab.course.toropchida.springlabyurov.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.toropchida.springlabyurov.entity.BankOffice;
import tech.reliab.course.toropchida.springlabyurov.service.BankOfficeService;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class BankOfficeController {
    public final BankOfficeService bankOfficeService;

    @GetMapping("/bank_office")
    public ResponseEntity<List<BankOffice>> getAllBankOffices() {
        try {
            return new ResponseEntity<>(bankOfficeService.getAllBankOffices(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bank_office/{id}")
    public ResponseEntity<Optional<BankOffice>> getBankOfficeById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(bankOfficeService.getBankOffice(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/bank_office")
    public ResponseEntity<BankOffice> createBankOffice(@RequestBody BankOffice bankOffice) {
        try {
            return new ResponseEntity<>(bankOfficeService.createBankOffice(bankOffice), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/bank_office/{id}")
    public ResponseEntity<BankOffice> updateBankOffice(@PathVariable Long id, @RequestBody BankOffice officeDetails) {
        try {
            return new ResponseEntity<>(bankOfficeService.updateBankOffice(id, officeDetails), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/bank_office")
    public HttpStatus deleteBankOffices() {
        try {
            bankOfficeService.deleteAllBanksOffices();
            return  HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @DeleteMapping("/bank_office/{id}")
    public HttpStatus deleteBankOffice(@PathVariable Long id) {
        try {
            bankOfficeService.deleteBankOffice(id);
            return  HttpStatus.NO_CONTENT;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}