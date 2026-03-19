package br.com.woonstel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "resident_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Resident{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer apartment;
    private String block;

    @OneToOne
    private User user;


}
