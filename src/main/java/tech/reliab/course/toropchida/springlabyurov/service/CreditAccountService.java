package tech.reliab.course.toropchida.springlabyurov.service;

import tech.reliab.course.toropchida.springlabyurov.entity.CreditAccount;

import java.util.List;
import java.util.Optional;


public interface CreditAccountService {
    CreditAccount createCreditAccount(CreditAccount bank);

    List<CreditAccount> getAllCreditAccounts();

    Optional<CreditAccount> getCreditAccount(Long id);

    CreditAccount updateCreditAccount(Long id, CreditAccount creditAccDetail);

    void deleteCreditAccount(Long id);

    void deleteAllCreditAccounts();
}