package components.emybank.services.bank;

import components.emybank.models.inputModels.Bank;

import java.util.List;

public interface BankService {
    List<Bank> findAll();
    Bank updateOne(Bank bank);
    Bank findById(int id);
}
