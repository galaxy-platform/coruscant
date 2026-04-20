package io.github.galaxyplatform.coruscant.account;

import io.github.galaxyplatform.account.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Account> all() {
        return repository.findAll().stream().map(AccountEntity::getData).toList();
    }

    @Transactional(readOnly = true)
    public Optional<Account> byId(UUID uuid) {
        return repository.findById(uuid).map(AccountEntity::getData);
    }

    public Account save(Account account) {
        AccountEntity entity = repository.findById(account.uuid())
                .orElseGet(() -> new AccountEntity(account));
        entity.setData(account);
        return repository.save(entity).getData();
    }
}
