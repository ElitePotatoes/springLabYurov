package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.Bank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    @Query(value = "SELECT bank.totalMoney FROM Bank bank WHERE bank.id = :id")
    int getTotalMoneyByBankId(@Param("id") Long id);

    @Query(value = "SELECT bank.numberAtms FROM Bank bank WHERE bank.id = :id")
    int getNumberAtmsByBankId(@Param("id") Long id);

    @Query(value = "SELECT bank.interestRate FROM Bank bank WHERE bank.name = :name")
    float getInterestRateByBankName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bank bank SET bank.numberUsers = bank.numberUsers + 1 WHERE bank.name = :name")
    void incrementNumberUsersByBankName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bank bank SET bank.numberEmployees = bank.numberEmployees + 1 WHERE bank.id = :id")
    void incrementNumberEmployeesByBankId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bank bank SET bank.numberAtms = bank.numberAtms + 1 WHERE bank.id = :id")
    void incrementNumberAtmsByBankId(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Bank bank SET bank.numberOffices = bank.numberOffices + 1 WHERE bank.id = :id")
    void incrementNumberOfficesByBankId(@Param("id") Long id);
}