package io.github.galaxyplatform.coruscant.account;

import io.github.galaxyplatform.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private UUID id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private Account data;

    protected AccountEntity() {
    }

    public AccountEntity(Account account) {
        this.id = account.uuid();
        this.data = account;
    }

    public UUID getId() {
        return id;
    }

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.id = data.uuid();
        this.data = data;
    }
}
