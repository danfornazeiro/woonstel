package br.com.woonstel.model;

import br.com.woonstel.model.enums.PriorityEnum;
import br.com.woonstel.model.enums.StatusTask;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "task_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String description;
    private String type;
    private String imageUrl;
    
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;
    
    @Enumerated(EnumType.STRING)
    private StatusTask status;
    
    private Double budget;
    private Double actualCost;
    private LocalDateTime deadline;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "solicitation_id")
    private Solicitation solicitation;

    @ManyToOne
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

}
