package tech.reliab.course.toropchida.springlabyurov.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAtm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;
    String address;
    String status;
    Long bankId;
    Long bankOfficeId;
    Long employeeId;
    Boolean issuesMoney;
    Boolean depositMoney;
    Integer totalMoney;
    Integer costMaintenance;
}