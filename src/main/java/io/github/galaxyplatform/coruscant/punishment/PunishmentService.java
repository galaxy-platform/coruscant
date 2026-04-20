package io.github.galaxyplatform.coruscant.punishment;

import io.github.galaxyplatform.punishment.Punishment;
import io.github.galaxyplatform.punishment.Revocation;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class PunishmentService {

    private final ConcurrentMap<UUID, Punishment> punishments = new ConcurrentHashMap<>();

    public Collection<Punishment> all() {
        return punishments.values();
    }

    public Optional<Punishment> byId(UUID id) {
        return Optional.ofNullable(punishments.get(id));
    }

    public List<Punishment> forTarget(UUID targetUuid) {
        return punishments.values().stream()
                .filter(p -> p.target().uuid().equals(targetUuid))
                .toList();
    }

    public Punishment save(Punishment punishment) {
        punishments.put(punishment.id(), punishment);
        return punishment;
    }

    public Optional<Punishment> revoke(UUID id, Revocation revocation) {
        return byId(id).map(existing -> {
            Punishment revoked = existing.withRevocation(revocation);
            punishments.put(id, revoked);
            return revoked;
        });
    }
}
