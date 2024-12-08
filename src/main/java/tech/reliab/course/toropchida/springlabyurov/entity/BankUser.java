package tech.reliab.course.toropchida.springlabyurov.entity;

import lombok.*;
import jakarta.persistence.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    Date dateOfBirth;

    @Column(nullable = false)
    String workplace;

    Integer monthlyIncome;

    @Column(nullable = false)
    String bankUsed;

    Long creditAccountId;
    Long paymentAccountId;
    Integer creditRating;
}