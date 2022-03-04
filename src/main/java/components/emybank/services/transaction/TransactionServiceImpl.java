package components.emybank.services.transaction;

import components.emybank.models.inputModels.Transaction;
import components.emybank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TransactionServiceImpl implements TransactionService{
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByAccountId(int id) {
        return transactionRepository.findTransactionByAccountId(id);
    }

    @Override
    public Transaction deposit(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
