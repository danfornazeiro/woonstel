package br.com.woonstel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "condomium_invitation_link_tb")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondomiumInvitationLink {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Token único para usar como link de convite
     * Formato sugerido: base64 encoded string ou UUID randomizado
     */
    @Column(unique = true, nullable = false)
    private String invitationCode;

    /**
     * Link completo que será enviado aos moradores
     * Exemplo: https://woonstel.com/invite/abc123def456
     */
    private String invitationUrl;

    /**
     * Indica se o link ainda está ativo
     */
    private Boolean isActive;

    /**
     * Data de criação do link
     */
    private LocalDateTime createdAt;

    /**
     * Data de expiração do link (pode ser null para nunca expirar)
     */
    private LocalDateTime expiresAt;

    /**
     * Data em que o link foi desativado
     */
    private LocalDateTime deactivatedAt;

    /**
     * Quantidade máxima de usos permitidos (null = ilimitado)
     */
    private Integer maxUses;

    /**
     * Quantidade atual de usos
     */
    private Integer currentUses;

    /**
     * Observações sobre o link
     */
    private String notes;

    @ManyToOne
    @JoinColumn(name = "condominium_id", nullable = false)
    private Condominium condominium;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager createdBy;

}

