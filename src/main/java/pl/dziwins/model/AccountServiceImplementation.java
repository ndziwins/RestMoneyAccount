package pl.dziwins.model;

import org.springframework.beans.factory.annotation.Autowired;

public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    @Override
    public void save(Account account) {

    }

    @Override
    public void edit(int id, Account newSource) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Account findById(Integer id) {
        return null;
    }
}
