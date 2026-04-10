package br.com.woonstel.repositoy;

import br.com.woonstel.model.CondomiumInvitationLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CondomiumInvitationLinkRepository extends JpaRepository<CondomiumInvitationLink, UUID> {

    /**
     * Buscar link de convite pelo código único
     */
    Optional<CondomiumInvitationLink> findByInvitationCode(String invitationCode);

    /**
     * Buscar links ativos para um condomínio
     */
    @Query("SELECT l FROM CondomiumInvitationLink l " +
           "WHERE l.condominium.id = :condominiumId " +
           "AND l.isActive = true " +
           "AND (l.expiresAt IS NULL OR l.expiresAt > CURRENT_TIMESTAMP)")
    List<CondomiumInvitationLink> findActiveLinksForCondominium(@Param("condominiumId") UUID condominiumId);

    /**
     * Buscar todos os links criados por um síndico
     */
    List<CondomiumInvitationLink> findByCreatedBy_Id(UUID managerId);

    /**
     * Buscar links que expiraram
     */
    @Query("SELECT l FROM CondomiumInvitationLink l " +
           "WHERE l.expiresAt IS NOT NULL " +
           "AND l.expiresAt <= CURRENT_TIMESTAMP " +
           "AND l.isActive = true")
    List<CondomiumInvitationLink> findExpiredLinks();

    /**
     * Buscar links que atingiram limite de usos
     */
    @Query("SELECT l FROM CondomiumInvitationLink l " +
           "WHERE l.maxUses IS NOT NULL " +
           "AND l.currentUses >= l.maxUses " +
           "AND l.isActive = true")
    List<CondomiumInvitationLink> findLinksWithMaxUsesReached();

    /**
     * Verificar se um link é válido (ativo, não expirado, não atingiu limite)
     */
    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END " +
           "FROM CondomiumInvitationLink l " +
           "WHERE l.invitationCode = :code " +
           "AND l.isActive = true " +
           "AND (l.expiresAt IS NULL OR l.expiresAt > CURRENT_TIMESTAMP) " +
           "AND (l.maxUses IS NULL OR l.currentUses < l.maxUses)")
    boolean isLinkValid(@Param("code") String code);

}

