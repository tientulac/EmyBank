package components.emybank.repositories;

import components.emybank.models.inputModels.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository  extends JpaRepository<Account, Integer> {
    @Query("SELECT a FROM Account a WHERE a.userName = ?1 AND a.password = ?2")
    Account findAccountByUserNameAndPassword(String userName, String password);
}
