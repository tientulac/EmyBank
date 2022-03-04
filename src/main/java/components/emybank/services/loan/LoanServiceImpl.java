package components.emybank.services.loan;
import components.emybank.models.inputModels.Loan;
import components.emybank.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    @Override
    public Loan updateOne(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan insertOne(Loan loan) {
        return loanRepository.save(loan);
    }

    @Override
    public Loan findById(int id) {
        return loanRepository.findById(id).get();
    }

    @Override
    public boolean deleteOne(int id) {
        Loan loan = loanRepository.findById(id).get();
        if (loan != null) {
            loanRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Loan> findByAccountId(int id) {
        List<Loan> list = loanRepository.findLoanByAccountId(id);
        if (list.size() > 0) {
            return  list;
        }
        else {
            return null;
        }
    }
}
