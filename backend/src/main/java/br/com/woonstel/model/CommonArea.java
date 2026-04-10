package br.com.woonstel.model;

import br.com.woonstel.model.enums.CommonAreaStatusEnum;
import br.com.woonstel.model.enums.StatusCommonArea;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name =  "commom_area_tb")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonArea {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private CommonAreaStatusEnum status;
    private Double price;
    private StatusCommonArea statusCommonArea;
    private String description;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "condominium_id")
    private Condominium condominium;

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;

}
