package br.com.ldf.article.test.container.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @Column(name = "name", nullable = false, unique = true)
    String name;
    @Column(name = "salary", nullable = false)
    BigDecimal salary;
}
