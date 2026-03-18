package br.com.woonstel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends User {
    private String roleFunction;
    private Double salary;
}
