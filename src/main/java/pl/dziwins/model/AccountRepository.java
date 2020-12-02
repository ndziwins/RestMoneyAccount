package pl.dziwins.model;

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

    Optional<Account> findById(int id);

    Optional<Account> findByName(String name);

    List<Account> findByCurrency(Currency currency);

    List<Account> findByIsTreasury(boolean isTreasury);

    boolean existsById(Integer id);

    Account save(Account entity);
}
