package components.emybank.services.account;

import components.emybank.models.dtos.AccountDTO;
import components.emybank.models.inputModels.Account;

import java.util.List;

public interface AccountService {
    Account register(AccountDTO accountDTO);
    Account login(Account account);
    Account findById(int id);
    Account updateOne(Account account);
    List<Account> findAll();
    Account findByName(String userName);
}
