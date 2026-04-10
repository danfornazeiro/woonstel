package br.com.woonstel.model;

import br.com.woonstel.model.enums.ScheduledServiceStatus;
import br.com.woonstel.model.enums.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "scheduled_service_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledService {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;
    private LocalDateTime scheduledDate;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    private ScheduledServiceStatus status;

    private Double estimatedCost;
    private Double actualCost;
    private String observations;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

    @ManyToOne
    @JoinColumn(name = "third_party_company_id")
    private ThirdPartyCompany thirdPartyCompany;

}

