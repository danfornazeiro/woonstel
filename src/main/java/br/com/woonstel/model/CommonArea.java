package br.com.woonstel.model;

import br.com.woonstel.model.enums.CommonAreaStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
