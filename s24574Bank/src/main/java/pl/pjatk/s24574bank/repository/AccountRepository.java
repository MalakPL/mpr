package pl.pjatk.s24574bank.repository;

import org.springframework.stereotype.Repository;
import pl.pjatk.s24574bank.exception.DatabaseException;
import pl.pjatk.s24574bank.model.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    private int AUTO_INCREMENT = 1;
    private List<Account> accountList = new ArrayList<>();

    public void append(Account account) throws Exception
    {
        account.setId(AUTO_INCREMENT++);

        if (findById(account.getId()).isPresent()) {
            throw new DatabaseException("Account already exists!");
        }

        accountList.add(account);
    }

    public Optional<Account> findById(int id) {
        return accountList.stream().filter(it -> it.getId() == id).findFirst();
    }

    public void setAmount(Account account, float amount) {
        accountList.stream()
                .filter(it -> it.equals(account))
                .forEach(it -> it.setAmount(amount));
    }

    public void removeAll() {
        AUTO_INCREMENT = 1;
        accountList = new ArrayList<>();
    }
}
