package components.emybank.services.transaction;
import components.emybank.models.inputModels.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    List<Transaction> findByAccountId(int id);
    Transaction deposit(Transaction transaction);
}
