package br.com.woonstel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "request_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private String type;
    private String priority;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "resident_id")
    private Resident resident;
}
