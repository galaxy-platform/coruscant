package io.github.galaxyplatform.coruscant.account;

import io.github.galaxyplatform.account.Account;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class AccountService {

    private final ConcurrentMap<UUID, Account> accounts = new ConcurrentHashMap<>();

    public Collection<Account> all() {
        return accounts.values();
    }

    public Optional<Account> byId(UUID uuid) {
        return Optional.ofNullable(accounts.get(uuid));
    }

    public Account save(Account account) {
        accounts.put(account.uuid(), account);
        return account;
    }
}
