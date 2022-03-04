package components.emybank.services.loan;

import components.emybank.models.inputModels.Bank;
import components.emybank.models.inputModels.Loan;

import java.util.List;

public interface LoanService {
    List<Loan> findAll();
    Loan updateOne(Loan loan);
    Loan insertOne(Loan loan);
    Loan findById(int id);
    boolean deleteOne(int id);
    List<Loan> findByAccountId(int id);
}
