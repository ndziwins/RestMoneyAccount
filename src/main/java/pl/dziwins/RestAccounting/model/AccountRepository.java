package pl.dziwins.RestAccounting.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAll();

    Page<Account> findAll(Pageable page);

    Optional<Account> findById(Integer id);

    Optional<Account> findByName(String name);

    Optional<Account> findByCurrency(Currency currency);

    Optional<Account> findIfTreasury(boolean isTreasury);

    boolean existsById(Integer id);

    Account save(Account entity);
}
