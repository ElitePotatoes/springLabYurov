package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.PaymentAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentAccountRepository extends JpaRepository<PaymentAccount, Long> {
    @Query(value = "SELECT pa FROM PaymentAccount pa WHERE pa.userId = :userId")
    List<PaymentAccount> selectByUserId(@Param("userId") Long userId);
}