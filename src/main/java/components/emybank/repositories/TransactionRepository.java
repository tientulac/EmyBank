package components.emybank.repositories;

import components.emybank.models.inputModels.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query("SELECT t FROM Transaction t WHERE t.from_account = ?1")
    List<Transaction> findTransactionByAccountId(int id);
}
