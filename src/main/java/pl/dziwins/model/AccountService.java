package pl.dziwins.model;

public interface AccountService {

    void save(Account account);

    void edit(int id, Account newSource);

    void delete(int id);

    Account findById(Integer id);
}

