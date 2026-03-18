package br.com.woonstel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resident_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resident extends User{

    private Integer apartment;
    private String block;

}
