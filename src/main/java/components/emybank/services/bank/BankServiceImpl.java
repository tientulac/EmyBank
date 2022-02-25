package components.emybank.services.bank;

import components.emybank.models.inputModels.Bank;
import components.emybank.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl implements BankService{

    @Autowired
    BankRepository bankRepository;

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public Bank updateOne(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public Bank findById(int id) {
        return bankRepository.findById(id).get();
    }
}
