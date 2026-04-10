package br.com.woonstel.model;

import br.com.woonstel.model.enums.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "third_party_company_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String cnpj;
    private String contactPerson;
    private String phone;
    private String email;
    private String website;
    private String address;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    private String description;
    private Double averageRating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

    @OneToMany(mappedBy = "thirdPartyCompany", cascade = CascadeType.ALL)
    private List<ScheduledService> scheduledServices;

}

