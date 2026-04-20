package io.github.galaxyplatform.coruscant.punishment;

import io.github.galaxyplatform.punishment.Punishment;
import io.github.galaxyplatform.punishment.Revocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class PunishmentService {

    private final PunishmentRepository repository;

    public PunishmentService(PunishmentRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Punishment> all() {
        return repository.findAll().stream().map(PunishmentEntity::getData).toList();
    }

    @Transactional(readOnly = true)
    public Optional<Punishment> byId(UUID id) {
        return repository.findById(id).map(PunishmentEntity::getData);
    }

    @Transactional(readOnly = true)
    public List<Punishment> forTarget(UUID targetUuid) {
        return repository.findByTargetUuid(targetUuid).stream().map(PunishmentEntity::getData).toList();
    }

    public Punishment save(Punishment punishment) {
        PunishmentEntity entity = repository.findById(punishment.id())
                .orElseGet(() -> new PunishmentEntity(punishment));
        entity.setData(punishment);
        return repository.save(entity).getData();
    }

    public Optional<Punishment> revoke(UUID id, Revocation revocation) {
        return repository.findById(id).map(entity -> {
            entity.setData(entity.getData().withRevocation(revocation));
            return repository.save(entity).getData();
        });
    }
}
