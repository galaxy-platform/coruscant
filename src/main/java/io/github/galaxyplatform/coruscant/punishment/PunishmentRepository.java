package io.github.galaxyplatform.coruscant.punishment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PunishmentRepository extends JpaRepository<PunishmentEntity, UUID> {

    List<PunishmentEntity> findByTargetUuid(UUID targetUuid);
}
