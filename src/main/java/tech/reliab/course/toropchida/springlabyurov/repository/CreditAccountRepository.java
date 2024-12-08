package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.CreditAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, Long> {
    @Query(value = "SELECT ca FROM CreditAccount ca WHERE ca.userId = :userId")
    List<CreditAccount> selectByUserId(@Param("userId") Long userId);
}