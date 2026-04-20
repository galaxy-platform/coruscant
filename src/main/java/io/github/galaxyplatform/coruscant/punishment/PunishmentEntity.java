package io.github.galaxyplatform.coruscant.punishment;

import io.github.galaxyplatform.punishment.Punishment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(
        name = "punishments",
        indexes = @Index(name = "idx_punishment_target_uuid", columnList = "target_uuid")
)
public class PunishmentEntity {

    @Id
    private UUID id;

    @Column(name = "target_uuid", nullable = false)
    private UUID targetUuid;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Punishment data;

    protected PunishmentEntity() {
    }

    public PunishmentEntity(Punishment punishment) {
        setData(punishment);
    }

    public UUID getId() {
        return id;
    }

    public UUID getTargetUuid() {
        return targetUuid;
    }

    public Punishment getData() {
        return data;
    }

    public void setData(Punishment data) {
        this.id = data.id();
        this.targetUuid = data.target().uuid();
        this.data = data;
    }
}
