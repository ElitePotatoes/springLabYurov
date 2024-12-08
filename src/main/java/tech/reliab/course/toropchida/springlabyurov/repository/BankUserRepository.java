package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.BankUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    @Query(value = "SELECT user FROM BankUser user WHERE user.bankUsed = :bankUsed")
    List<BankUser> getBankUserByBankName(@Param("bankUsed") String bankUsed);

    @Modifying
    @Transactional
    @Query(value = "UPDATE BankUser bu SET bu.creditAccountId = :creditAccountId WHERE bu.id = :id")
    void setCreditAccountIdByUserId(@Param("creditAccountId") Long creditAccountId, @Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE BankUser bu SET bu.paymentAccountId = :paymentAccountId WHERE bu.id = :id")
    void setPaymentAccountIdByUserId(@Param("paymentAccountId") Long paymentAccountId, @Param("id") Long id);
}