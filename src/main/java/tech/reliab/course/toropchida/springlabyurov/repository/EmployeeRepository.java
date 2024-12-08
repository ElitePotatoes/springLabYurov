package tech.reliab.course.toropchida.springlabyurov.repository;

import tech.reliab.course.toropchida.springlabyurov.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "SELECT empl FROM Employee empl WHERE empl.bankId = :bankId")
    List<Employee> getEmployeeByBankId(@Param("bankId") Long bankId);
}