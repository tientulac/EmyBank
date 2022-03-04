package components.emybank.services.accountType;

import components.emybank.models.inputModels.AccountType;
import components.emybank.models.inputModels.Bank;
import components.emybank.repositories.AccountTypeRepository;
import components.emybank.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    AccountTypeRepository accountTypeRepository;


    @Override
    public List<AccountType> findAll() {
        return accountTypeRepository.findAll();
    }

    @Override
    public AccountType insertOne(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public AccountType updateOne(AccountType accountType) {
        return accountTypeRepository.save(accountType);
    }

    @Override
    public boolean deleteOne(int id) {
        AccountType accountType = accountTypeRepository.findById(id).get();
        if (accountType != null) {
            accountTypeRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public AccountType findById(int id) {
        return accountTypeRepository.findById(id).get();
    }
}
