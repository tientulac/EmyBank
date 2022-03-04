package components.emybank.services.accountType;

import components.emybank.models.inputModels.AccountType;
import components.emybank.models.inputModels.Bank;

import java.util.List;

public interface AccountTypeService {
    List<AccountType> findAll();
    AccountType findById(int id);
    AccountType insertOne(AccountType accountType);
    AccountType updateOne(AccountType accountType);
    boolean deleteOne(int id);
}
