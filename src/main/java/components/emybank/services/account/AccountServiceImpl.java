package components.emybank.services.account;

import components.emybank.models.dtos.AccountDTO;
import components.emybank.models.inputModels.Account;
import components.emybank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AccountServiceImpl  implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public String encodePassword(String password) {
        String hashPassword = passwordEncoder.encode(password);
        return hashPassword;
    }

    @Override
    public Account login(Account account) {
        String password = encodePassword(account.getPassword());
        Account result = accountRepository.findAccountByUserNameAndPassword(account.getUserName(), password);
        if (result != null) {
            return result;
        }
        else {
            return null;
        }
    }

    @Override
    public Account findById(int id) {
        return accountRepository.findById(id).get();
    }

    @Override
    public Account updateOne(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account register(AccountDTO accountDTO) {
        Account account = new Account();
        String password = encodePassword(accountDTO.getPassword());
        account.setUserName(accountDTO.getUserName());
        account.setPassword(password);
        account.setAccountType_id(accountDTO.getAccountType_id());
        account.setTotalAmount(accountDTO.getTotalAmount());
        account.setFullName(accountDTO.getFullName());
        account.setAddress(accountDTO.getAddress());
        account.setPhone(accountDTO.getPhone());
        account.setGender(accountDTO.getGender());
        account.setBirth(accountDTO.getBirth());
        account.setImage(accountDTO.getImage());
        account.setCreatedAt(new Date());
        return accountRepository.save(account);
    }
}
