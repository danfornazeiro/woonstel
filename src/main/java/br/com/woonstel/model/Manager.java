package br.com.woonstel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "manager_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends User {
    private LocalDate startMandate;
    private LocalDate finalMandate;

}
