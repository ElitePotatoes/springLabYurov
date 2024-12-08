package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.BankAtm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAtmRepository extends JpaRepository<BankAtm, Long> {
    @Query(value = "SELECT atm FROM BankAtm atm WHERE atm.bankId = :bankId")
    List<BankAtm> getBankAtmByBankId(@Param("bankId") Long bankId);
}