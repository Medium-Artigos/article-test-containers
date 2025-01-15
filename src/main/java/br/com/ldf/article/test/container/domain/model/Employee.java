package br.com.ldf.article.test.container.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Employee {
    Long id;
    String name;
    BigDecimal salary;
}
