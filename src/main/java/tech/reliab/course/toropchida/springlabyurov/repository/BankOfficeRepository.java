package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.BankOffice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BankOfficeRepository extends JpaRepository<BankOffice, Long> {
    @Query(value = "SELECT office.address FROM BankOffice office WHERE office.id = :id")
    String getAddressByOfficeId(@Param("id") Long id);

    @Query(value = "SELECT office FROM BankOffice office WHERE office.bankId = :bankId")
    List<BankOffice> getBankOfficeByBankId(@Param("bankId") Long bankId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE BankOffice office SET office.numberAtms = office.numberAtms + 1 WHERE office.id = :id")
    void incrementNumberAtmsByOfficeId(@Param("id") Long id);
}