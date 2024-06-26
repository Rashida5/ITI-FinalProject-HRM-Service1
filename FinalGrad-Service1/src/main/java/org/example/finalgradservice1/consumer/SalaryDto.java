package org.example.finalgradservice1.consumer;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class SalaryDto {
    private Integer id;
    @NotNull
    private Integer empId;
    @NotNull
    private BigDecimal amount;
}
